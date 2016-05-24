/*******************************************************************************
 * 2016, All rights reserved.

//reste GEtStudentGroup !!!
 *******************************************************************************/
package userModel;

import userModel.Utilisateur;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Etudiant.
 * 
 * @author Quentin
 */
public class Etudiants extends Utilisateur {
	//-------------------------------------ATTRIBUT------------------
	/**
	 * Description of the property IdEtudiant.
	 */
	protected int IdEtudiant = 0;
	protected int IdEtudiantGroup = -1;

//---------------------------------------SET----------------------------------
	/**
	 * Description of the method SetIdEtudiant.
	 * @param idetudiant 
	 */
	public void SetIdEtudiant(int idetudiant) {
		// Start of user code for method SetIdEtudiant
		IdEtudiant = idetudiant;
		// End of user code
	}

	public void SetIdEtudiantGroup(int id) {
		this.IdEtudiantGroup = id;
	}

	
//---------------------------------------GET----------------------------------
	/**
	 * Description of the method getidetudiant.
	 * @return 
	 */
	public int GetIdEtudiant() {
		// Start of user code for method getidetudiant
		return IdEtudiant;
		// End of user code
	}
	
	public int GetIdEtudiantGroup() {
		return this.IdEtudiantGroup;
	}

//--------------------------------------METHODES---------------------------------	
	/**
	 * Description of the method getStudentGroup.
	 * @param studentLogin 
	 * @return 
	 */
	//public String getStudentGroup(String studentLogin) {
		// Start of user code for method getStudentGroup
		
		//return ;
		// End of user code
	//}

public Etudiants(int id, String Prenom, String Nom, String Login, String MDP, int idgroup) {
	super(Prenom, Nom, Login, MDP, "Etudiant");
	SetIdEtudiant(id);
	SetIdEtudiantGroup(idgroup);
	
}

public Etudiants() {
	
}

}
