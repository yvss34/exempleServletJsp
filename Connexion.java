package com.exemple.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exemple.bdd.DAOContext;
import com.exemple.beans.Utilisateur;
import com.exemple.forms.ConnexionForm;

@WebServlet(urlPatterns = {"/connexion"})
public class Connexion extends HttpServlet {
    public static final String ATT_USER         = "utilisateur";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String VUE              = "/WEB-INF/connexion.jsp";

    @Override
    public void init() throws ServletException {
        DAOContext.init( this.getServletContext() );
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page de connexion */
    	 request.getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
    	
    	String first_name = request.getParameter("type");
    	
    	if(first_name.equals("Inscrire"))
    	{
    		response.sendRedirect( request.getContextPath()+"/inscription" );
    	}else
    	{	
	        /* Préparation de l'objet formulaire */
	        ConnexionForm form = new ConnexionForm();
	
	        /* Traitement de la requête et récupération du bean en résultant */
	        Utilisateur utilisateur = form.connecterUtilisateur( request );
	
	        /* Récupération de la session depuis la requête */
	        HttpSession session = request.getSession();
	
	        /**
	         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
	         * Utilisateur à la session, sinon suppression du bean de la session.
	         */
	        if ( form.getErreurs().isEmpty() ) {
	        	if(utilisateur.getAdmin() == null) {
	        		 response.sendRedirect( request.getContextPath()+"/utilisateur" );
	        	      	}
	        	else {
	        		
	        		System.out.println(" "+this.getServletContext().getRequestDispatcher( "/restreint/adminEnLigne.jsp" )+"   "+this.getServletContext());
	        		response.sendRedirect( request.getContextPath()+"/admin" );
	        	}
	            session.setAttribute( ATT_SESSION_USER, utilisateur );
	           
	        } else {
	            session.setAttribute( ATT_SESSION_USER, null );
	            /* Stockage du formulaire et du bean dans l'objet request */
		        request.setAttribute( ATT_FORM, form );
		        request.setAttribute( ATT_USER, utilisateur );
	            this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	        }
	
	        

	        
    	}
    }
}