package com.exemple.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exemple.bdd.DAOContext;
import com.exemple.beans.Utilisateur;
import com.exemple.forms.UtilisateurForm;


@WebServlet("/utilisateur")
public class ConnexionUtilisateur extends HttpServlet {
    public static final String VUE      = "/restreint/utilisateurEnLigne.jsp";
    private static Connection conn;
	private static Statement stmt;
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String ATT_NOM_USER = "utilisateurNom";
	public static final String ATT_MDP_USER = "utilisateurMDP";
	public static final String ATT_EMAIL_USER = "utilisateurEmail";
	private static String id;
	private static String nom;
	private static String motDePasse;
	private static String email;
	private static ResultSet rs;

    @Override
    public void init() throws ServletException {
        DAOContext.init( this.getServletContext() );
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	/* Création ou récupération de la session */
    	HttpSession session = request.getSession();
    	
    	 Utilisateur user = (Utilisateur)session.getAttribute(ATT_SESSION_USER);
         email = user.getEmail();
         motDePasse = user.getMotDePasse();
         
    	session.setAttribute(ATT_MDP_USER, motDePasse);
    	session.setAttribute(ATT_EMAIL_USER, email);
    	session.setAttribute(ATT_NOM_USER, nom);
        request.getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
       
    }
 
}
