package org.imie;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Personne;
import model.Projet;
import model.Promotion;

import org.imie.service.ServiceGestionEcoleJPALocal;

/**
 * Servlet implementation class TP3_Controller
 */
@WebServlet("/HProjet/*")
public class HCompetence extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB ServiceGestionEcoleJPALocal serviceGestionEcole;
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
		System.out.println("HProjet Get");

		// on met tous les projets dans foundProjets
		Projet searchProjet = new Projet();
		List<Projet> foundProjets = serviceGestionEcole.rechercherProjet(searchProjet);
		request.setAttribute("foundProjets", foundProjets);
		
		// on passe tous les profils en request pour la liste de la popup modif projet
		Personne searchPersonne = new Personne();
		List<Personne> foundPersonnes = serviceGestionEcole
				.rechercherPersonne(searchPersonne);
		request.setAttribute("foundPersonnes", foundPersonnes);
		
		// loguedPerson passé en request
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		Personne loguedPerson = new Personne();
		loguedPerson=(Personne) httpServletRequest.getSession().getAttribute("authentifiedPersonne");
		request.setAttribute("loguedPerson", loguedPerson);
		
		request.getRequestDispatcher("/WEB-INF/JProjet.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("HProjet Post");
		
		// recherche du projet à modifier
		Projet updatedProjet = new Projet();

		// affectation des nouvelles valeurs
		String inputPersonne = request.getParameter("inputPersonne");
		System.out.println("inputPersonne par id : "+inputPersonne);
		Personne cdp = new Personne();
		cdp.setId(Integer.valueOf(inputPersonne));
		cdp = serviceGestionEcole.rechercherPersonne(cdp).get(0);
		updatedProjet.setPersonne(cdp);
		System.out.println("inputCdpNom : "+cdp.getNom());
	
		String inputProjNom = request.getParameter("inputProjNom");
		updatedProjet.setProjNom(inputProjNom);
		System.out.println("inputProjNom : "+inputProjNom);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		String inputProjDatedebut = request.getParameter("inputProjDatedebut");
		System.out.println("inputProjDatedebut : "+inputProjDatedebut);
		try {
			Date inputDate = simpleDateFormat.parse(inputProjDatedebut);
			updatedProjet.setProjDatedebut(inputDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		String inputProjDatedefin = request.getParameter("inputProjDatedefin");
		System.out.println("inputProjDatedefin : "+inputProjDatedefin);
		try {
			Date inputDate = simpleDateFormat.parse(inputProjDatedefin);
			updatedProjet.setProjDatedefin(inputDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		
		String inputprojDescription = request.getParameter("inputprojDescription");
		updatedProjet.setProjDescription(inputprojDescription);
		System.out.println("inputprojDescription : "+inputprojDescription);

		String inputProjWikiCdp = request.getParameter("inputProjWikiCdp");
		updatedProjet.setProjWikiCdp(inputProjWikiCdp);
		System.out.println("inputProjWikiCdp : "+inputProjWikiCdp);
		
		String inputprojWikiMembre = request.getParameter("inputprojWikiMembre");
		updatedProjet.setProjWikiMembre(inputprojWikiMembre);
		System.out.println("inputprojWikiMembre : "+inputprojWikiMembre);
		
		String inputprojAvancemen = request.getParameter("inputprojAvancement");
		updatedProjet.setProjAvancement(inputprojAvancemen);
		System.out.println("inputprojAvancement : "+inputprojAvancemen);
		
		
		
		////////////////////////////////////  delete update create  /////////////////////////////////////
		
		
		
		if (request.getParameter("delete") != null) {
			System.out.println("HProjet Post delete");
			try {
				Integer inputProjId = Integer.valueOf(request.getParameter("inputProjId"));
				updatedProjet.setProjId(inputProjId);
				System.out.println("inputProjId : "+inputProjId);
				serviceGestionEcole.deleteProjet(updatedProjet);
			}
			catch (NumberFormatException e) {
				// parametres non corrects : pas de suppression
			}
		}

		if (request.getParameter("create") != null) {
			System.out.println("HProjet POST create");
			serviceGestionEcole.insertProjet(updatedProjet);
		}

		if (request.getParameter("update") != null) {
			System.out.println("HProjet POST update");
			Integer inputProjId = Integer.valueOf(request.getParameter("inputProjId"));
			System.out.println("inputProjId = "+inputProjId);
			updatedProjet.setProjId(inputProjId);
			serviceGestionEcole.updateProjet(updatedProjet);
		}

		response.sendRedirect("/GTC/HProjet");
	}

}
