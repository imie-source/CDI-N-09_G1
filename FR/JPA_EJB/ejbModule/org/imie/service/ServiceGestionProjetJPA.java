package org.imie.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.Personne;
import model.Projet;
//import javax.persistence.JoinColumn;
//import model.Actionanotifier;

/**
 * Session Bean implementation class ServiceGestionProjetJPA
 */
@Stateless(mappedName = "ServiceGestionProjet")
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ServiceGestionProjetJPA implements ServiceGestionProjetJPARemote,
		ServiceGestionProjetJPALocal {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ServiceGestionProjetJPA() {
		// TODO Auto-generated constructor stub
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Projet> rechercherProjet(Projet prj) {
		CriteriaBuilder qb = entityManager.getCriteriaBuilder();

		CriteriaQuery<Projet> query = qb.createQuery(Projet.class);
		Root<Projet> prjRoot = query.from(Projet.class);

		List<Predicate> criteria = new ArrayList<Predicate>();
		if (prj.getChefDeProjet() != null) {
			criteria.add(qb.equal(prjRoot.get("chefDeProjet"), prj.getChefDeProjet()));
		}
		if (prj.getProjId() != null) {
			criteria.add(qb.equal(prjRoot.get("projId"), prj.getProjId()));
		}
		if (prj.getProjNom() != null) {
			criteria.add(qb.equal(prjRoot.<String> get("ProjNom"),
					prj.getProjNom()));
		}

		query.where(criteria.toArray(new Predicate[] {}));
		List<Projet> result = entityManager.createQuery(query).getResultList();
		//actualisation des infos liées aux personnes participant aux projets
		for(Projet projet : result){
			projet.getMembres().size();
		}
		return result;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Projet insertProjet(Projet projet) {
		Projet projet2 = new Projet();
		projet2.setProjNom(projet.getProjNom());
		projet2.setProjDatedebut(projet.getProjDatedebut());
		projet2.setProjDatedefin(projet.getProjDatedefin());
		projet2.setProjDescription(projet.getProjDescription());
		projet2.setProjWikiCdp(projet.getProjWikiCdp());
		projet2.setProjWikiMembre(projet.getProjWikiMembre());
		projet2.setProjAvancement(projet.getProjAvancement());
		projet2.setChefDeProjet(projet.getChefDeProjet());
		//Projet: inscription du chef de projet dans la liste des membres
		//Personne: prevoir mise a jour de liste des projets auxquels il participe (bi-directionnalite)
		if(projet.getChefDeProjet() != null){
			Set<Personne> membres = new HashSet<Personne>();
			membres.add(projet.getChefDeProjet());
			projet2.setMembres(membres);
		}

		//passage du projet en base
		entityManager.persist(projet2);
		return projet2;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteProjet(Projet projet) {
		projet = entityManager.find(Projet.class, projet.getProjId());
		entityManager.remove(projet);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Projet updateProjet(Projet projetToUpdate) {
		return entityManager.merge(projetToUpdate);
	}

}
