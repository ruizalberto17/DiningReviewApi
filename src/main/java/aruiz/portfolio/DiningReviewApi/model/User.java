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
public class User {
	@Id
	@GeneratedValue
    private Long id;
	private String displayName;

	private String city;
	private String state;
	private String zipcode;

	private Boolean peanutAllergy;
 	private Boolean eggAllergy;
 	private Boolean dairyAllergy;
}