package models;
 
import play.*;
import play.db.jpa.*;
 
import javax.persistence.*;
import java.util.*;
 
import play.data.validation.*;
import play.libs.*;
 
@Entity
public class Joueur extends Model {
	
	@Required
	@Email
	@Unique
    public String email;
	@Required
    public String password;
	@Required
    public String fullname;
    public boolean isAdmin;
	
    @ManyToOne (cascade = CascadeType.ALL)
    public Equipe monEquipe=null;
    
    public Joueur(String email, String password, String fullname) {
    	this.email = email;
        this.password = password;
        this.fullname = fullname;
    }
    
    public static Joueur connect(String fullname, String password) {
        return find("byFullnameAndPassword", fullname, password).first();
    }
    @Override
	 public String toString() {
	  return  fullname;
	 }
}