package aruiz.portfolio.DiningReviewApi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import aruiz.portfolio.DiningReviewApi.model.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Optional<Restaurant> findRestaurantsByNameAndZipCode(String name, String zipCode);

    List<Restaurant> findRestaurantsByZipCodeAndPeanutScoreNotNullOrderByPeanutScore(String zipcode);

    List<Restaurant> findRestaurantsByZipCodeAndDairyScoreNotNullOrderByDairyScore(String zipcode);

    List<Restaurant> findRestaurantsByZipCodeAndEggScoreNotNullOrderByEggScore(String zipcode);
}
