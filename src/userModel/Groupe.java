/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package userModel;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import userModel.Etudiants;
/**
 * Description of Groupe.
 * 
 * @author Quentin
 */

public class Groupe {
	//--------------------------------------------ATTRIBUT------------------------
	/**
	 * Description of the property IdGroup.
	 */
	protected int IdGroup = 0;
	protected int NombreEtudiant = 0;

	/**
	 * cr�ation d'une liste d'�tudiant
	 *
	 */
	protected Hashtable etudiants2 = new Hashtable();
	
//----------------------------------Set------------------------------------

	/**
	 * Description of the method SetIdGroup.
	 * @param idgroup 
	 */
	public void SetIdGroup(int idgroup) {
		// Start of user code for method SetIdGroup
		IdGroup = idgroup;
		// End of user code
	}
	
	public void SetNombreEtudiant(int Nombre) {
		NombreEtudiant = Nombre;
	}
//---------------------------------GET-------------------------------------
	/**
	 * Description of the method GetIdGroup.
	 * @return 
	 */
	public int GetIdGroup() {
		// Start of user code for method GetIdGroup
		return IdGroup;
		// End of user code
	}

	public int GetNombreEtudiant() {
		return NombreEtudiant;
	}
	
	public Groupe GetGroupe() {
		return this;
	}
	
	public Hashtable GetEtudiants() {
		return this.etudiants2;
	}
	
	public void IncrementNombreEtudiant() {
		NombreEtudiant++;
	}
	
	public void DecrementNombreEtudiant() {
		if(NombreEtudiant > 0)
		NombreEtudiant--;
	}
	
	

	
	//-------------------------------------------METHODES------------------------------

	public Groupe(int id) {
		SetIdGroup(id);
		SetNombreEtudiant(0);
	}
	
	public Groupe() {
		
	}
	
}
	
