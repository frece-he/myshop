package business.service;

import beans.Registration;


public interface RegistrationService {
	public String checkUserId(String userId) throws Exception ;
	public Registration registerNewMember(Registration reg) throws Exception;	
	public Registration getProfileDetails(String userId) throws Exception;	
	public void editProfileDetails(Registration reg) throws Exception;
	public String getSecurityQstnforUser(String userId) throws Exception;
	public Boolean validateSecurityAnswer(String userId, String answer) throws Exception;
}
