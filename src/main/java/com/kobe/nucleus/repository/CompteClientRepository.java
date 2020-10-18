package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.CompteClient;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CompteClient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompteClientRepository extends JpaRepository<CompteClient, Long> {
}