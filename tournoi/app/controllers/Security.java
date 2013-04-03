package controllers;
 
import models.*;
 
public class Security extends Secure.Security {
 
    static boolean authentify(String fullname, String password) {
        if(Joueur.connect(fullname, password)!=null)
        	return Joueur.connect(fullname, password).isAdmin;
        else
        	return false;
 
    }
    
}