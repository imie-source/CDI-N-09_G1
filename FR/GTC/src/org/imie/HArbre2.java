package org.imie;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Competence;
import model.Personne;
//import model.arbre.Branche;

//import org.imie.service.ServiceGestionCompJPA;
import org.imie.service.ServiceGestionCompJPALocal;

/**
 * Servlet implementation class TP3_Controller ????
 */
@WebServlet("/Arbre2/*")
public class HArbre2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	ServiceGestionCompJPALocal serviceGestionComp;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HArbre2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Do get Arbre2");
		// ///////////////////////////////////// update / modifie
				// if (request.getParameter("affiche") != null) {
				// fonctionne pour la modif intitulé
				// a valider pour la modif du parent ?
				// compId non nul !!!

				// on crée un modèle vide de type compétence
				Competence searchCompetence = new Competence();

				searchCompetence.setCompetence(null);

				// on met toutes les competences avec parent null dans foundCompetences
				List<Competence> foundCompetences = serviceGestionComp
						.rechercherCompetence(searchCompetence);

				// Affectation de la liste des enfants comme attributs
				serviceGestionComp.setChildCompetence(foundCompetences);
				// on a initialisé la liste de tous les enfants
				// on la passe en paramètre à la request
				Integer rootId = 0;
				rootId = serviceGestionComp.addRoot(foundCompetences);

				System.out.println("Construction modele");
				Competence searchRacine = new Competence();
				Competence root = new Competence();
				System.out.println("Construction modele avec id" + rootId);
				root.setCompId(rootId);

				root = serviceGestionComp.racineBase(root);
				searchRacine.setCompetence(root);

				System.out.println("LOOK FOR RACINE");
				
				List<Competence> foundRacines = serviceGestionComp
						.rechercherCompetence(searchRacine);
				// searchCompetence.addCompetence(serviceGestionComp.racine());

				System.out.println("Envoie requete");

				request.setAttribute("foundCompetences2",foundCompetences);
				serviceGestionComp.setChildCompetence(foundRacines);
				request.setAttribute("foundCompetences", foundRacines);
				
				request.setAttribute("node",foundCompetences);
	

		// loguedPerson passé en request
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		Personne loguedPerson = new Personne();
		loguedPerson = (Personne) httpServletRequest.getSession().getAttribute(
				"authentifiedPersonne");
		request.setAttribute("loguedPerson", loguedPerson);

		request.getRequestDispatcher("/WEB-INF/JArbre2.jsp").forward(request,
				response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Do post Arbre");

		// redirection vers DoGet
		response.sendRedirect("/GTC/Arbre2/");

	}
}
