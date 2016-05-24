/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package userModel;
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
	 * création d'une liste d'étudiant
	 */
	protected List<Etudiants> etudiants = new LinkedList<Etudiants>();
	
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
	
	public void IncrementNombreEtudiant() {
		NombreEtudiant++;
	}
	
	public void DecrementNombreEtudiant() {
		if(NombreEtudiant > 0)
		NombreEtudiant--;
	}
	
	/**
	 * Returns etudiants.
	 * @return etudiants 
	 */
	public List<Etudiants> getEtudiants() {
		return this.etudiants;
	}

	
	//-------------------------------------------METHODES------------------------------

	public Groupe(int id) {
		SetIdGroup(id);
		SetNombreEtudiant(0);
	}
	
	public Groupe() {
		
	}
	
}
	
