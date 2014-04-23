package org.imie;

import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Competence;
import model.Personne;

import org.imie.service.ServiceGestionEcoleJPALocal;

/**
 * Servlet implementation class TP3_Controller ????
 */
@WebServlet("/Competence/*")
public class HCompetence extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	ServiceGestionEcoleJPALocal serviceGestionEcole;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HCompetence() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("HCompetence Get"); // console verif

		// on crée un modèle vide de type compétence
		Competence searchCompetences = new Competence();
		// on met toutes les competences dans foundCompetences
		List<Competence> foundCompetences = serviceGestionEcole
				.rechercherCompetence(searchCompetences);

		// Affectation de la liste des enfants comme attributs
		serviceGestionEcole.setChildCompetence(foundCompetences);
		// on a initialisé la liste de tous les enfants
		request.setAttribute("foundCompetences", foundCompetences);

		// loguedPerson passé en request
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;	
		Personne loguedPerson = new Personne();
		loguedPerson = (Personne) httpServletRequest.getSession().getAttribute(
				"authentifiedPersonne");
		request.setAttribute("loguedPerson", loguedPerson);

		request.getRequestDispatcher("/WEB-INF/JCompetence.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("HCompetence Post");
		
		// on cree un modèle vide de competence
		Competence modelCompetence = new Competence();

		System.out.println("HCompetence Post attribution request");
		// valeurs passés en request
		String inputLibelComp = request.getParameter("inputLibelleComp");
		Integer compId = Integer.valueOf(request.getParameter("inputId"));
		modelCompetence.setCompIntitule(inputLibelComp);
		modelCompetence.setCompId(compId);
		// ///////////////////////////////////// update / modifie
		if (request.getParameter("update") != null) {
			System.out.println("HCompetence Post update");
			serviceGestionEcole.updateCompetence(modelCompetence);
			
		}
		
		// /////////////////////////////////////
		// ////////////////////////////////// delete update create
		if (request.getParameter("delete") != null) {
			System.out.println("HCompetence Post delete");
			if (request.getParameter("inputCompId") != null) {
				System.out.println("Dans IF HCompetence Post delete");
				Integer inputCompId = Integer.valueOf(request.getParameter("inputCompId"));
				System.out.println("CompId = "+inputCompId);
				try {
					Competence deletedCompetence = new Competence();
					deletedCompetence.setCompId(inputCompId);
					System.out.println("inputCompId : " + inputCompId);
					// serviceGestionEcole.deleteCompetence(updatedCompetence);
				} catch (NumberFormatException e) {
					// parametres non corrects : pas de suppression
				}
			}
		} else {
			Competence searchCompetences = new Competence();
			List<Competence> foundCompetences = serviceGestionEcole
					.rechercherCompetence(searchCompetences);
			request.setAttribute("foundCompetences", foundCompetences);
		}

		// redirection vers DoGet
		response.sendRedirect("/GTC/Competence/");
		
	}

}