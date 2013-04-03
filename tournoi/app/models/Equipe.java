package models;

import play.*;
import play.db.jpa.*;
 
import javax.persistence.*;


import java.util.*;
 
import play.data.validation.*;

@Entity
public class Equipe extends Model {

	@Required
	public String nomDeLequipe;
	@Lob
	public String description;
	public String avatar;
	
	@OneToOne (cascade=CascadeType.ALL)
	public Tournoi leTournoi=null;
	
	@OneToMany(mappedBy="monEquipe", cascade=CascadeType.ALL)
	public List<Joueur> lesPlayers=new ArrayList();

	public Equipe(String nomDeLequipe, String description, String avatar) {
		this.nomDeLequipe = nomDeLequipe;
		this.description = description;
		this.avatar = avatar;
	}
	@Override
	 public String toString() {
	  return  nomDeLequipe;
	 }
}
