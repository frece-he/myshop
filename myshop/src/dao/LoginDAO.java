package dao;

import java.util.List;

import beans.Admin;
import beans.Login;



public interface LoginDAO {

	public Login getLogin(String userId) throws Exception;
	public Admin getAdminLogin(String userId) throws Exception;
	public void saveLoginDetails(Login login) throws Exception ;
	public void unlockAccount(List<String> userIdList) throws Exception;
	public void freezeAccount(String userId) throws Exception;
	public void lockAccount(String userId) throws Exception;
	public List<Login> getUnusualAccount() throws Exception;
	public List<Login> getAllAccount() throws Exception;
}
