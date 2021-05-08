package ro.fasttrackit.homeWork9.service.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fasttrackit.homeWork9.controller.exception.ValidationException;
import ro.fasttrackit.homeWork9.model.entity.Cleanup;
import ro.fasttrackit.homeWork9.repository.RoomRepository;

import java.time.LocalDate;
import java.util.Optional;

import static java.util.Optional.empty;

@Component
@RequiredArgsConstructor
public class CleanupValidator {
    private final RoomRepository roomRepository;

    public void validateExistsOrThrow(String roomId) {
        exists(roomId).ifPresent(ex -> {
            throw ex;
        });
    }

    private Optional<ValidationException> exists(String roomId) {
        return roomRepository.existsById(roomId)
                ? empty()
                : Optional.of(new ValidationException("Room with id " + roomId + " doesn't exist"));
    }

    public void validateReplaceThrow(String cleanupId, Cleanup newCleanup) {
        exists(cleanupId)
                .or(() -> validate(newCleanup))
                .ifPresent(ex -> {
                    throw ex;
                });
    }

    private Optional<ValidationException> validate(Cleanup cleanup) {
        if (cleanup.getDate() == null) {
            return Optional.of(new ValidationException("Date cannot be null"));
        } else if (cleanup.getDate().isAfter(LocalDate.now())) {
            return Optional.of(new ValidationException("Invalid date"));
        } else {
            return empty();
        }
    }
}
