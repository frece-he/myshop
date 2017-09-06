package business.service;

import java.util.List;

import beans.Admin;
import beans.Login;



public interface LoginService {
	public Login getLogin(String userId) throws Exception;
	public void saveLoginDetails(Login login) throws Exception ;
	public Login doLogin(Login Login)throws Exception;
	public void lockAccount(String userId) throws Exception ;
	public void freezeAccount(String userId) throws Exception ;
	public void unlockAccount(List<String> userId) throws Exception ;
	public String adminLogin(Admin admin)throws Exception;
	public List<Login> getUnusualAccount()throws Exception;
	public List<Login> getAllAccount()throws Exception;
}
