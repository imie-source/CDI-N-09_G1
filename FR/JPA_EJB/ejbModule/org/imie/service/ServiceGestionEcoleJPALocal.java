package org.imie.service;

import java.util.List;

import javax.ejb.Local;
import javax.xml.rpc.ServiceException;

import model.Competence;
import model.Personne;
import model.Possede;
import model.Projet;
import model.Promotion;
import model.Role;
import model.Travaille;

// Commentaire JM
// besoin d'annotations documentations et commentaires explicatifs

@Local
public interface ServiceGestionEcoleJPALocal {
	public List<Personne> rechercherPersonne(Personne personne);

    public Personne insertPersonne(Personne personne);
    
    public void deletePersonne(Personne personne);
    
    public Personne updatePersonne(Personne personneToUpdate);
    
    public Promotion insertPromotion(Promotion promotion);
    
    public void deletePromotion(Promotion promotion);
    
    public List<Promotion> rechercherPromotion(Promotion promotion);
   
    public Promotion updatePromotion(Promotion promotionToUpdate);
    
    public void duplicatePersonne(Personne personneToDuplicate, Integer nbFois);
    
    public Personne verifierAuthPersonne(Personne personne) throws ServiceException;
    
    public List<Possede> rechercherPossede(Possede possede);
    public List<Projet> rechercherProjet(Projet prj);
    public List<Travaille> rechercherTravaille(Travaille trv);
    public List<Role> rechercherRole(Role role);
    public Projet insertProjet(Projet projet);
    public void deleteProjet(Projet projet);
    public Projet updateProjet(Projet projetToUpdate);

    // ajout methodes Competence JM
	public List<Competence> rechercherCompetence(Competence searchCompetences);
	public void deleteCompetence(Competence updatedCompetence);
	public void insertCompetence(Competence updatedCompetence);
	public Competence updateCompetence(Competence updatedCompetence);
	public void setChildCompetence(List<Competence> foundCompetences);
    	  
    
}
