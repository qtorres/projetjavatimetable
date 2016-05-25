package userController;

import userModel.UserDB;
/**
 * Cette classe est le contr�leur d'utilisateurs que vous devez impl�menter. 
 * Elle contient un attribut correspondant � la base de donn�es utilisateurs que vous allez cr�er.
 * Elle contient toutes les fonctions de l'interface IUserController que vous devez impl�menter.
 * 
 * @author Jose Mennesson (Mettre � jour)
 * @version 04/2016 (Mettre � jour)
 * 
 */

//TODO Classe � modifier

public class UserController implements IUserController
{
	
	/**
	 * Contient une instance de base de donn�es d'utilisateurs
	 * 
	 */
	private UserDB userDB=null;
	
	
	/**
	 * Constructeur de controleur d'utilisateurs cr�ant la base de donn�es d'utilisateurs
	 * 
	 * @param userfile
	 * 		Fichier XML contenant la base de donn�es d'utilisateurs
	 */
	public UserController(String userfile){
		UserDB userDB=new UserDB(userfile);
		this.setUserDB(userDB);
	}

	@Override
	public String getUserName(String userLogin) {
		return userDB.getUserName(userLogin);
	}

	@Override
	public String getUserClass(String userLogin, String userPwd) {
		return userDB.getUserClass(userLogin, userPwd);
	}

	@Override
	public int getStudentGroup(String studentLogin) {
		return Integer.parseInt(userDB.getStudentGroup(studentLogin));
	}

	@Override
	public boolean addAdmin(String adminLogin, String newAdminlogin, int adminID, String firstname, String surname,
			String pwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addTeacher(String adminLogin, String newteacherLogin, int teacherID, String firstname,
			String surname, String pwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addStudent(String adminLogin, String newStudentLogin, int studentID, String firstname,
			String surname, String pwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeUser(String adminLogin, String userLogin) {
		return userDB.removeUser(adminLogin, userLogin);
	}

	@Override
	public boolean addGroup(String adminLogin, int groupId) {
		return userDB.addGroup(adminLogin, groupId);
	}

	@Override
	public boolean removeGroup(String adminLogin, int groupId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean associateStudToGroup(String adminLogin, String studentLogin, int groupId) {
		return userDB.associateStudToGroup(adminLogin, studentLogin, groupId);
	}

	@Override
	public String[] usersToString() {
		return userDB.usersToString();
	}

	@Override
	public String[] usersLoginToString() {
		return userDB.usersLoginToString();
	}

	@Override
	public String[] studentsLoginToString() {
		return userDB.usersToString();	
	}

	@Override
	public String[] groupsIdToString() {
		return userDB.groupsIdToString();
	}

	@Override
	public String[] groupsToString() {
		return userDB.groupsToString();
		
	}

	@Override
	public boolean loadDB() {
		return userDB.loadDB();
	}

	@Override
	public boolean saveDB() {
		return saveDB();
	}

	public UserDB getUserDB() {
		return userDB;
	}

	public void setUserDB(UserDB userDB) {
		this.userDB = userDB;
	}
	
	

}

