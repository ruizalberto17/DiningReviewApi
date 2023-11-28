package aruiz.portfolio.DiningReviewApi.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import aruiz.portfolio.DiningReviewApi.model.DiningUser;
import aruiz.portfolio.DiningReviewApi.repository.UserRepository;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody DiningUser user) {
        validateUser(user);

        userRepository.save(user);
    }

    @GetMapping("/{displayName}")
    public DiningUser getUser(@PathVariable String displayName) {
        validateDisplayName(displayName);

        Optional<DiningUser> optionalExistingUser = userRepository.findUserByDisplayName(displayName);
        if (!optionalExistingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        DiningUser existingUser = optionalExistingUser.get();
        existingUser.setId(null);

        return existingUser;
    }

    @PutMapping("/{displayName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUserInfo(@PathVariable String displayName, @RequestBody DiningUser updatedUser) {
        validateDisplayName(displayName);

        Optional<DiningUser> optionalExistingUser = userRepository.findUserByDisplayName(displayName);
        if (optionalExistingUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        DiningUser existingUser = optionalExistingUser.get();

        copyUserInfoFrom(updatedUser, existingUser);
        userRepository.save(existingUser);
    }

    private void copyUserInfoFrom(DiningUser updatedUser, DiningUser existingUser) {
        if (ObjectUtils.isEmpty(updatedUser.getDisplayName())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (!ObjectUtils.isEmpty(updatedUser.getCity())) {
            existingUser.setCity(updatedUser.getCity());
        }

        if (!ObjectUtils.isEmpty(updatedUser.getState())) {
            existingUser.setState(updatedUser.getState());
        }

        if (!ObjectUtils.isEmpty(updatedUser.getZipCode())) {
            existingUser.setZipCode(updatedUser.getZipCode());
        }

        if (!ObjectUtils.isEmpty(updatedUser.getPeanutAllergy())) {
            existingUser.setPeanutAllergy(updatedUser.getPeanutAllergy());
        }

        if (!ObjectUtils.isEmpty(updatedUser.getDairyAllergy())) {
            existingUser.setDairyAllergy(updatedUser.getDairyAllergy());
        }

        if (!ObjectUtils.isEmpty(updatedUser.getEggAllergy())) {
            existingUser.setEggAllergy(updatedUser.getEggAllergy());
        }
    }

    private void validateUser(DiningUser user) {
        validateDisplayName(user.getDisplayName());

        Optional<DiningUser> existingUser = userRepository.findUserByDisplayName(user.getDisplayName());
        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    private void validateDisplayName(String displayName) {
        if (ObjectUtils.isEmpty(displayName)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
