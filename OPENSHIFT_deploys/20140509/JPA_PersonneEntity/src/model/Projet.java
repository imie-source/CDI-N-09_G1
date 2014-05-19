package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the projet database table.
 * 
 */
@Entity
public class Projet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="proj_id")
	private Integer projId;

	@Column(name="proj_avancement")
	private String projAvancement;

	@Temporal(TemporalType.DATE)
	@Column(name="proj_datedebut")
	private Date projDatedebut;

	@Temporal(TemporalType.DATE)
	@Column(name="proj_datedefin")
	private Date projDatedefin;

	@Column(name="proj_description")
	private String projDescription;

	@Column(name="proj_nom")
	private String projNom;

	@Column(name="proj_wiki_cdp")
	private String projWikiCdp;

	@Column(name="proj_wiki_membre")
	private String projWikiMembre;

	//bi-directional many-to-one association to Candidature
	@OneToMany(mappedBy="projet")
	private List<Candidature> candidatures;

	//bi-directional many-to-one association to Personne
	@ManyToOne
	@JoinColumn(name="pers_id")
	private Personne personne;

	//bi-directional many-to-one association to Travaille
	@OneToMany(mappedBy="projet")
	private List<Travaille> travailles;

	public Projet() {
	}

	public Integer getProjId() {
		return this.projId;
	}

	public void setProjId(Integer projId) {
		this.projId = projId;
	}

	public String getProjAvancement() {
		return this.projAvancement;
	}

	public void setProjAvancement(String projAvancement) {
		this.projAvancement = projAvancement;
	}

	public Date getProjDatedebut() {
		return this.projDatedebut;
	}

	public void setProjDatedebut(Date projDatedebut) {
		this.projDatedebut = projDatedebut;
	}

	public Date getProjDatedefin() {
		return this.projDatedefin;
	}

	public void setProjDatedefin(Date projDatedefin) {
		this.projDatedefin = projDatedefin;
	}

	public String getProjDescription() {
		return this.projDescription;
	}

	public void setProjDescription(String projDescription) {
		this.projDescription = projDescription;
	}

	public String getProjNom() {
		return this.projNom;
	}

	public void setProjNom(String projNom) {
		this.projNom = projNom;
	}

	public String getProjWikiCdp() {
		return this.projWikiCdp;
	}

	public void setProjWikiCdp(String projWikiCdp) {
		this.projWikiCdp = projWikiCdp;
	}

	public String getProjWikiMembre() {
		return this.projWikiMembre;
	}

	public void setProjWikiMembre(String projWikiMembre) {
		this.projWikiMembre = projWikiMembre;
	}

	public List<Candidature> getCandidatures() {
		return this.candidatures;
	}

	public void setCandidatures(List<Candidature> candidatures) {
		this.candidatures = candidatures;
	}

	public Candidature addCandidature(Candidature candidature) {
		getCandidatures().add(candidature);
		candidature.setProjet(this);

		return candidature;
	}

	public Candidature removeCandidature(Candidature candidature) {
		getCandidatures().remove(candidature);
		candidature.setProjet(null);

		return candidature;
	}

	public Personne getPersonne() {
		return this.personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public List<Travaille> getTravailles() {
		return this.travailles;
	}

	public void setTravailles(List<Travaille> travailles) {
		this.travailles = travailles;
	}

	public Travaille addTravaille(Travaille travaille) {
		getTravailles().add(travaille);
		travaille.setProjet(this);

		return travaille;
	}

	public Travaille removeTravaille(Travaille travaille) {
		getTravailles().remove(travaille);
		travaille.setProjet(null);

		return travaille;
	}

}