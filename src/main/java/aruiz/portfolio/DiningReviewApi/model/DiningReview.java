package aruiz.portfolio.DiningReviewApi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class DiningReview {
    @Id
    @GeneratedValue
    private Long id;

    private String submittedBy;
    private Long restaurantId;
    private String review;

    private Integer peanutScore;
    private Integer dairyScore;
    private Integer eggScore;

    private ReviewStatus status;
}