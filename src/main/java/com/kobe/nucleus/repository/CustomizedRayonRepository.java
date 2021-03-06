package com.kobe.nucleus.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.kobe.nucleus.domain.Rayon;
import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.service.dto.RayonDTO;

@Repository
@Transactional
public class CustomizedRayonRepository implements CustomizedRayonService {
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public Page<RayonDTO> listRayonsByMagasinId(Long magasinId, String query, Pageable pageable) {
		long total = findAllCount(magasinId, query);
		List<RayonDTO> list = new ArrayList<>();
		if (total > 0) {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Rayon> cq = cb.createQuery(Rayon.class);
			Root<Rayon> root = cq.from(Rayon.class);
			cq.select(root);
			List<Predicate> predicates = produitPredicate(cb, root, magasinId, query);
			cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			TypedQuery<Rayon> q = em.createQuery(cq);
			list = q.getResultList().stream().map(o -> buildRayonDTOFromRayon(o)).collect(Collectors.toList());
		}
		return new PageImpl<>(list, pageable, total);
	}

	private long findAllCount(Long magasinId, String query) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Rayon> root = cq.from(Rayon.class);
		cq.select(cb.count(root));
		List<Predicate> predicates = produitPredicate(cb, root, magasinId, query);
		cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		TypedQuery<Long> q = em.createQuery(cq);
		Long v = q.getSingleResult();
		return v != null ? v : 0;

	}

	private List<Predicate> produitPredicate(CriteriaBuilder cb, Root<Rayon> root, Long magasinId, String query) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(query)) {
			String search = query.toUpperCase() + "%";
			predicates.add(
					cb.or(cb.like(cb.upper(root.get("libelle")), search), cb.like(cb.upper(root.get("code")), search)));

		}
		predicates.add(cb.equal(root.get("magasin").get("id"), magasinId));
		predicates.add(cb.equal(root.get("status"), Status.ACTIVE));
		return predicates;
	}

	@Override
	public RayonDTO save(RayonDTO dto) {
		return new RayonDTO(em.merge(buildRayonFromRayonDTO(dto)));
	}

	@Override
	public RayonDTO update(RayonDTO dto) {
		Rayon rayon = em.find(Rayon.class, dto.getId());
		return new RayonDTO(em.merge(buildRayonFromRayonDTO(dto, rayon)));
	}

}
