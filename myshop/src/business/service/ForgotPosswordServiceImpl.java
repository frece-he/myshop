package business.service;

import dao.ForgotPasswordDAO;
import resource.Factory;
import resource.MyShopLogger;


public class ForgotPosswordServiceImpl implements ForgotPosswordService{
	
	@Override
	public void checkUserId(String userId) throws Exception {
		try {

			ForgotPasswordDAO dao = Factory.createForgotPasswordDAO();

			if (dao.checkUserId(userId).equals("Not Found")) {
				throw new Exception("无效的用户名");
			} 			
			if(dao.checkStatus(userId).equals("锁定")){					
				throw new Exception("账户已被锁定");			
		}
			
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(), "checkUserId",
					exception.getMessage());
			throw exception;
		}		
		
		
		
	}
	
	
	@Override
	public String changePassword(String userId, String password)
			throws Exception {
		try {
			
			
			ForgotPasswordDAO dao = Factory.createForgotPasswordDAO();
			
//			Registration to = new Registration();
//			to = dao.getProfileDetails(userId);
//			if (!(answer.equalsIgnoreCase(to.getSecurityAnswer()))) 
//			{
//				throw new Exception("SECURITY_ANSWER_NOT_MATCHING_EXCEPTION");
//			}
			
			
			dao.changePassword(userId, password);
			
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "changePassword", e
					.toString());
			throw e;
		}	
		
		
		return "success";
	}

	@Override
	public void lockAccount(String userId) throws Exception {

		try {
			Factory.createForgotPasswordDAO().lockAccount(userId);
			
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(), "lockAccount",
					exception.getMessage());
			throw exception;
		}
		
	}



	
	
}
