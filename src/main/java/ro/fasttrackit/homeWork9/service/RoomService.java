package ro.fasttrackit.homeWork9.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ro.fasttrackit.homeWork9.controller.exception.EntityNotFoundException;
import ro.fasttrackit.homeWork9.controller.exception.ValidationException;
import ro.fasttrackit.homeWork9.model.RoomsFilters;
import ro.fasttrackit.homeWork9.model.entity.Room;
import ro.fasttrackit.homeWork9.repository.RoomDao;
import ro.fasttrackit.homeWork9.repository.RoomRepository;
import ro.fasttrackit.homeWork9.service.validator.RoomValidator;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository repository;
    private final RoomDao roomDao;
    private final RoomValidator validator;
    private final ObjectMapper mapper;

    public List<Room> getAllByFilters(RoomsFilters filters) {
        return roomDao.getAll(filters);
    }

    public Optional<Room> getRoomById(String roomId) {
        return repository.findById(roomId);
    }

    public void deleteRoomById(String roomId) {
        repository.deleteById(roomId);
    }

    @SneakyThrows
    public Room patchRoom(String roomId, JsonPatch patch) {
        validator.validateExistsOrThrow(roomId);
        Room dbRoom = repository.findById(roomId)
                .orElseThrow(() -> new EntityNotFoundException("Couldn't find room with id " + roomId));

        JsonNode patchedRoomJson = patch.apply(mapper.valueToTree(dbRoom));
        Room patchedRoom = mapper.treeToValue(patchedRoomJson, Room.class);
        return replaceRoom(roomId, patchedRoom);
    }

    private Room replaceRoom(String roomId, Room newRoom) {
        newRoom.setRoomId(roomId);
        validator.validateReplaceThrow(roomId, newRoom);

        Room dbProduct = repository.findById(roomId)
                .orElseThrow(() -> new ValidationException("Couldn't find room with id " + roomId));
        copyProduct(newRoom, dbProduct);
        return repository.save(dbProduct);
    }

    private void copyProduct(Room newRoom, Room dbProduct) {
        dbProduct.setFloor(newRoom.getFloor());
        dbProduct.setHotelName(newRoom.getHotelName());
        dbProduct.setNumber(newRoom.getNumber());
        dbProduct.setRoomFacilitiesId(newRoom.getRoomFacilitiesId());
    }
}
