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
	
	protected List<Utilisateur> user= new LinkedList<Utilisateur>();
	protected int NombreAdmin = 0;
	protected int NombreEtudiant = 0;
	protected int NombreProfesseur = 0;
	protected List<Groupe> group = new LinkedList<Groupe>();
	private String nomfichier;
	
	/**
	 * The constructor.
	 */
	public UserDB(String userfile) {
		
		this.nomfichier=userfile;
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
      	      Etudiants etudiant = new Etudiants(Integer.parseInt(id), prenom, nom, login, mdp, Integer.parseInt(g));
      	      
      	      //on l'ajoute dans la liste des Utilisateurs
      	      user.add(etudiant);
      	      
      	      this.NombreEtudiant++;
      	      
      	      if(g!="-1") { //Si l'étudiant à un groupe
      	    	  //on l'ajoute au groupe
      	    	  AddStudenttoGroup(etudiant);
      	    	  //A CHANGER
      	      }
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

	      	      //on crée l'objet Etudiant
	      	      Professeur prof = new Professeur(Integer.parseInt(id), prenom, nom, login, mdp);
	      	      
	      	      //on l'ajoute dans la liste des Utilisateurs
	      	      user.add(prof);
	      	      
	      	      this.NombreProfesseur++;
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
	      	      Administrateur admin = new Administrateur(Integer.parseInt(id), prenom, nom, login, mdp);
	      	      
	      	      //on l'ajoute dans la liste des Utilisateurs
	      	      user.add(admin);
	      	      
	      	      this.NombreAdmin++;		
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
	      	      Groupe groupe = new Groupe(Integer.parseInt(id));
	      	      
	      	      //on l'ajoute dans la liste des groupe
	      	      group.add(groupe);

		  }
	  }
	  
	  public void AddStudenttoGroup(Etudiants etudiant) {
		  //S'il existe un groupe
		  int i=0;
		  boolean ok = true;
		  if(!group.isEmpty()) {
			  
			  for(i=0; i<group.size(); i++) { //on parcourt la liste des groupes
				  //si on trouve le groupe correspondant à l'Id groupe de l'étudiant
				  if(group.get(i).GetIdGroup()==etudiant.GetIdEtudiantGroup()) {
					  //on ajoute l'étudiant
					  group.get(i).getEtudiants().add(etudiant);
				  }
			  }
			 
		  }
		  
		  
		  //on vérifie que l'id groupe n'est pas pris
		  for(i=0; i<group.size(); i++) {
			  if(group.get(i).GetIdGroup() == group.size()+1)
				  ok = false;
		  }
		  
		  if(ok = true) {
			//Si on a pas trouver le groupe ou si aucun groupe existe
			  //on crée le groupe
			  Groupe groupe = new Groupe(group.size()+1);
			  
			  //on l'ajoute à la liste
			  group.add(groupe);
		  }
		  else {
			//Si on a pas trouver le groupe ou si aucun groupe existe
			  //on crée le groupe
			  Groupe groupe = new Groupe(group.size()+100);
			  
			  //on l'ajoute à la liste
			  group.add(groupe);
		  }
	  }
	  
	  public boolean associateStudToGroup(String adminLogin, String studentLogin, int groupId) {
		  
		  int admin = IndexUser(adminLogin);
		  if (admin==-1)
			  return false;
		  
		  if(user.get(admin).GetClasse() == "Administrateur") {
			  int etudiant = IndexUser(studentLogin);
			  if(etudiant == -1)
				  return false;
			  
			  //on vérifie que le groupe existe
			  int groupe = IndexGroup(groupId);
			  if(groupe != -1) {
				//on ajoute l'étudiant
				  group.get(groupe).getEtudiants().add((Etudiants)user.get(etudiant));  
				  return true;
			  }
			  else {
				//Si le groupe n'existe pas
				  Groupe g = new Groupe(groupId);
				  group.add(g);
				  
			  }
		  
			  saveDB();
		  return true;
		  }
		  return false;
	  }
	  
	  public Utilisateur ResearchUser(String login) {
		  int i = 0;
		  for(i=0; i<user.size(); i++) {
			  if(user.get(i).GetLogin() == login)
				  return user.get(i);
		  }
		  return null;
	  }
	  
	  public int IndexUser(String login) {
		  int i = 0;
		  for(i=0; i<user.size(); i++) {
			  if(user.get(i).GetLogin() == login)
				  return i;
		  }
		  return -1;
	  }
	  
	  public Groupe ResearchGroup(int id) {
		  int i = 0;
		  for(i=0; i<group.size(); i++) {
			  if(group.get(i).GetIdGroup() == id)
				  return group.get(i);
		  }
		  return null;
	  }
	 
	  public int IndexGroup(int id) {
		  int i = 0;
		  for(i=0; i<group.size(); i++) {
			  if(group.get(i).GetIdGroup() == id)
				  return i;
		  }
		  return -1;
	  }
	  
	 /**
	 * Description of the method saveDB.
	 */
	public void saveDB() {
		
	}

	//renvoie la classe de la personne à partir du login et du mot de passe
	public String getUserClass(String userLogin, String userPwd) {
		String classe;
		//on parcourt la liste
		for(int i=0; i<user.size(); i++) { 
			//on compare
			if(user.get(i).GetLogin()==userLogin && user.get(i).GetMot_De_Passe() == userPwd) {
				//Si trouvé, on retourne
				classe = user.get(i).GetClasse(); 
				if(classe == "Administrateur")
					return "Administrator";
				if(classe == "Professeur")
					return "Teacher";
				if(classe == "Etudiant")
					return "Student";
			}
			
		}
		//Si non trouvé -> message d'erreur
		return "Login / mot de passe incorrect";
	}
	
	//Fonction qui renvoie le prenom et le nom de l'utilisateur à partir de son login
 	public String getUserName(String userLogin) {
 		int i=0;
 		for(i=0; i<user.size(); i++) {
 			if(user.get(i).GetLogin() == userLogin) {
 				return user.get(i).GetPrenom() + " " + user.get(i).GetNom();
 			}
 		}
 		return "Login incorrect, impossible de trouver l'utilisateur";
 	}

 	//Fonction permettant de rÃ©cupÃ©rer les identifiants des groupes sous la forme d'un tableau 
 	//de chaÃ®nes de caractÃ¨res oÃ¹ chaque ligne contient l'identifiant d'un groupe.
 	public String[] groupsIdToString() {
 		
 		//S'il n'y a pas de groupes
 		if(group.size() == 0) {
 			String tabGroup[] = new String[2];
 			tabGroup[0] = "pas de groupe existants";
 			tabGroup[1] = "pas de groupe existants";
 			return tabGroup ;
 		}
 		
 		//Si il y a au moins un groupe
 		String tabGroup[] = new String[group.size()];
 		int i = 0;
 		//on parcourt la liste de groupe
 		for(i=0; i<group.size(); i++) {
 			//Integer.toString -> converti un int en String
 			//on ajoute l'IdGroupe dans le tableau
 			tabGroup[i] = Integer.toString(group.get(i).GetIdGroup());
 		}
 		return tabGroup;
 	}
 	
 	//Fonction permettant de rÃ©cupÃ©rer toutes les informations des groupes sous la forme d'un tableau de 
 	//chaÃ®nes de caractÃ¨res oÃ¹ chaque ligne contient les informations d'un groupe.
 	public String[] groupsToString() {
 		//S'il n'y a pas de groupes
 		if(group.size() == 0) {
 			String tabGroup[] = new String[2];
 			tabGroup[0] = "pas de groupe existants";
 			tabGroup[1] = "pas de groupe existants";
 			return tabGroup ;
 		}
 
 		//Si il y a au moins un groupe
 		String tabGroup[] = new String[group.size()];
 		int i = 0;
 		int j=0;
 		int nombreetudiant = 0;
 		//on parcourt la liste de groupe
 		for(i=0; i<group.size(); i++) {
 			nombreetudiant = group.get(i).GetNombreEtudiant();
 			j=0;
 			for(j=0; j<nombreetudiant; j++) {
 			tabGroup[i] = group.get(i).getEtudiants().get(j).GetLogin() + " " + group.get(i).getEtudiants().get(j).GetPrenom() + " " + group.get(i).getEtudiants().get(j).GetNom() + " " + group.get(i).getEtudiants().get(j).GetMot_De_Passe() + " " + group.get(i).getEtudiants().get(j).GetClasse();
 		}
 		}
 		return tabGroup;
 	}
 	
    public List<Utilisateur> Getuser() {
	 return user;
 }
    
}
