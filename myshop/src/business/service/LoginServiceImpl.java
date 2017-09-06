package business.service;

import java.util.List;

import resource.Factory;
import resource.MyShopLogger;
import beans.Admin;
import beans.Login;

public class LoginServiceImpl implements LoginService {

	@Override
	public Login getLogin(String userId) throws Exception {
		Login log = null;
		try {
			log = Factory.createLoginDAO().getLogin(userId);
			if (log == null) {
				throw new Exception("无效的用户名");
			}

		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "getLogin",
					e.getMessage());
			throw e;
		}
		return log;
	}

	@Override
	public void saveLoginDetails(Login login) throws Exception {
		try {
			getLogin(login.getUserId());
			Factory.createLoginDAO().saveLoginDetails(login);

		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "saveLoginDetails",
					e.getMessage());
			throw e;
		}

	}
	

	@Override
	public Login doLogin(Login login) throws Exception {
		Login log = null;
		try {
			log = Factory.createLoginDAO().getLogin(login.getUserId());
				if(log != null){
					if(log.getStatus().equals("冻结") ){
						throw new Exception("您的账户已被冻结，请尝试忘记密码或联系管理员");
					}else if(log.getStatus().equals("锁定")){
						throw new Exception("您的账户已被锁定，请联系管理员");
					}else {						
						if(!log.getPassword().equals(login.getPassword())){
							throw new Exception("密码错误");
						}
					}					
				}else{
					throw new Exception("用户名不存在");
				}
			} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "doLogin",
					e.getMessage());
			throw e;
		}		
		
		return log;
	}


	@Override
	public void freezeAccount(String userId) throws Exception {
		try {
			if (Factory.createLoginDAO().getLogin(userId) != null) {
				Factory.createLoginDAO().freezeAccount(userId);
			}
			else{
				throw new Exception("用户名不存在");
			}
			

		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "lockAccount",
					e.getMessage());
			throw e;
		}

	}

	@Override
	public void lockAccount(String userId) throws Exception {
		try {
			if (Factory.createLoginDAO().getLogin(userId) != null) {
				Factory.createLoginDAO().lockAccount(userId);
			}
			else{
				throw new Exception("用户名不存在");
			}
			

		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "lockAccount",
					e.getMessage());
			throw e;
		}

	}
	
	@Override
	public void unlockAccount(List<String> userIdList) throws Exception {
		try {
		Factory.createLoginDAO().unlockAccount(userIdList);

		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "unlockAccount",
					e.getMessage());
			throw e;
		}		
	}

	@Override
	public String adminLogin(Admin admin) throws Exception {
		Admin ad = null;
		try {
			ad = Factory.createLoginDAO().getAdminLogin(admin.getAdminId());
				if(ad != null){
					if(!ad.getPassword().equals(admin.getPassword())){
						throw new Exception("请检查用户名或密码");
					}
				}
				else{
					throw new Exception("请检查用户名或密码");
				}
			} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "adminLogin",
					e.getMessage());
			throw e;
		}		
		
		
		return "success";
	}

	@Override
	public List<Login> getUnusualAccount() throws Exception {
		try {
			return Factory.createLoginDAO().getUnusualAccount();
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "getUnusualAccount",
					e.getMessage());
			throw e;
		}
		
	}

	@Override
	public List<Login> getAllAccount() throws Exception {
		try {
			return Factory.createLoginDAO().getAllAccount();
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "getAllAccount",
					e.getMessage());
			throw e;
		}
		
	}


}
