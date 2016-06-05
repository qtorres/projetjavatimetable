/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package userModel;

import userModel.Utilisateur;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Administrateur.
 * 
 * @author Quentin
 */
public class Administrateur extends Utilisateur {
	//-------------------------------ATTRIBUTS--------------------
	/**
	 * Description of the property IdAdmin.
	 */
	protected int IdAdmin = 0;
	
	
	
	/**
	 * Returns IdAdmin.
	 * @return IdAdmin 
	 */
	public int getIdAdmin() {
		return this.IdAdmin;
	}

	/**
	 * Sets a value to attribute IdAdmin. 
	 * @param newIdAdmin 
	 */
	public void setIdAdmin(int newIdAdmin) {
		this.IdAdmin = newIdAdmin;
	}
	
	public Administrateur(int id, String Prenom, String Nom, String Login, String MDP) {
		super(Prenom, Nom, Login, MDP, "Administrateur");
		setIdAdmin(id);
	}

}
