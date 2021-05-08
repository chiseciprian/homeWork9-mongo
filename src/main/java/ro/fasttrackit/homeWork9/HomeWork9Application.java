package ro.fasttrackit.homeWork9;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ro.fasttrackit.homeWork9.model.entity.Cleanup;
import ro.fasttrackit.homeWork9.model.entity.Room;
import ro.fasttrackit.homeWork9.model.entity.RoomFacilities;
import ro.fasttrackit.homeWork9.repository.CleanupRepository;
import ro.fasttrackit.homeWork9.repository.RoomFacilitiesRepository;
import ro.fasttrackit.homeWork9.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class HomeWork9Application {

    public static void main(String[] args) {
        SpringApplication.run(HomeWork9Application.class, args);
    }

//    @Bean
//    CommandLineRunner startup(RoomRepository repo, RoomFacilitiesRepository roomFacilitiesRepository, CleanupRepository cleanupRepository) {
//        return args -> {
//
//            List<RoomFacilities> roomFacilities = roomFacilitiesRepository.saveAll(List.of(
//                    RoomFacilities.builder()
//                            .hasDoubleBed(true)
//                            .hasTv(true)
//                            .build(),
//                    RoomFacilities.builder()
//                            .hasDoubleBed(false)
//                            .hasTv(true)
//                            .build(),
//                    RoomFacilities.builder()
//                            .hasDoubleBed(false)
//                            .hasTv(false)
//                            .build()
//            ));
//
//
//            List<Room> rooms = repo.saveAll(List.of(
//                    Room.builder()
//                            .number("32A")
//                            .floor(1)
//                            .hotelName("Spring")
//                            .roomFacilitiesId(roomFacilities.get(0).getRoomFacilitiesId())
//                            .build(),
//                    Room.builder()
//                            .number("33C")
//                            .floor(4)
//                            .hotelName("Wing")
//                            .roomFacilitiesId(roomFacilities.get(1).getRoomFacilitiesId())
//                            .build(),
//                    Room.builder()
//                            .number("33C")
//                            .floor(5)
//                            .hotelName("Lido")
//                            .roomFacilitiesId(roomFacilities.get(1).getRoomFacilitiesId())
//                            .build()
//            ));
//
//            cleanupRepository.saveAll(List.of(
//                    Cleanup.builder()
//                            .date(LocalDate.now())
//                            .roomId(rooms.get(0).getRoomId())
//                            .build(),
//                    Cleanup.builder()
//                            .date(LocalDate.now())
//                            .roomId(rooms.get(1).getRoomId())
//                            .build()
//            ));
//        };
//    }

}
