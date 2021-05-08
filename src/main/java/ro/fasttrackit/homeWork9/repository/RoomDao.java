package ro.fasttrackit.homeWork9.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.homeWork9.model.RoomsFilters;
import ro.fasttrackit.homeWork9.model.entity.Room;
import ro.fasttrackit.homeWork9.model.entity.RoomFacilities;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Repository
@RequiredArgsConstructor
public class RoomDao {
    private final MongoTemplate mongo;

    public List<Room> getAll(RoomsFilters filters) {
        Criteria facilitiesCriteria = getFacilitiesCriteria(filters);
        Criteria roomCriteria = getRoomCriteria(filters);
        List<Room> rooms = getRooms(roomCriteria);
        List<RoomFacilities> roomFacilities = getRoomFacilities(facilitiesCriteria);
        return filterRooms(rooms, roomFacilities);
    }

    private List<Room> filterRooms(List<Room> rooms, List<RoomFacilities> roomFacilities) {
        return rooms.stream()
                .filter(room -> roomFacilities.stream()
                        .anyMatch(facilities -> facilities.getRoomFacilitiesId().equals(room.getRoomFacilitiesId()))
                ).collect(Collectors.toList());
    }

    private List<RoomFacilities> getRoomFacilities(Criteria facilitiesCriteria) {
        Query facilitiesQuery = new Query(facilitiesCriteria);
        List<RoomFacilities> roomFacilities = mongo.find(facilitiesQuery, RoomFacilities.class);
        return roomFacilities;
    }

    private List<Room> getRooms(Criteria roomCriteria) {
        Query roomQuery = new Query(roomCriteria);
        List<Room> rooms = mongo.find(roomQuery, Room.class);
        return rooms;
    }

    private Criteria getRoomCriteria(RoomsFilters filters) {
        Criteria roomCriteria = new Criteria();
        ofNullable(filters.getNumber())
                .ifPresent(number -> roomCriteria.and("number").is(number));
        ofNullable(filters.getFloor())
                .ifPresent(floor -> roomCriteria.and("floor").is(floor));
        ofNullable(filters.getHotel())
                .ifPresent(hotel -> roomCriteria.and("hotelName").is(hotel));
        return roomCriteria;
    }

    private Criteria getFacilitiesCriteria(RoomsFilters filters) {
        Criteria facilitiesCriteria = new Criteria();
        ofNullable(filters.getHasTv())
                .ifPresent(hasTv -> facilitiesCriteria.and("hasTv").is(hasTv));
        ofNullable(filters.getHasDoubleBed())
                .ifPresent(hasDoubleBed -> facilitiesCriteria.and("hasDoubleBed").is(hasDoubleBed));
        return facilitiesCriteria;
    }
}
