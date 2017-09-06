package business.service;



public interface ForgotPosswordService {
	public void checkUserId(String userId)throws Exception;
	public void lockAccount(String userId) throws Exception;
	public String changePassword(String userId, String password)throws Exception;
}
