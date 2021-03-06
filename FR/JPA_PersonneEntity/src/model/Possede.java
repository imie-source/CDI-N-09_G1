package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the possede database table.
 * 
 */
@Entity
public class Possede implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="poss_id")
	private Integer possId;

	@Column(name="comp_niveau")
	private Integer compNiveau;

	// many-to-one : association simple vers une autre classe persistante 
	// modèle relationnel association many-to-one (au sens propre, référence à un objet). 
	// uni-directional many-to-one association to Competence
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="comp_id")
	private Competence competence;
	// classe Possede possède un champ Competence, 
	//sans que Competence n'ait accès à la liste de ses Posseseurs ??

	//bi-directional many-to-one association to Personne
	@ManyToOne
	@JoinColumn(name="pers_id")
	private Personne personne;

	public Possede() {
	}

	public Integer getPossId() {
		return this.possId;
	}

	public void setPossId(Integer possId) {
		this.possId = possId;
	}

	public Integer getCompNiveau() {
		return this.compNiveau;
	}

	public void setCompNiveau(Integer compNiveau) {
		this.compNiveau = compNiveau;
	}

	public Competence getCompetence() {
		return this.competence;
	}

	public void setCompetence(Competence competence) {
		this.competence = competence;
	}

	public Personne getPersonne() {
		return this.personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

}