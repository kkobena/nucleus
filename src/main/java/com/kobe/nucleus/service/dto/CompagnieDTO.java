package com.kobe.nucleus.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.kobe.nucleus.domain.enumeration.Status;

/**
 * A DTO for the {@link com.kobe.nucleus.domain.Compagnie} entity.
 */
public class CompagnieDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String libelle;

    @NotNull
    private Status status;

    
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CompagnieDTO)) {
            return false;
        }

        return id != null && id.equals(((CompagnieDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CompagnieDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
