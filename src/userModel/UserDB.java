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
import java.util.Enumeration;
import java.util.Set;

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
	protected Hashtable user = new Hashtable();
	protected Hashtable group = new Hashtable();
	
	/**
	 * The constructor.
	 */
	public UserDB(String userfile) {
		this.nomfichier=userfile;

		this.user.put("su", new Administrateur(0, "su", "su", "su", "su"));
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
      	    this.user.put(login, new Etudiants(Integer.parseInt(id), prenom, nom, login, mdp, Integer.parseInt(g)));
      	  
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
	      	      this.user.put(login, new Professeur(Integer.parseInt(id), prenom, nom, login, mdp));
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
	      	    this.user.put(login, new Administrateur(Integer.parseInt(id), prenom, nom, login, mdp));
	      	     	
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
	      	    this.group.put(id, new Groupe(Integer.parseInt(id)));

		  }
	  }
	  
	  public boolean associateStudToGroup(String adminLogin, String studentLogin, int groupId) {
		 //si le login administrateur est correct et que le login etudiant est correct
		  if(this.user.get(adminLogin) instanceof Administrateur && this.user.get(studentLogin) instanceof Etudiants) {
			  //si le groupe existe
			  if(this.group.get(groupId) instanceof Groupe) {
				  Groupe groupe = (Groupe)this.group.get(groupId);
				  groupe.GetEtudiants().put(studentLogin, this.user.get(studentLogin));
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
	public boolean saveDB() {
		
		Enumeration liste_user = this.user.keys();
		Enumeration liste_group = this.group.keys();
		SAXBuilder sax =  new SAXBuilder();
		Document document = null;
        Element racine = new Element("UsersDB");
        Element racine_groups = new Element("Groups");
        Element racine_students = new Element("Students");
        Element racine_teachers = new Element("Teachers");
        Element racine_administrators = new Element("Administrators");
        racine.addContent((Content)racine_groups);
        racine.addContent((Content)racine_students);
        racine.addContent((Content)racine_teachers);
        racine.addContent((Content)racine_administrators);
        
        try { //on essaye d'ouvrir le fichier
            document = sax.build(new File(this.nomfichier));
        } catch (Exception v0) {}
        
        if(document != null) { //si on arrive à ouvrir le fichier
        	
        	
        	//Pour les groupes
		while(liste_group.hasMoreElements()) {
			String cle = (String)liste_group.nextElement();
			Element group = new Element("Group");
            Element groupId = new Element("groupId");
            groupId.setText(cle);
            group.addContent((Content)groupId);
            racine_groups.addContent((Content)group);
		}

		//pour les utilisateurs
		while(liste_user.hasMoreElements()) {
			String cle = (String)liste_user.nextElement();
			
			//si c'est un étudiant
			if(this.user.get(cle) instanceof Etudiants) {
				Etudiants etudiant = (Etudiants)this.user.get(cle);
				Element Student = new Element("Student");
				Element login = new Element("login");
				Element firstname = new Element("firstname");
				Element surname = new Element("surname");
				Element pwd = new Element("pwd");
				Element studentId = new Element("studentId");
				Element groupId = new Element("groupId");
				login.setText(cle);
				firstname.setText(etudiant.GetPrenom());
				surname.setText(etudiant.GetNom());
				pwd.setText(etudiant.GetMot_De_Passe());
				studentId.setText(Integer.toString(etudiant.GetIdEtudiant()));
				groupId.setText(Integer.toString(etudiant.GetIdEtudiantGroup()));
				Student.addContent(login);
				Student.addContent(firstname);
				Student.addContent(surname);
				Student.addContent(pwd);
				Student.addContent(studentId);
				Student.addContent(groupId);
				racine_students.addContent(Student);
			}
			
			//Si c'est un professeur
			if(this.user.get(cle) instanceof Professeur) {
				Professeur professeur = (Professeur)this.user.get(cle);
				Element Teacher = new Element("Teacher");
				Element login = new Element("login");
				Element firstname = new Element("firstname");
				Element surname = new Element("surname");
				Element pwd = new Element("pwd");
				Element teacherId = new Element("teacherId");
				login.setText(cle);
				firstname.setText(professeur.GetPrenom());
				surname.setText(professeur.GetNom());
				pwd.setText(professeur.GetMot_De_Passe());
				teacherId.setText(Integer.toString(professeur.GetIdProf()));
				Teacher.addContent(login);
				Teacher.addContent(firstname);
				Teacher.addContent(surname);
				Teacher.addContent(pwd);
				Teacher.addContent(teacherId);
				racine_teachers.addContent(Teacher);
			}
			
			//Si c'est un Administrateur
			if(this.user.get(cle) instanceof Administrateur) {
				Administrateur administrateur = (Administrateur)this.user.get(cle);
				Element Admin = new Element("Administrator");
				Element login = new Element("login");
				Element firstname = new Element("firstname");
				Element surname = new Element("surname");
				Element pwd = new Element("pwd");
				Element adminId = new Element("adminId");
				login.setText(cle);
				firstname.setText(administrateur.GetPrenom());
				surname.setText(administrateur.GetNom());
				pwd.setText(administrateur.GetMot_De_Passe());
				adminId.setText(Integer.toString(administrateur.getIdAdmin()));
				Admin.addContent(login);
				Admin.addContent(firstname);
				Admin.addContent(surname);
				Admin.addContent(pwd);
				Admin.addContent(adminId);
				racine_administrators.addContent(Admin);
			}
			
        } //fin while
		
		//on ajoute le tout
		document.setRootElement(racine);
		

        try {
        	XMLOutputter object = new XMLOutputter(Format.getPrettyFormat());
            object.output(document, (OutputStream)new FileOutputStream(this.nomfichier));
        }
        catch (IOException v0) {
            return false;
        }
        return true;
	} //fin écriture document
        return true;
	} //fin fonction
	
	
	//renvoie la classe de la personne à partir du login et du mot de passe
	public String getUserClass(String userLogin, String userPwd) {
	return "ok";
			
		}

	
	//Fonction qui renvoie le prenom et le nom de l'utilisateur à partir de son login
 	public String getUserName(String userLogin) {
 		
 		return "Login incorrect, impossible de trouver l'utilisateur";
 	}

 	//Fonction permettant de rÃ©cupÃ©rer les identifiants des groupes sous la forme d'un tableau 
 	//de chaÃ®nes de caractÃ¨res oÃ¹ chaque ligne contient l'identifiant d'un groupe.
 	public String[] groupsIdToString() {
 		String[] groupString = new String[this.group.size()];
 		Enumeration liste_group = this.group.keys();
 		int i = 0;
 		while(liste_group.hasMoreElements()) {
			String cle = (String)liste_group.nextElement();
			groupString[i] = cle;
			i++;
		}
 		return groupString;
 	}
 	
 	//Fonction permettant de rÃ©cupÃ©rer toutes les informations des groupes sous la forme d'un tableau de 
 	//chaÃ®nes de caractÃ¨res oÃ¹ chaque ligne contient les informations d'un groupe.
 	public String[] groupsToString() {
 		String[] groupString = new String[this.group.size()];
 		Enumeration liste_group = this.group.keys();
 		int i = 0;
 		while(liste_group.hasMoreElements()) {
			String cle = (String)liste_group.nextElement();
			Groupe groupe = (Groupe)this.group.get(cle);
			//on récupere l'étudiant
			Hashtable table = groupe.GetEtudiants();
			Enumeration liste_etudiant = table.keys();
			while(liste_etudiant.hasMoreElements()) {
				String cle_etudiant = (String)liste_etudiant.nextElement();
				Etudiants etudiantcourant = (Etudiants)table.get(cle_etudiant);
				//maintenant on affiche
				groupString[i] = etudiantcourant.GetLogin() + "|" + etudiantcourant.GetPrenom() + "|" + etudiantcourant.GetNom()+ "|" + etudiantcourant.GetMot_De_Passe() + "|" + etudiantcourant.GetIdEtudiant() + "|" + etudiantcourant.GetIdEtudiantGroup();
						i++;
			}
			
		}
 		return groupString;
 	}
 	
 	//renvoie toutes les info des utilisateurs
 	public String[] usersToString() {
		  String[] userString = new String[this.user.size()];
		  Set keys = user.keySet();
		  Iterator it = keys.iterator();
		  int i = 0;
		  while (it.hasNext()){
		     String key = (String)it.next();
		     Utilisateur userTemp = (Utilisateur)user.get(key);
		     if (userTemp instanceof Etudiants) {
		      userString[i] = ((Etudiants) userTemp).GetIdEtudiant() + "|" + userTemp.GetLogin() + "|" + userTemp.GetMot_De_Passe() + "|" + userTemp.GetPrenom() + "|" + userTemp.GetNom() + "|" + ((Etudiants)userTemp).GetIdEtudiantGroup();
		     }
		     if (userTemp instanceof Professeur) {
		      userString[i] = ((Professeur) userTemp).GetIdProf() + "|" + userTemp.GetLogin() + "|" + userTemp.GetMot_De_Passe() + "|" + userTemp.GetPrenom() + "|" + userTemp.GetNom();
		     }
		     if (userTemp instanceof Administrateur) {
		      userString[i] = ((Administrateur) userTemp).getIdAdmin() + "|" + userTemp.GetLogin() + "|" + userTemp.GetMot_De_Passe() + "|" + userTemp.GetPrenom() + "|" + userTemp.GetNom();
		     }
		     ++i;
		  }
		  return userString;
		 }
 	
 	//renvoie tous les login des utilisateurs
 	public String[] usersLoginToString() {
 		  String[] userLoginString = new String[this.user.size()];
 		  Set keys = user.keySet();
 		  Iterator it = keys.iterator();
 		  int i = 0;
 		  while (it.hasNext()){
 		     String key = (String)it.next();
 		     Utilisateur userTemp = (Utilisateur)user.get(key);
 		     userLoginString[i] = userTemp.GetLogin();
 		     ++i;
 		  }
 		  return userLoginString;
 		 }
}
