package com.kobe.nucleus.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.FormProduit} entity.
 */
public class FormProduitDTO implements Serializable {

    private Long id;

   
    private String code;

   
    private String libelle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FormProduitDTO)) {
            return false;
        }

        return id != null && id.equals(((FormProduitDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FormProduitDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +

            "}";
    }
}
