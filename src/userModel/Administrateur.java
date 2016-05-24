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
	 * Description of the method AdminLogin.
	 * @param adminLogin 
	 * @param newAdminlogin 
	 * @param adminID 
	 * @param firstname 
	 * @param surname 
	 * @param pwd 
	 * @return 
	 */
	public Boolean AdminLogin(String adminLogin, String newAdminlogin, int adminID, String firstname,
			String surname, String pwd) {
		// Start of user code for method AdminLogin
		Boolean AdminLogin = Boolean.FALSE;
		return AdminLogin;
		// End of user code
	}

	/**
	 * Description of the method addGroup.
	 * @param adminLogin 
	 * @param groupId 
	 * @return 
	 */
	public Boolean addGroup(String adminLogin, int groupId) {
		// Start of user code for method addGroup
		Boolean addGroup = Boolean.FALSE;
		return addGroup;
		// End of user code
	}

	/**
	 * Description of the method addStudent.
	 * @param adminLogin 
	 * @param newStudentLogin 
	 * @param studentID 
	 * @param firstname 
	 * @param surname 
	 * @param pwd 
	 * @return 
	 */
	public Boolean addStudent(String adminLogin, String newStudentLogin, int studentID, String firstname,
			String surname, String pwd) {
		// Start of user code for method addStudent
		Boolean addStudent = Boolean.FALSE;
		return addStudent;
		// End of user code
	}

	/**
	 * Description of the method addTeacher.
	 * @param adminLogin 
	 * @param newteacherLogin 
	 * @param teacherID 
	 * @param firstname 
	 * @param surname 
	 * @return 
	 */
	public Boolean addTeacher(String adminLogin, String newteacherLogin, int teacherID, String firstname,
			String surname) {
		// Start of user code for method addTeacher
		Boolean addTeacher = Boolean.FALSE;
		return addTeacher;
		// End of user code
	}

	/**
	 * Description of the method associateStudToGroup.
	 * @param adminLogin 
	 * @param studentLogin 
	 * @param groupId 
	 * @return 
	 */
	public Boolean associateStudToGroup(String adminLogin, String studentLogin, int groupId) {
		// Start of user code for method associateStudToGroup
		Boolean associateStudToGroup = Boolean.FALSE;
		return associateStudToGroup;
		// End of user code
	}

	/**
	 * Description of the method removeGroup.
	 * @param adminLogin 
	 * @param groupId 
	 * @return 
	 */
	public Boolean removeGroup(String adminLogin, int groupId) {
		// Start of user code for method removeGroup
		Boolean removeGroup = Boolean.FALSE;
		return removeGroup;
		// End of user code
	}

	/**
	 * Description of the method removeUser.
	 * @param adminLogin 
	 * @param userLogin 
	 * @return 
	 */
	public Boolean removeUser(String adminLogin, String userLogin) {
		// Start of user code for method removeUser
		Boolean removeUser = Boolean.FALSE;
		return removeUser;
		// End of user code
	}

	
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
