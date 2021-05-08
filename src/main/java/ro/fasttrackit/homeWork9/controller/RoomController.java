package ro.fasttrackit.homeWork9.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.homeWork9.controller.exception.EntityNotFoundException;
import ro.fasttrackit.homeWork9.model.CollectionResponse;
import ro.fasttrackit.homeWork9.model.PageInfo;
import ro.fasttrackit.homeWork9.model.RoomsFilters;
import ro.fasttrackit.homeWork9.model.entity.Room;
import ro.fasttrackit.homeWork9.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    CollectionResponse<Room> getAll(RoomsFilters filters, Pageable pageable) {
        List<Room> rooms = roomService.getAllByFilters(filters);
        Page<Room> pagesRooms = new PageImpl<>(rooms);
        return CollectionResponse.<Room>builder()
                .content(pagesRooms.getContent())
                .pageInfo(PageInfo.builder()
                        .totalPages(pagesRooms.getTotalPages())
                        .totalElements(pagesRooms.getNumberOfElements())
                        .crtPage(pageable.getPageNumber())
                        .pageSize(pageable.getPageSize())
                        .build())
                .build();
    }

    @GetMapping(path = "{roomId}")
    Room getRoomById(@PathVariable String roomId) {
        return roomService.getRoomById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Room with id " + roomId + " not found"));
    }

    @PatchMapping("{roomId}")
    Room patchRoom(@RequestBody JsonPatch patch, @PathVariable String roomId) {
        return roomService.patchRoom(roomId, patch);
    }

    @DeleteMapping(path = "{roomId}")
    void deleteRoomById(@PathVariable String roomId) {
        roomService.deleteRoomById(roomId);
    }
}
