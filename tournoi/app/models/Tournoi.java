package models;

import play.*;
import play.db.jpa.*;
 
import javax.persistence.*;


import java.util.*;
import play.data.validation.*;

@Entity
public class Tournoi extends Model{
	
	@Required
	public String nomTournoi;
	@Required
	public String typeTournoi;
	@Lob
	public String description;
	@Required
	public int nbEquipes;

	@OneToMany(mappedBy="leTournoi", cascade=CascadeType.ALL)
	public List<Equipe> lesEquipes=new ArrayList();

	public Tournoi(String nomTournoi, String typeTournoi, String description, List<Equipe> lesEquipes) {
		this.nomTournoi = nomTournoi;
		this.typeTournoi = typeTournoi;
		this.description = description;
		this.lesEquipes = lesEquipes;
	}
	
	@Override
	 public String toString() {
	  return  nomTournoi;
	 }
}
