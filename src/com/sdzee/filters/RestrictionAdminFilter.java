package com.sdzee.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.exemple.beans.Utilisateur;

public class RestrictionAdminFilter implements Filter {
    public static final String ACCES_PUBLIC     = "/connexion.jsp";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";

    public void init( FilterConfig config ) throws ServletException {
    }

    public void doFilter( ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException,
            ServletException {
        /* Cast des objets request et response */
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        /* R�cup�ration de la session depuis la requ�te */
        HttpSession session = request.getSession();

        /**
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connect�.
         */
        
       Utilisateur user = (Utilisateur)session.getAttribute(ATT_SESSION_USER);
    		   
        
        if ( user == null) {
        	/* Redirection vers la page publique */
        	 response.sendRedirect( "http://localhost:8080/pro_form_session/connexion" );
        	
          
        } else {
        	
        	if(user.getAdmin() == null) {
        		/* Redirection vers la page publique */
        	response.sendRedirect( "http://localhost:8080/pro_form_session/connexion" );

        	}
        	else {
            	/* Affichage de la page restreinte */
                chain.doFilter( request, response );
        	}
        }
    }

    public void destroy() {
    }
}