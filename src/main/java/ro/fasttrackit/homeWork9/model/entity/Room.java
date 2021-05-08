package ro.fasttrackit.homeWork9.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "rooms")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
    @Id
    private String roomId;

    private String number;
    private int floor;
    private String hotelName;

    private String roomFacilitiesId;
}
