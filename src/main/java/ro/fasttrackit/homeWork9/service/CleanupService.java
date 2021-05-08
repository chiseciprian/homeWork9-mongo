package ro.fasttrackit.homeWork9.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ro.fasttrackit.homeWork9.controller.exception.EntityNotFoundException;
import ro.fasttrackit.homeWork9.controller.exception.ValidationException;
import ro.fasttrackit.homeWork9.model.entity.Cleanup;
import ro.fasttrackit.homeWork9.repository.CleanupRepository;
import ro.fasttrackit.homeWork9.service.validator.CleanupValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CleanupService {
    private final CleanupRepository cleanupRepository;
    private final CleanupValidator validator;
    private final ObjectMapper mapper;

    public List<Cleanup> getCleanups(String roomId) {
        return cleanupRepository.findCleanupsByRoomId(roomId);
    }

    public Cleanup addCleanup(Cleanup cleanup) {
        validator.validateExistsOrThrow(cleanup.getRoomId());
        return cleanupRepository.save(cleanup);
    }

    public void deleteCleanupById(String cleanupId) {
        cleanupRepository.deleteById(cleanupId);
    }

    @SneakyThrows
    public Cleanup patchCleanup(String cleanupId, JsonPatch patch) {
        validator.validateExistsOrThrow(cleanupId);
        Cleanup dbCleanup = cleanupRepository.findById(cleanupId)
                .orElseThrow(() -> new EntityNotFoundException("Couldn't find cleanup with id " + cleanupId));

        JsonNode patchedCleanupJson = patch.apply(mapper.valueToTree(dbCleanup));
        Cleanup patchedCleanup = mapper.treeToValue(patchedCleanupJson, Cleanup.class);
        return replaceCleanup(cleanupId, patchedCleanup);
    }

    private Cleanup replaceCleanup(String cleanupId, Cleanup newCleanup) {
        newCleanup.setCleanupId(cleanupId);
        validator.validateReplaceThrow(cleanupId, newCleanup);

        Cleanup dbProduct = cleanupRepository.findById(cleanupId)
                .orElseThrow(() -> new ValidationException("Couldn't find cleanup with id " + cleanupId));
        copyProduct(newCleanup, dbProduct);
        return cleanupRepository.save(dbProduct);
    }

    private void copyProduct(Cleanup newCleanup, Cleanup dbCleanup) {
        dbCleanup.setRoomId(newCleanup.getRoomId());
        dbCleanup.setDate(newCleanup.getDate());
    }
}
