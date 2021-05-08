package ro.fasttrackit.homeWork9.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.homeWork9.model.entity.Cleanup;
import ro.fasttrackit.homeWork9.service.CleanupService;

import java.util.List;

@RestController
@RequestMapping("rooms")
@RequiredArgsConstructor
public class CleanupController {
    private final CleanupService cleanupService;

    @GetMapping(path = "{roomId}/cleanups")
    List<Cleanup> getCleanups(@PathVariable String roomId) {
        return cleanupService.getCleanups(roomId);
    }

    @PostMapping(path = "/cleanup")
    Cleanup addCleanup(@RequestBody @NonNull Cleanup cleanup) {
        return cleanupService.addCleanup(cleanup);
    }

    @PatchMapping("cleanup/{cleanupId}")
    Cleanup patchCleanup(@RequestBody JsonPatch patch, @PathVariable String cleanupId) {
        return cleanupService.patchCleanup(cleanupId, patch);
    }

    @DeleteMapping(path = "cleanup/{cleanupId}")
    void deleteRoomById(@PathVariable String cleanupId) {
        cleanupService.deleteCleanupById(cleanupId);
    }
}
