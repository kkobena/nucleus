package com.kobe.nucleus.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Fournisseur} entity.
 */
public class FournisseurDTO implements Serializable {
    private Long id;
    @NotNull
    private String libelle;
    private Status status;

    private String addresspostale;

    private String numFaxe;

    private String addressePostal;

    private String phone;

    private String mobile;

    private String site;

    @NotNull
    @Size(max = 70)
    private String code;


    private Long groupeFournisseurId;

    private String groupeFournisseurLibelle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAddresspostale() {
        return addresspostale;
    }

    public void setAddresspostale(String addresspostale) {
        this.addresspostale = addresspostale;
    }

    public String getNumFaxe() {
        return numFaxe;
    }

    public void setNumFaxe(String numFaxe) {
        this.numFaxe = numFaxe;
    }

    public String getAddressePostal() {
        return addressePostal;
    }

    public void setAddressePostal(String addressePostal) {
        this.addressePostal = addressePostal;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getGroupeFournisseurId() {
        return groupeFournisseurId;
    }

    public void setGroupeFournisseurId(Long groupeFournisseurId) {
        this.groupeFournisseurId = groupeFournisseurId;
    }

    public String getGroupeFournisseurLibelle() {
        return groupeFournisseurLibelle;
    }

    public void setGroupeFournisseurLibelle(String groupeFournisseurLibelle) {
        this.groupeFournisseurLibelle = groupeFournisseurLibelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FournisseurDTO)) {
            return false;
        }

        return id != null && id.equals(((FournisseurDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FournisseurDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", status='" + getStatus() + "'" +
            ", addresspostale='" + getAddresspostale() + "'" +
            ", numFaxe='" + getNumFaxe() + "'" +
            ", addressePostal='" + getAddressePostal() + "'" +
            ", phone='" + getPhone() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", site='" + getSite() + "'" +
            ", code='" + getCode() + "'" +
            ", groupeFournisseurId=" + getGroupeFournisseurId() +
            ", groupeFournisseurLibelle='" + getGroupeFournisseurLibelle() + "'" +
            "}";
    }
}