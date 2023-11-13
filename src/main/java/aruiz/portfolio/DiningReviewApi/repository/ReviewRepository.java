package aruiz.portfolio.DiningReviewApi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import aruiz.portfolio.DiningReviewApi.model.DiningReview;
import aruiz.portfolio.DiningReviewApi.model.ReviewStatus;

public interface ReviewRepository extends CrudRepository<DiningReview, Long> {
    List<DiningReview> findReviewsByRestaurantIdAndStatus(Long restaurantId, ReviewStatus reviewStatus);

    List<DiningReview> findReviewsByStatus(ReviewStatus reviewStatus);
}
