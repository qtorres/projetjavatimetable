/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package userModel;
import userModel.Utilisateur;


/**
 * Description of Professeur.
 * 
 * @author Quentin
 */
public class Professeur extends Utilisateur {
	//-------------------------------------ATTRIBUT-----------------------------------
	/**
	 * Description of the property IdProf.
	 */
	protected int IdProf = 0;

	//----------------------------------SET----------------------------------
	/**
	 * Description of the method SetIdProf.
	 * @param idprof 
	 */
	public void SetIdProf(int idprof) {
		// Start of user code for method SetIdProf
		IdProf = idprof;
		// End of user code
	}

	//------------------------------------GET----------------------------------
	/**
	 * Description of the method GetIdProf.
	 * @return 
	 */
	public int GetIdProf() {
		// Start of user code for method GetIdProf
		return IdProf;
		// End of user code
	}
	
	//------------------------------------METHODES----------------------
	Professeur(int id, String Prenom, String Nom, String Login, String MDP) {
		super(Prenom, Nom, Login, MDP, "Professeur");
		SetIdProf(id);
	}
	
	Professeur() {
		
	}

}
