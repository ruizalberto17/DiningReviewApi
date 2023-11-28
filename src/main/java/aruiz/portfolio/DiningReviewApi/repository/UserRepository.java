package aruiz.portfolio.DiningReviewApi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import aruiz.portfolio.DiningReviewApi.model.DiningUser;

public interface UserRepository extends CrudRepository<DiningUser, Long> {
    Optional<DiningUser> findUserByDisplayName(String displayName);
}
