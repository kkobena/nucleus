package com.kobe.nucleus.service.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.kobe.nucleus.config.Constants;
import com.kobe.nucleus.domain.Authority;
import com.kobe.nucleus.domain.Magasin;
import com.kobe.nucleus.domain.User;

/**
 * A DTO representing a user, with his authorities.
 */
public class UserDTO {

	private Long id;

	@NotBlank
	@Pattern(regexp = Constants.LOGIN_REGEX)
	@Size(min = 1, max = 50)
	private String login;
	private String firstName;
	private String lastName;

	@Email
	@Size(min = 5, max = 254)
	private String email;
	private String imageUrl;

	private boolean activated = false;

	@Size(min = 2, max = 10)
	private String langKey;

	private String createdBy;

	private Instant createdDate;

	private String lastModifiedBy;

	private Instant lastModifiedDate;
	private String role;
	private List<String> authorities=new ArrayList<>();
	private Long magasinId;
	private String magasinNom;
	private String fullName;


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserDTO() {

	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.login = user.getLogin();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.activated = user.getActivated();
		this.imageUrl = user.getImageUrl();
		this.langKey = user.getLangKey();
		this.createdBy = user.getCreatedBy();
		this.createdDate = user.getCreatedDate();
		this.lastModifiedBy = user.getLastModifiedBy();
		this.lastModifiedDate = user.getLastModifiedDate();
		this.authorities = user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toList());
		if(!authorities.isEmpty()) {
			this.role=authorities.get(0);
		}
		
		Magasin magasin = user.getMagasin();
		if (magasin != null) {
			this.magasinId = magasin.getId();
			this.magasinNom = magasin.getNomCourt();
		}
		this.fullName= user.getFirstName()+" "+user.getLastName();
	}

	public Long getMagasinId() {
		return magasinId;
	}

	public void setMagasinId(Long magasinId) {
		this.magasinId = magasinId;
	}

	public String getMagasinNom() {
		return magasinNom;
	}

	public void setMagasinNom(String magasinNom) {
		this.magasinNom = magasinNom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getLangKey() {
		return langKey;
	}

	public void setLangKey(String langKey) {
		this.langKey = langKey;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Instant getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Instant lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	


	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "UserDTO{" + "login='" + login + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName
				+ '\'' + ", email='" + email + '\'' + ", imageUrl='" + imageUrl + '\'' + ", activated=" + activated
				+ ", langKey='" + langKey + '\'' + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", lastModifiedBy='" + lastModifiedBy + '\'' + ", lastModifiedDate=" + lastModifiedDate
				+ ", authorities=" + authorities + "}";
	}
}
