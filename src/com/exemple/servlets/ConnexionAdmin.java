package com.exemple.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exemple.bdd.DAOContext;
import com.exemple.beans.Utilisateur;
import com.exemple.forms.InscriptionForm;



@WebServlet("/admin")
public class ConnexionAdmin extends HttpServlet {
    public static final String VUE      = "/restreint/adminEnLigne.jsp";
    private static Connection conn;
	private static Statement stmt;
	private static String nom;
	public static final String ATT_INFO_USER = "utilisateurINFO";
	private String resultat;
	private static ResultSet rs;
	private static int rsD;
	
	static ArrayList<String> noms = new ArrayList<String>();

    @Override
    public void init() throws ServletException {
        DAOContext.init( this.getServletContext() );
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	
    	
    	HttpSession session = request.getSession();
    	
        try {
			this.initConnection();
			resultat = nom;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        session.setAttribute(ATT_INFO_USER, noms);
        request.getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
    	
    	String first_name = request.getParameter("type");
    	System.out.println("DoPost");
    	if(first_name.equals("Supprimer"))
    	{
    		System.out.println("DoPost - Supprimer");
    		String email = getValeurChamp( request, "email" );
    		System.out.println("DoPost - Supprimer" + email);
    		deleteItem(email);
    	}
    	 this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
    
    public static void initConnection() throws SQLException {
		
		Properties userInfo = new Properties();
		userInfo.setProperty("user", "root");
		userInfo.setProperty("password", "password");
		
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cours_JEE_exemple1?serverTimezone=UTC", userInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql = "SELECT * FROM Utilisateur;";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				// le numéro correspond au numéro de la colonne dans la table
				noms.add(rs.getString(2));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSetMetaData metadata = rs.getMetaData(); 
		int nombreColonnes = metadata.getColumnCount(); 
		System.out.println("Ce ResultSet contient "+nombreColonnes+" colonnes.");
		System.out.println(" "+rs+" "+nom);
		
		
	}
    
	public static void deleteItem(String emailParam)
	{
		
		System.out.println("deleteItem");
		Properties userInfo = new Properties();
		userInfo.setProperty("user", "root");
		userInfo.setProperty("password", "password");
		
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Cours_JEE_exemple1?serverTimezone=UTC", userInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql = "DELETE FROM Utilisateur WHERE email='"+emailParam+"' AND isadmin=false;";
		System.out.println(" "+sql);
		try {
			stmt = conn.createStatement();
			rsD = stmt.executeUpdate(sql);
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
	
	  private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
	        String valeur = request.getParameter( nomChamp );
	        if ( valeur == null || valeur.trim().length() == 0 ) {
	            return null;
	        } else {
	            return valeur;
	        }
	    }
 
}
