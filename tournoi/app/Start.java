import play.*;
import play.jobs.*;
import play.test.*;
 
import models.*;
 
@OnApplicationStart
public class Start extends Job {
 
    public void doJob() {
        // Check if the database is empty
    	
        if(Joueur.count() == 0) {
            //Fixtures.load("data.yml");
        }
    }
 
}