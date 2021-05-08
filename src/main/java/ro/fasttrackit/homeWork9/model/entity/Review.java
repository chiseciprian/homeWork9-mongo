package ro.fasttrackit.homeWork9.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "review")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
    @Id
    private String reviewId;

    private String message;
    private int rating;
    private String tourist;

    @NonNull
    private String roomId;
}
