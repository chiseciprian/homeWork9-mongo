package ro.fasttrackit.homeWork9.service.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fasttrackit.homeWork9.controller.exception.ValidationException;
import ro.fasttrackit.homeWork9.model.entity.Room;
import ro.fasttrackit.homeWork9.repository.RoomRepository;

import java.util.Optional;

import static java.util.Optional.empty;

@Component
@RequiredArgsConstructor
public class RoomValidator {
    private final RoomRepository repo;

    public void validateExistsOrThrow(String roomId) {
        exists(roomId).ifPresent(ex -> {
            throw ex;
        });
    }

    public void validateReplaceThrow(String roomId, Room newRoom) {
        exists(roomId)
                .or(() -> validate(newRoom))
                .ifPresent(ex -> {
                    throw ex;
                });
    }

    private Optional<ValidationException> validate(Room room) {
        if (room.getNumber() == null) {
            return Optional.of(new ValidationException("Number cannot be null"));
        } else if (room.getHotelName() == null) {
            return Optional.of(new ValidationException("Hotel name cannot be null"));
        } else if (repo.existsByNumber(room.getNumber())) {
            return Optional.of(new ValidationException("Name cannot be duplicate"));
        } else {
            return empty();
        }
    }

    private Optional<ValidationException> exists(String roomId) {
        return repo.existsById(roomId)
                ? empty()
                : Optional.of(new ValidationException("Room with id " + roomId + " doesn't exist"));
    }
}
