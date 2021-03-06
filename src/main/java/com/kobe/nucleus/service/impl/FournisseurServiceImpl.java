package com.kobe.nucleus.service.impl;

import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.service.FournisseurService;
import com.kobe.nucleus.domain.Fournisseur;
import com.kobe.nucleus.domain.GroupeFournisseur;
import com.kobe.nucleus.domain.constants.EntityConstant;
import com.kobe.nucleus.repository.FournisseurRepository;
import com.kobe.nucleus.repository.GroupeFournisseurRepository;
import com.kobe.nucleus.repository.util.Condition;
import com.kobe.nucleus.repository.util.SpecificationBuilder;
import com.kobe.nucleus.service.dto.FournisseurDTO;
import com.kobe.nucleus.service.mapper.FournisseurMapper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Fournisseur}.
 */
@Service
@Transactional
public class FournisseurServiceImpl implements FournisseurService {

	private final Logger log = LoggerFactory.getLogger(FournisseurServiceImpl.class);

	private final FournisseurRepository fournisseurRepository;

	private final FournisseurMapper fournisseurMapper;
	private final GroupeFournisseurRepository groupeFournisseurRepository;

	public FournisseurServiceImpl(FournisseurRepository fournisseurRepository, FournisseurMapper fournisseurMapper,
			GroupeFournisseurRepository groupeFournisseurRepository) {
		this.fournisseurRepository = fournisseurRepository;
		this.fournisseurMapper = fournisseurMapper;
		this.groupeFournisseurRepository = groupeFournisseurRepository;
	}

	/**
	 * Save a fournisseur.
	 *
	 * @param fournisseurDTO the entity to save.
	 * @return the persisted entity.
	 */
	@Override
	public FournisseurDTO save(FournisseurDTO fournisseurDTO) {
		log.debug("Request to save Fournisseur : {}", fournisseurDTO);
		Fournisseur fournisseur = fournisseurMapper.toEntity(fournisseurDTO);
		fournisseur.setStatus(Status.ACTIVE);
		fournisseur = fournisseurRepository.save(fournisseur);
		return fournisseurMapper.toDto(fournisseur);
	}

	/**
	 * Get all the fournisseurs.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<FournisseurDTO> findAll(String search ,Pageable pageable) {
		  Pageable page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
	                Sort.by(Sort.Direction.ASC, "libelle"));
	            if(!StringUtils.isEmpty(search)){
	                SpecificationBuilder<Fournisseur> builder=new SpecificationBuilder<>();
	                Specification<Fournisseur> spec = builder
	                    .with(new String[]{"libelle"}, search+"%", Condition.OperationType.LIKE, Condition.LogicalOperatorType.END)
	                    .build();
	                return  fournisseurRepository.findAll(spec, page).map(fournisseurMapper::toDto);
	            }
		return fournisseurRepository.findAll(page).map(fournisseurMapper::toDto);
	}

	/**
	 * Get one fournisseur by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<FournisseurDTO> findOne(Long id) {
		log.debug("Request to get Fournisseur : {}", id);
		return fournisseurRepository.findById(id).map(fournisseurMapper::toDto);
	}

	/**
	 * Delete the fournisseur by id.
	 *
	 * @param id the id of the entity.
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Fournisseur : {}", id);

		fournisseurRepository.deleteById(id);
	}

	@Override
	public void importation(InputStream inputStream) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().parse(br);
			records.forEach(record -> {
				Fournisseur fournisseur = new Fournisseur();
				fournisseur.setStatus(Status.ACTIVE);
				fournisseur.setLibelle(record.get(0));
				fournisseur.setCode(record.get(1));
				fournisseur.setAddressePostal(record.get(3));
				fournisseur.setMobile(record.get(4));
				fournisseur.setPhone(record.get(5));
				fournisseur.setSite(record.get(6));
				fournisseur.setIdentifiantRepartiteur(record.get(7));
				log.debug("GroupeFournisseur LIBELLE  : {}", record.get(2));
				if (!StringUtils.isEmpty(record.get(2))) {
					Optional<GroupeFournisseur> op = groupeFournisseurRepository.findOneByLibelle(record.get(2));
					log.debug("Optional  : {}", op);
					if (op.isPresent()) {
						fournisseur.setGroupeFournisseur(op.get());

					} else {
						groupeFournisseurRepository.findOneByLibelle(EntityConstant.AUTRES_FOURNISSEURS)
								.ifPresent(g -> {
									fournisseur.setGroupeFournisseur(g);
								});

					}

				} else {
					groupeFournisseurRepository.findOneByLibelle(EntityConstant.AUTRES_FOURNISSEURS).ifPresent(g -> {
						fournisseur.setGroupeFournisseur(g);
					});
				}

				fournisseurRepository.save(fournisseur);
			});
		} catch (IOException e) {
			log.debug("importation : {}", e);
		}

	}
}
