/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package userModel;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Utilisateur.
 * 
 * @author Quentin
 */
public abstract class Utilisateur {
	
	// ----------------------------ATRIBUT------------------------
	/**
	 * login de l'utilisateur. Sert à se connecter sur l'emploi du temps
	 */
	protected String Login = "";

	/**
	 * Prenom de l'utilisateur
	 */
	protected String Prénom="";

	/**
	 * Nom de l'utilisateur
	 */
	protected String Nom = "";

	/**
	 * Mot de passe de l'utilisateur
	 */
	protected String Mot_de_passe = "";

	/**
	 * Description of the property Classe.
	 */
	protected String Classe = "";
	
	//------------------------------------SET--------------------------------

	// Start of user code (user defined attributes for Utilisateur)

	// End of user code

	/**
	 * Description of the method SetPrenom.
	 * @param prenom 
	 */
	public void SetPrenom(String prenom) {
		// Start of user code for method SetPrenom
		this.Prénom = prenom;
		// End of user code
	}

	/**
	 * Description of the method SetNom.
	 * @param nom 
	 */
	public void SetNom(String nom) {
		// Start of user code for method SetNom
		this.Nom = nom;
		// End of user code
	}

	/**
	 * Description of the method SetLogin.
	 * @param login 
	 */
	public void SetLogin(String login) {
		// Start of user code for method SetLogin
		this.Login = login;
		// End of user code
	}

	/**
	 * Description of the method SetMot_De_Passe.
	 * @param mot_de_passe 
	 */
	public void SetMot_De_Passe(String mot_de_passe) {
		// Start of user code for method SetMot_De_Passe
		this.Mot_de_passe = mot_de_passe;
		// End of user code
	}
	
	/**
	 * Description of the method SetClasse.
	 * @param userclass 
	 */
	public void SetClasse(String userclass) {
		// Start of user code for method SetClasse
		this.Classe = userclass;
		// End of user code
	}

//---------------------------------------------------GET---------------------------------------
	
	/**
	 * Description of the method GetNom.
	 * @return 
	 */
	public String GetNom() {
		// Start of user code for method GetNom
		return Nom;
		// End of user code
	}

	/**
	 * Description of the method GetPrenom.
	 * @return 
	 */
	public String GetPrenom() {
		// Start of user code for method GetPrenom
		return Prénom;
		// End of user code
	}


	/**
	 * Description of the method GetLogin.
	 * @return 
	 */
	public String GetLogin() {
		// Start of user code for method GetLogin
		return Login;
		// End of user code
	}
	
	public Utilisateur GetUser() {
		return this;
	}
	
	/**
	 * Description of the method GetMot_De_Passe.
	 * @return 
	 */
	public String GetMot_De_Passe() {
		// Start of user code for method GetMot_De_Passe
		return Mot_de_passe;
		// End of user code
	}

	/**
	 * Description of the method GetClasse.
	 * @return 
	 */
	public String GetClasse() {
		return Classe;
	}
	
	//--------------------------------------METHODES------------------------------------
	
	/**
	 * Description of the method Utilisateur().
	 * @param nom 
	 * @param login 
	 * @param mot_de_passe 
	 * @param prenom 
	 * @param class 
	 */
	public Utilisateur(String prenom, String nom, String login, String mot_de_passe, String Class) {
		this.SetPrenom(prenom);
		this.SetNom(nom);
		this.SetLogin(login);
		this.SetMot_De_Passe(mot_de_passe);
		this.SetClasse(Class);
	}

	public Utilisateur() {
		
	}
	
	/**
	 * Description of the method GetUserName.
	 * @param login 
	 * @return 
	 */
	public String GetUserName() {
		// Start of user code for method GetUserName
		return Nom;
		// End of user code
	}



}
