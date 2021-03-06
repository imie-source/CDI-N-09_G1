package org.imie;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Personne;
import model.Possede;
import model.Promotion;

//import org.imie.service.ServiceGestionCompJPALocal;
//import org.imie.service.ServiceGestionCompJPALocal;
import org.imie.service.ServiceGestionEcoleJPALocal;

/**
 * Servlet implementation class Profil
 */
@WebServlet("/Profil")
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB ServiceGestionEcoleJPALocal serviceGestionEcole;
	//@EJB ServiceGestionCompJPALocal serviceGestionComp;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Profil Get");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		Personne loguedPerson0= new Personne();
		loguedPerson0 = (Personne) httpServletRequest.getSession().getAttribute("authentifiedPersonne");
		Personne loguedPerson = new Personne();
		loguedPerson.setId(loguedPerson0.getId());
		loguedPerson = serviceGestionEcole.rechercherPersonne(loguedPerson).get(0);
		request.setAttribute("loguedPerson", loguedPerson);
		
		request.setAttribute("promotions",serviceGestionEcole.rechercherPromotion(new Promotion()));
		
		Integer[] tempArray = new Integer[]{0,1,2,3,4,5};
		List<Integer> list5int = Arrays.asList(tempArray);;
		request.setAttribute("list5int", list5int);
		
		Possede possede = new Possede();
		possede.setPersonne(loguedPerson);

		System.out.println("RECHERCHER POSSEDE");
		request.setAttribute("possedes", serviceGestionEcole.rechercherPossede(possede));
		//request.setAttribute("possedes", serviceGestionComp.rechercherPossede(possede));
		
		System.out.println(loguedPerson.getNom());
		
//		if (loguedPerson.getCgu()){
			request.getRequestDispatcher("/WEB-INF/JProfil.jsp").forward(request, response) ;
//		}else {
//			//httpServletRequest.getSession().setAttribute("authentifiedPersonne",null);
//			response.sendRedirect("/GTC/CGU");
//		}
//		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Profil Post");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		Personne loguedPerson = new Personne();
		loguedPerson=(Personne) httpServletRequest.getSession().getAttribute("authentifiedPersonne");
		request.setAttribute("loguedPerson", loguedPerson);
		
		// recherche de la personne à modifier
		Personne updatedPerson = new Personne();
		
		//updatedPerson = serviceGestionEcole.rechercherPersonne(updatedPerson).get(0);
		// affectation des nouvelles valeurs
		
		
		
		String inputNom = request.getParameter("inputNom");
		updatedPerson.setNom(inputNom);
		System.out.println("nom : "+inputNom);
	
		String inputPrenom = request.getParameter("inputPrenom");
		updatedPerson.setPrenom(inputPrenom);
		System.out.println("prenom : "+inputPrenom);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String inputDateNaissString = request.getParameter("inputDateNaiss");
		System.out.println("datenaissServletOut : "+inputDateNaissString);
		try {
			//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//			String inputDateNaissString = request.getParameter("inputDateNaiss");
//			//Date inputDateNaiss = simpleDateFormat.parse(inputDateNaissString);
//			updatedPerson.setDateNaiss(inputDateNaiss);
			Date inputDateNaiss = simpleDateFormat.parse(inputDateNaissString);
			updatedPerson.setDateNaiss(inputDateNaiss);
			System.out.println("datenaissServletIn : "+inputDateNaissString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		String inputPassword = request.getParameter("inputPassw");
		if (inputPassword != null && !inputPassword.isEmpty()) {
			updatedPerson.setPassw(inputPassword);
		}
		
		String inputPromotionString = request.getParameter("inputPromotion");
		if (!inputPromotionString.isEmpty()) {
			Integer inputPromotion = Integer.valueOf(inputPromotionString);
			Promotion searchPromotion = new Promotion();
			searchPromotion.setId(inputPromotion);
			searchPromotion = serviceGestionEcole.rechercherPromotion(
					searchPromotion).get(0);
			updatedPerson.setPromotion(searchPromotion);
		} else {
			updatedPerson.setPromotion(null);
		}

		// + categorie : admin, user, super admin
		
		String inputEmail = request.getParameter("inputEmail");
		updatedPerson.setEmail(inputEmail);
		System.out.println("email : "+inputEmail);
		
		if (request.getParameter("delete") != null) {
			System.out.println("Profil Post delete");
			try {
				Integer inputId = Integer.valueOf(request.getParameter("inputId"));
				updatedPerson.setId(inputId);
				System.out.println("id : "+inputId);
				serviceGestionEcole.deletePersonne(updatedPerson);
			}
			catch (NumberFormatException e) {
				// parametres non corrects : pas de suppression
			}
		}
		
		String inputInfos = request.getParameter("inputInfos");
		updatedPerson.setInfos(inputInfos);
		System.out.println("infos : "+inputInfos);
		
		updatedPerson.setIdentConnexion(loguedPerson.getIdentConnexion());
		
		updatedPerson.setDisponibilite(loguedPerson.getDisponibilite());
		
		updatedPerson.setRole(loguedPerson.getRole());
		
		

		if (request.getParameter("create") != null) {
			serviceGestionEcole.insertPersonne(updatedPerson);
		}

		if (request.getParameter("update") != null) {
			System.out.println("Profil POST update");
			Integer inputId = Integer.valueOf(request.getParameter("inputId"));
			System.out.println("InputId = "+inputId);
			updatedPerson.setId(inputId);
			updatedPerson = serviceGestionEcole.updatePersonne(updatedPerson);
			//HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			httpServletRequest.getSession().setAttribute("authentifiedPersonne",updatedPerson);
		}

		
		System.out.println("Personne POST update");
		Integer inputId = Integer.valueOf(request.getParameter("inputId"));
		System.out.println("InputId = "+inputId);
		updatedPerson.setId(inputId);
		serviceGestionEcole.updatePersonne(updatedPerson);
		
		
		response.sendRedirect("/GTC/Profil");
	}
}


