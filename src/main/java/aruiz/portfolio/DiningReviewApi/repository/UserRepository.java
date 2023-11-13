package aruiz.portfolio.DiningReviewApi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import aruiz.portfolio.DiningReviewApi.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByDisplayName(String displayName);
}
