package com.exemple.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.exemple.beans.Utilisateur;

public class UtilisateurDAO extends DAOContext {
	
	public static Boolean isValidLoginAdmin( String login, String password ) {
        try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ) {
            // String strSql = "SELECT * FROM T_Users WHERE login='" + login +
            // "' AND password='" + password + "'";
            String strSql = "SELECT * FROM Utilisateur WHERE email=? AND mot_de_passe=? AND isadmin=true";
            try ( PreparedStatement statement = connection.prepareStatement( strSql ) ) {
                statement.setString( 1, login );
                statement.setString( 2, password );
                try ( ResultSet resultSet = statement.executeQuery() ) {
                    if ( resultSet.next() ) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch ( Exception exception ) {
            throw new RuntimeException( exception );
        }
    }
	
	
    public static Boolean isValidLogin( String login, String password ) {
        try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ) {
            // String strSql = "SELECT * FROM T_Users WHERE login='" + login +
            // "' AND password='" + password + "'";
            String strSql = "SELECT * FROM Utilisateur WHERE email=? AND mot_de_passe=? AND isadmin=false";
            try ( PreparedStatement statement = connection.prepareStatement( strSql ) ) {
                statement.setString( 1, login );
                statement.setString( 2, password );
                try ( ResultSet resultSet = statement.executeQuery() ) {
                    if ( resultSet.next() ) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch ( Exception exception ) {
            throw new RuntimeException( exception );
        }
    }

    public static Boolean isValidEmail( String login ) {
        try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ) {

            String strSql = "SELECT * FROM Utilisateur WHERE email=?";
            try ( PreparedStatement statement = connection.prepareStatement( strSql ) ) {
                statement.setString( 1, login );
                try ( ResultSet resultSet = statement.executeQuery() ) {
                    if ( resultSet.next() ) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch ( Exception exception ) {
            throw new RuntimeException( exception );
        }
    }

    public static void creerUtilisateur( Utilisateur utilisateur ) {
        try ( Connection connection = DriverManager.getConnection( dbURL,
                dbLogin, dbPassword ) ) {

            String strSql = "INSERT INTO Utilisateur (email, mot_de_passe, nom, isadmin, date_inscription) VALUES (?, ?, ?, ?, NOW())";
            try ( PreparedStatement statement = connection.prepareStatement(
                    strSql ) ) {
                statement.setString( 1, utilisateur.getEmail() );
                statement.setString( 2, utilisateur.getMotDePasse() );
                statement.setString( 3, utilisateur.getNom() );
                statement.setBoolean( 4, utilisateur.getAdmin() );
                statement.executeUpdate();
            }
        } catch ( Exception exception ) {
            throw new RuntimeException( exception );
        }
    }

}
