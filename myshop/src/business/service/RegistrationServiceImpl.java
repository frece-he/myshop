package business.service;

import resource.Factory;
import resource.MyShopLogger;
import beans.Registration;

public class RegistrationServiceImpl implements RegistrationService {

	@Override
	public String checkUserId(String userId) throws Exception {
		String checked = null;
		try {			

			 checked = Factory.createRegistrationDAO().checkUserId(userId);
			 
			 if (checked.equals("Found")) {
				throw new Exception("用户名已存在");
			}
		} catch (Exception exception) {

			MyShopLogger.logError(this.getClass().getName(), "checkUserId",
					exception.getMessage());
			throw exception;
		}
		return checked;
	}

	@Override
	public Registration registerNewMember(Registration reg) throws Exception {

		try {
			return Factory.createRegistrationDAO().registerNewMember(reg);
		} catch (Exception exception) {

			MyShopLogger.logError(this.getClass().getName(), "registerNewMember",
					exception.getMessage());
			throw exception;
		}

	}

	@Override
	public Registration getProfileDetails(String userId) throws Exception {
		try {
			return Factory.createRegistrationDAO().getProfileDetails(userId);
		} catch (Exception exception) {

			MyShopLogger.logError(this.getClass().getName(), "getProfileDetails",
					exception.getMessage());
			throw exception;
		}
	}

	@Override
	public void editProfileDetails(Registration reg) throws Exception {
		
		try {
			Factory.createRegistrationDAO().editProfileDetails(reg);;
		} catch (Exception exception) {

			MyShopLogger.logError(this.getClass().getName(), "editProfileDetails",
					exception.getMessage());
			throw exception;
		}
	}

	@Override
	public String getSecurityQstnforUser(String userId) throws Exception {
		try {
			return Factory.createRegistrationDAO().getSecurityQstnforUser(userId);
		} catch (Exception exception) {

			MyShopLogger.logError(this.getClass().getName(), "getSecurityQstnforUser",
					exception.getMessage());
			throw exception;
		}
	}

	@Override
	public Boolean validateSecurityAnswer(String userId, String answer)
			throws Exception {
		try {
			return Factory.createRegistrationDAO().validateSecurityAnswer(userId, answer);
		} catch (Exception exception) {

			MyShopLogger.logError(this.getClass().getName(), "validateSecurityAnswer",
					exception.getMessage());
			throw exception;
		}
	}



}
