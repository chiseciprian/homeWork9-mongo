package ro.fasttrackit.homeWork9.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "room_facilities")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomFacilities {
    @Id
    private String roomFacilitiesId;

    private boolean hasTv;
    private boolean hasDoubleBed;
}
