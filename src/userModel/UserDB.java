/*******************************************************************************
 * 2016, All rights reserved.
 * 
 * 
 *******************************************************************************/
package userModel;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Iterator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Hashtable;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import userModel.Administrateur;
import userModel.Etudiants;
import userModel.Groupe;
import userModel.Professeur;
import userModel.Utilisateur;
import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Description of UserDB.
 * 
 * @author Quentin
 */
public class UserDB {
	/**
	 * Description of the property file.
	 */
	

	private String nomfichier;
	protected Hashtable utilisateur2 = new Hashtable();
	protected Hashtable group2 = new Hashtable();
	
	/**
	 * The constructor.
	 */
	public UserDB(String userfile) {
		this.nomfichier=userfile;

		this.utilisateur2.put("su", new Administrateur(0, "su", "su", "su", "su"));
		loadDB();
	}

	/**
	 * charge la base de données à partir du fichier XML
	 * 
	 */
	    public boolean loadDB()  {
		SAXBuilder sax;
		sax = new SAXBuilder();
		Document document;
        document = null;
        Element racine;
        
        try { //on essaye d'ouvrir le fichier
            document = sax.build(new File(this.nomfichier));
        } catch (Exception v0) {}
		
        if(document != null) { //si on arrive à ouvrir le fichier
        	//On initialise un nouvel élément racine avec l'élément racine du document.
        	racine = document.getRootElement();
        	
        	//pour les groupes
        	Chargement_Groupe(racine);
        	
        	//POUR LES ETUDIANTS
        	Chargement_Etudiant(racine);

        	//POUR LES PROFESSEURS
        	Chargement_Professeur(racine);
        	
        	//Pour les administrateurs
        	Chargement_Administrateur(racine);
        	
        	return true;
        	  } //fin if
       return false;
        	   }
        
	  public void Chargement_Etudiant(Element racine) {
		
	        String login, mdp, prenom, nom, id, g;

		//On descend d'un cran
      	Element racine2 = racine.getChild("Students");
          
      	//on récupère la liste des étudiants
          List<Element> e = racine2.getChildren("Student");

      	  int i = 0;
      	  for(i = 0; i<e.size(); i++) {
      	  List<Element> student1 = e.get(i).getChildren();

      	      //Et on enregistre dans les variables
      		  login = student1.get(0).getText();
      	      prenom = student1.get(1).getText();
      	      nom = student1.get(2).getText();
      	      mdp =student1.get(3).getText();
      	      id = student1.get(4).getText();
      	      g = student1.get(5).getText();

      	      //on crée l'objet Etudiant
      	    this.utilisateur2.put(login, new Etudiants(Integer.parseInt(id), prenom, nom, login, mdp, Integer.parseInt(g)));
      	  
	  }
 
	  }
	  
	  public void Chargement_Professeur(Element racine) {
		  String login, mdp, prenom, nom, id;
	       
			//On descend d'un cran
	      	Element racine2 = racine.getChild("Teachers");
	          
	      	//on récupère la liste des étudiants
	          List<Element> e = racine2.getChildren("Teacher");

	      	  int i = 0;
	      	  for(i = 0; i<e.size(); i++) {
	      	  List<Element> liste = e.get(i).getChildren();

	      	      //Et on enregistre dans les variables
	      		  login = liste.get(0).getText();
	      	      prenom = liste.get(1).getText();
	      	      nom = liste.get(2).getText();
	      	      mdp =liste.get(3).getText();
	      	      id = liste.get(4).getText();

	      	      //on crée l'objet Professeur
	      	      this.utilisateur2.put(login, new Professeur(Integer.parseInt(id), prenom, nom, login, mdp));
		  }
	      	
	  }
	  
	  public void Chargement_Administrateur(Element racine) {

		  String login, mdp, prenom, nom, id;
	       
			//On descend d'un cran
	      	Element racine2 = racine.getChild("Administrators");
	          
	      	//on récupère la liste des étudiants
	          List<Element> e = racine2.getChildren("Administrator");

	      	  int i = 0;
	      	  for(i = 0; i<e.size(); i++) {
	      	  List<Element> liste = e.get(i).getChildren();

	      	      //Et on enregistre dans les variables
	      		  login = liste.get(0).getText();
	      	      prenom = liste.get(1).getText();
	      	      nom = liste.get(2).getText();
	      	      mdp =liste.get(3).getText();
	      	      id = liste.get(4).getText();

	      	      //on crée l'objet Admin
	      	    this.utilisateur2.put(login, new Administrateur(Integer.parseInt(id), prenom, nom, login, mdp));
	      	     	
	      	      }
	  }

	  public void Chargement_Groupe(Element racine) {

		  String id;
	       
			//On descend d'un cran
	      	Element racine2 = racine.getChild("Groups");
	      	//on récupère la liste des groupes
	          List<Element> e = racine2.getChildren("Group");

	      	  int i = 0;
	      	  for(i = 0; i<e.size(); i++) {
	      	  List<Element> liste = e.get(i).getChildren();

	      	      //Et on enregistre dans les variables
	      	      id = liste.get(0).getText();

	      	      //on crée l'objet groupe
	      	    this.group2.put(id, new Groupe(Integer.parseInt(id)));

		  }
	  }
	  
	  public boolean associateStudToGroup(String adminLogin, String studentLogin, int groupId) {
		 //si le login administrateur est correct et que le login etudiant est correct
		  if(this.utilisateur2.get(adminLogin) instanceof Administrateur && this.utilisateur2.get(studentLogin) instanceof Etudiants) {
			  //si le groupe existe
			  if(this.group2.get(groupId) instanceof Groupe) {
				  Groupe groupe = (Groupe)this.group2.get(groupId);
				  groupe.GetEtudiants().put(studentLogin, this.utilisateur2.get(studentLogin));
			  }
			  else {
				 // addGroup();
			  }
		  
		  return true;
		 }
		  
		  //si le login admin est incorrect
		 return false;
	  }

	 /**
	 * Description of the method saveDB.
	 */
	public void saveDB() {
		
	}

	//renvoie la classe de la personne à partir du login et du mot de passe
	//public String getUserClass(String userLogin, String userPwd) {
	
			
		//}

	
	//Fonction qui renvoie le prenom et le nom de l'utilisateur à partir de son login
 	public String getUserName(String userLogin) {
 		
 		return "Login incorrect, impossible de trouver l'utilisateur";
 	}

 	//Fonction permettant de rÃ©cupÃ©rer les identifiants des groupes sous la forme d'un tableau 
 	//de chaÃ®nes de caractÃ¨res oÃ¹ chaque ligne contient l'identifiant d'un groupe.
 	public String[] groupsIdToString() {
 		
 		
 			String tabGroup[] = new String[2];
 			tabGroup[0] = "pas de groupe existants";
 			tabGroup[1] = "pas de groupe existants";
 			return tabGroup ;
 		
 	}
 	
 	//Fonction permettant de rÃ©cupÃ©rer toutes les informations des groupes sous la forme d'un tableau de 
 	//chaÃ®nes de caractÃ¨res oÃ¹ chaque ligne contient les informations d'un groupe.
 	public String[] groupsToString() {
 		String tabGroup[] = new String[2];
			tabGroup[0] = "pas de groupe existants";
			tabGroup[1] = "pas de groupe existants";
			return tabGroup ;
 	}
 	
}
