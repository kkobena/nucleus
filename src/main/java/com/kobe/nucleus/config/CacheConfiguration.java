package com.kobe.nucleus.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
	private GitProperties gitProperties;
	private BuildProperties buildProperties;
	private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

	public CacheConfiguration(JHipsterProperties jHipsterProperties) {
		JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

		jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(CacheConfigurationBuilder
				.newCacheConfigurationBuilder(Object.class, Object.class,
						ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
				.withExpiry(
						ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
				.build());
	}

	@Bean
	public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
		return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
	}

	@Bean
	public JCacheManagerCustomizer cacheManagerCustomizer() {
		return cm -> {
			createCache(cm, com.kobe.nucleus.repository.UserRepository.USERS_BY_LOGIN_CACHE);
			createCache(cm, com.kobe.nucleus.repository.UserRepository.USERS_BY_EMAIL_CACHE);
			createCache(cm, com.kobe.nucleus.domain.User.class.getName());
			createCache(cm, com.kobe.nucleus.domain.Authority.class.getName());
			createCache(cm, com.kobe.nucleus.domain.User.class.getName() + ".authorities");
			createCache(cm, com.kobe.nucleus.domain.PersistentToken.class.getName());
			createCache(cm, com.kobe.nucleus.domain.User.class.getName() + ".persistentTokens");
			createCache(cm, com.kobe.nucleus.domain.Magasin.class.getName());

			createCache(cm, com.kobe.nucleus.domain.Magasin.class.getName() + ".rayons");
			createCache(cm, com.kobe.nucleus.domain.Magasin.class.getName() + ".utilisateurs");
			createCache(cm, com.kobe.nucleus.domain.Magasin.class.getName() + ".magasins");
			createCache(cm, com.kobe.nucleus.domain.Rayon.class.getName());
			
			createCache(cm, com.kobe.nucleus.domain.GroupeFournisseur.class.getName());
			createCache(cm, com.kobe.nucleus.domain.Fournisseur.class.getName());
			createCache(cm, com.kobe.nucleus.domain.DetailsInventaire.class.getName());
			createCache(cm, com.kobe.nucleus.domain.Ajustement.class.getName());
			createCache(cm, com.kobe.nucleus.domain.DetailsAjustement.class.getName());
			createCache(cm, com.kobe.nucleus.domain.FormProduit.class.getName());
		
			createCache(cm, com.kobe.nucleus.domain.GammeProduit.class.getName());
			

			createCache(cm, com.kobe.nucleus.domain.FamilleProduit.class.getName());
		
			createCache(cm, com.kobe.nucleus.domain.CategorieProduit.class.getName());

			createCache(cm, com.kobe.nucleus.domain.Tva.class.getName());
			
			createCache(cm, com.kobe.nucleus.domain.Remise.class.getName());
	

			createCache(cm, com.kobe.nucleus.domain.CodeGestion.class.getName());
	
			createCache(cm, com.kobe.nucleus.domain.ModelFacture.class.getName());
		
			createCache(cm, com.kobe.nucleus.domain.CategorieAyantDroit.class.getName());
			
			createCache(cm, com.kobe.nucleus.domain.Client.class.getName());
		
			createCache(cm, com.kobe.nucleus.domain.Client.class.getName() + ".ayantDroits");
			createCache(cm, com.kobe.nucleus.domain.Client.class.getName() + ".compteClients");
			createCache(cm, com.kobe.nucleus.domain.AyantDroit.class.getName());
		
		
			createCache(cm, com.kobe.nucleus.domain.Risque.class.getName());
			
			createCache(cm, com.kobe.nucleus.domain.Compagnie.class.getName());
			createCache(cm, com.kobe.nucleus.domain.Compagnie.class.getName() + ".clients");
			createCache(cm, com.kobe.nucleus.domain.Laboratoire.class.getName());
			createCache(cm, com.kobe.nucleus.domain.Laboratoire.class.getName() + ".produits");
			createCache(cm, com.kobe.nucleus.domain.TypeRisque.class.getName());
			createCache(cm, com.kobe.nucleus.domain.TypeRisque.class.getName() + ".risques");
			createCache(cm, com.kobe.nucleus.domain.TypeEtiquette.class.getName());

			createCache(cm, com.kobe.nucleus.domain.CompteClient.class.getName());
	
			createCache(cm, com.kobe.nucleus.domain.Motif.class.getName());
		

			createCache(cm, com.kobe.nucleus.domain.GroupeTierspayant.class.getName());
			createCache(cm, com.kobe.nucleus.domain.GroupeTierspayant.class.getName() + ".factures");
			createCache(cm, com.kobe.nucleus.domain.GroupeTierspayant.class.getName() + ".tierspayants");
			createCache(cm, com.kobe.nucleus.domain.Tierspayant.class.getName());
			createCache(cm, com.kobe.nucleus.domain.Tierspayant.class.getName() + ".factures");
	
			createCache(cm, com.kobe.nucleus.domain.Permission.class.getName());
			createCache(cm, com.kobe.nucleus.domain.Paiement.class.getName());
			createCache(cm, com.kobe.nucleus.domain.Paiement.class.getName() + ".paiementItems");
			createCache(cm, com.kobe.nucleus.domain.StockReport.class.getName());
			createCache(cm, com.kobe.nucleus.domain.PaiementItem.class.getName());
			createCache(cm, com.kobe.nucleus.domain.Etiquette.class.getName());
			createCache(cm, com.kobe.nucleus.domain.Menu.class.getName());
			createCache(cm, com.kobe.nucleus.domain.Menu.class.getName() + ".menus");
			createCache(cm, com.kobe.nucleus.domain.Menu.class.getName() + ".permissions");
			
			createCache(cm, com.kobe.nucleus.domain.ModePaiement.class.getName());
		
			createCache(cm, com.kobe.nucleus.domain.TypeMvtCaisse.class.getName());
			createCache(cm, com.kobe.nucleus.domain.Banque.class.getName());
			createCache(cm, com.kobe.nucleus.domain.Banque.class.getName() + ".paiements");
			createCache(cm, com.kobe.nucleus.domain.MvtsProduit.class.getName());

		};
	}

	private void createCache(javax.cache.CacheManager cm, String cacheName) {
		javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
		if (cache == null) {
			cm.createCache(cacheName, jcacheConfiguration);
		}
	}

	@Autowired(required = false)
	public void setGitProperties(GitProperties gitProperties) {
		this.gitProperties = gitProperties;
	}

	@Autowired(required = false)
	public void setBuildProperties(BuildProperties buildProperties) {
		this.buildProperties = buildProperties;
	}

	@Bean
	public KeyGenerator keyGenerator() {
		return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
	}
}
