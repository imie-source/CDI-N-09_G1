package org.imie.service;

import java.util.List;

import javax.ejb.Local;

import model.Competence;
import model.Possede;
import model.PropositionComp;
//import model.Possede;
import model.arbre.Branche;

@Local
public interface ServiceGestionCompJPALocal {

    // ajout methodes Competence JM
	public List<Competence> rechercherCompetence(Competence searchCompetences);
	
	public void deleteCompetence(Competence updatedCompetence);
	
	//public void createCompetence(Competence modelCompetence); = insert
	public Integer insertCompetence(Competence updatedCompetence);
	
	public void updateCompetence(Competence updatedCompetence);
	
	public void setChildCompetence(List<Competence> foundCompetences);

	public List<PropositionComp> rechercherPropComp(PropositionComp prop);

	//public void movedCompetence(Competence movedComp);

	//void movedCompetence(Competence movedComp, Competence father);
	public void movedCompetence(Competence movedComp);

	public List<Possede> rechercherPossedeC(Possede possede);

	public Integer addRoot(List<Competence> competences);

	public Competence racine();

	public List<Competence> rechercherRacines();

	public Competence racineBase(Competence root);

	public List<Branche> constructionArbre(List<Competence> competencesRacine,
			Integer nivp);

	public Integer tailleArbre(List<Branche> branches);

	public Integer[] tailleBranche(List<Branche> branches);

	public String[][] tableauArbre(List<Branche> branches, Integer[] tailleB);

	public int tabMax(List<Branche> branches);
	
//	void initRoot(); //???
//	public Boolean addRoot();
	
	//public void updatePropComp(PropositionComp propitToUpdate);
	
	//public void updatePossede(Possede relToUpdate);

}
