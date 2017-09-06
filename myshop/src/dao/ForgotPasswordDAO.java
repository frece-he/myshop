package dao;

import beans.Registration;



public interface ForgotPasswordDAO {
	public String checkUserId(String userId) throws Exception;
	public String checkStatus(String userId) throws Exception;	
	public Registration getProfileDetails(String userId) throws Exception;	
	public String changePassword(String userId, String newPassword)throws Exception;	
	public void lockAccount(String userId) throws Exception;
	
}
