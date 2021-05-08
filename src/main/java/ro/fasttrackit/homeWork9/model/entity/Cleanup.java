package ro.fasttrackit.homeWork9.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "cleanup")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cleanup {
    @Id
    private String cleanupId;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @NonNull
    private String roomId;
}
