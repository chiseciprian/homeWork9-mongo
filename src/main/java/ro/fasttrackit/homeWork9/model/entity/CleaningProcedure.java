package ro.fasttrackit.homeWork9.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cleaning_procedure")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CleaningProcedure {
    @Id
    private String cleaningProcedureId;

    private String name;
    private Integer outcome;

    @NonNull
    private String cleanupId;
}
