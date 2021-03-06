package com.kobe.nucleus.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.kobe.nucleus.domain.Client;
import com.kobe.nucleus.domain.Compagnie;
import com.kobe.nucleus.domain.Remise;
import com.kobe.nucleus.domain.enumeration.CategorieAssurance;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Client} entity.
 */
public class ClientDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private LocalDate createdAt;

	private LocalDate updatedAt;

	private Status status;

	@NotNull
	private String firstName;
	@NotNull
	private String lastName;

	private String sexe;

	private LocalDate datNaiss;

	private String mobile;
	private String numMaticule;
	private String mail;
	private String fullName;
	private List<CompteClientDTO> compteClients = new ArrayList<>();
	private List<AyantDroitDTO> ayantDroits = new ArrayList<>();

	private CompteClientDTO compteClient;

	public String getNumMaticule() {
		return numMaticule;
	}

	public String getFullName() {
		return fullName;
	}

	public ClientDTO fullName(String fullName) {
		this.fullName = fullName;
		return this;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setNumMaticule(String numMaticule) {
		this.numMaticule = numMaticule;
	}

	public List<CompteClientDTO> getCompteClients() {
		return compteClients;
	}

	public void setCompteClients(List<CompteClientDTO> compteClients) {
		this.compteClients = compteClients;
	}

	public ClientDTO compteClients(List<CompteClientDTO> compteClients) {
		this.compteClients = compteClients;
		return this;
	}

	private Long compagnieId;

	private String compagnieLibelle;

	private Long remiseId;

	private String remiseValeur;
	private int encours;

	public List<AyantDroitDTO> getAyantDroits() {
		return ayantDroits;
	}

	public void setAyantDroits(List<AyantDroitDTO> ayantDroits) {
		this.ayantDroits = ayantDroits;
	}

	public ClientDTO ayantDroits(List<AyantDroitDTO> ayantDroits) {
		this.ayantDroits = ayantDroits;
		return this;
	}

	public int getEncours() {
		return encours;
	}

	public ClientDTO encours(int encours) {
		this.encours = encours;
		return this;
	}

	public void setEncours(int encours) {
		this.encours = encours;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public LocalDate getDatNaiss() {
		return datNaiss;
	}

	public void setDatNaiss(LocalDate datNaiss) {
		this.datNaiss = datNaiss;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Long getCompagnieId() {
		return compagnieId;
	}

	public void setCompagnieId(Long compagnieId) {
		this.compagnieId = compagnieId;
	}

	public String getCompagnieLibelle() {
		return compagnieLibelle;
	}

	public void setCompagnieLibelle(String compagnieLibelle) {
		this.compagnieLibelle = compagnieLibelle;
	}

	public Long getRemiseId() {
		return remiseId;
	}

	public void setRemiseId(Long remiseId) {
		this.remiseId = remiseId;
	}

	public String getRemiseValeur() {
		return remiseValeur;
	}

	public ClientDTO() {

	}

	public void setRemiseValeur(String remiseValeur) {
		this.remiseValeur = remiseValeur;
	}

	public ClientDTO(Client client) {
		super();
		this.id = client.getId();
		this.createdAt = client.getCreatedAt();
		this.updatedAt = client.getUpdatedAt();
		this.status = client.getStatus();
		this.firstName = client.getFirstName();
		this.lastName = client.getLastName();
		this.mail=client.getMail();
		this.mobile=client.getMobile();
		this.sexe = client.getSexe();
		this.datNaiss = client.getDatNaiss();
		this.fullName = client.getFirstName() + " " + client.getLastName();
		this.compteClients = client.getCompteClients().stream().sorted(Comparator.comparing(c -> c.getCategorie()))
				.map(e -> new CompteClientDTO(e)).collect(Collectors.toList());
		this.compteClients.stream().filter(a -> a.getCategorie() == CategorieAssurance.RO).findFirst().ifPresent(e -> {
			this.numMaticule = e.getNumMaticule();
			this.compteClient=e;
		});
		;
		Compagnie compagnie = client.getCompagnie();
		if (compagnie != null) {
			this.compagnieId = compagnie.getId();
			this.compagnieLibelle = compagnie.getLibelle();
		}
		Remise remise = client.getRemise();
		if (remise != null) {
			this.remiseId = remise.getId();
			this.remiseValeur = remise.getValeur();
		}
		this.ayantDroits = client.getAyantDroits().stream().map(e -> new AyantDroitDTO(e)).collect(Collectors.toList());
		this.encours = this.compteClients.stream().map(e -> e.getEncours()).reduce(0, Integer::sum);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ClientDTO clientDTO = (ClientDTO) o;
		if (clientDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), clientDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "ClientDTO{" + "id=" + getId() + ", createdAt='" + getCreatedAt() + "'" + ", updatedAt='"
				+ getUpdatedAt() + "'" + ", status='" + getStatus() + "'" + ", firstName='" + getFirstName() + "'"
				+ ", lastName='" + getLastName() + "'" + ", sexe='" + getSexe() + "'" + ", datNaiss='" + getDatNaiss()
				+ "'" + ", mobile='" + getMobile() + "'" + ", mail='" + getMail() + "'" + ", compagnieId="
				+ getCompagnieId() + ", compagnieLibelle='" + getCompagnieLibelle() + "'" + ", remiseId="
				+ getRemiseId() + ", remiseValeur='" + getRemiseValeur() + "'" + "}";
	}

	public CompteClientDTO getCompteClient() {
		return compteClient;
	}

	public void setCompteClient(CompteClientDTO compteClient) {
		this.compteClient = compteClient;
	}

	public ClientDTO compteClient(CompteClientDTO compteClient) {
		this.compteClient = compteClient;
		return this;
	}

}
