package controllers;

import play.*;
import play.cache.Cache;
import play.data.validation.Required;
import play.libs.Codec;
import play.libs.Images;
import play.mvc.*;
import sun.io.Converters;

import java.util.*;

import org.apache.commons.beanutils.Converter;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }
    public static void captcha(String id) {
        Images.Captcha captcha = Images.captcha();
        String code = captcha.getText("#FFFFFF");
        Cache.set(id, code, "10mn");
        renderBinary(captcha);
        
    }
    public static void Inscription()
    {
    	render("Joueurs/inscriptionJoueur.html");
    }
	public static void Connexion()
    {
    	render("Joueurs/connexion.html");
    }
	public static void VerifConnexion(@Required String fullname, @Required String password) {
    	//boolean isConnected = false ; 
		//    	List<Joueur> LesJoueurs = Joueur.find("login like ?", fullname)
		//   			.fetch(1);

		//    		if (LesJoueurs.size() !=  0 && LesJoueurs.get(0).password.equals(password))
		//    		{
		//    			render("/GestionJoueur");
		//    		}
    if(Joueur.connect(fullname, password)!=null)
    {
    	//isConnected = true ;
    	render("main.html",fullname);
    }else
    {
    	render("Joueurs/connexion.html");
    }
    	

    }
    
    public static void addJoueur(
        @Required(message="Le nom est requis") String name, 
        @Required(message="L'E-mail est requis") String email, 
        @Required(message="Veuillez entrer le captcha") String code, 
        String randomID, 
        @Required(message="Le mot de passe est requis") String password,
        @Required(message="recopiez le mot de passe") String password2) 
    {
        if(!Play.id.equals("test")) {
            validation.equals(code, Cache.get(randomID)).message("Code invalide, Rééssayez");
        }
        if(validation.hasErrors()) {
            render("Joueurs/inscriptionJoueur.html", randomID);
        }
        if(password.equals(password2)==false){
        	
        }
        Joueur player= new Joueur(email,password,name);
        player.save();
        flash.success("Thanks for posting %s", name);
        Cache.delete(randomID);
        render("main.html");
        
    }
    public static void consulter()
    {
    	//List<Tournoi> lesTournois = Tournoi.findAll();
    	Tournoi t = Tournoi.findById((long)1);
    	render("Tournois/afficheTournois.html");
    	
    }
    
    public static void creer()
    {
    	render("Tournois/creerTournoi.html");
    }
    
    public static void addTournoi(@Required(message="Le nom est requis") String name,
         @Required(message="La description est requise") String desc, 
         @Required(message="Le type de tournoi est requis") String type )
    {

    	 
        
             if(validation.hasErrors()) {
                 render("Tournois/creerTournoi.html");
             }
    	List<Equipe> lesEquipes = new ArrayList<Equipe>();
    	Tournoi t = new Tournoi(name, type, desc, lesEquipes);
    	t.save();
    	flash.success("Tournoi creer! %s", name);
    }
}