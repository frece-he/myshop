package dao;




import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;








import entities.AdminEntity;
import entities.LoginEntity;
import resource.HibernateUtility;
import resource.MyShopLogger;
import beans.Admin;
import beans.Login;

public class LoginDAOImpl implements LoginDAO{
	SessionFactory sessionFactory = HibernateUtility.createSessionFactory();

	public LoginEntity getLoginEntityByUserId(String userId) throws Exception {
		Session session = sessionFactory.openSession();
		LoginEntity le = null;
		try {
			le = (LoginEntity) session.get(LoginEntity.class, userId);
		}catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"getLoginEntityByUserId", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally{
			session.close();
		}
		return le;
	}



	@Override
	public Login getLogin(String userId) throws Exception {

		Login login = null;
		LoginEntity le = null;		
		try {
			le = getLoginEntityByUserId(userId);
			if(le == null){
				return null;
			}
			login = new Login();
			login.setUserId(userId);
			login.setPassword(le.getPassword());
			login.setStatus(le.getStatus());

		}catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"getLogin", exception.getMessage());			
			throw exception;			
		}
		return login;
	}

	@Override
	public Admin getAdminLogin(String userId) throws Exception {
		Session session = sessionFactory.openSession();
		Admin admin = null;
		AdminEntity ae = null;		
		try {
			ae = (AdminEntity) session.get(AdminEntity.class, userId);
			if(ae != null){
				admin = new Admin();
				admin.setAdminId(ae.getAdminId());
				admin.setPassword(ae.getPassword());
			}
			else {
				throw new Exception("无效的用户名");
			}

		}catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"getAdminLogin", exception.getMessage());			
			throw exception;			
		}
		return admin;
	}
	
	@Override
	public void saveLoginDetails(Login login) throws Exception {	
		Session session = sessionFactory.openSession();
		try {
			LoginEntity le = new LoginEntity();
			le.setUserId(login.getUserId());
			le.setPassword(login.getPassword());
			le.setStatus(login.getStatus());
			session.beginTransaction();
			session.save(le);
			session.getTransaction().commit();

		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"saveLoginDetails", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally{
			session.close();
		}
	}

	@Override
	public void unlockAccount(List<String> userIdList) throws Exception {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		LoginEntity le = null;
		try {
			
			for (String userId : userIdList) {
				le = getLoginEntityByUserId(userId);
				if (le != null) {					
					
					le.setStatus("正常");
					session.update(le);
					
				}else {
					throw new Exception(userId + " 用户名不存在");
				}
			}
			
			
		}catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"unlockAccount", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally{
			session.getTransaction().commit();	
			session.close();
//			sessionFactory.close();
		}

	}

	@Override
	public void freezeAccount(String userId) throws Exception {
		Session session = sessionFactory.openSession();
		try {
			LoginEntity le = getLoginEntityByUserId(userId);

			if (le != null) 
			{				
				session.beginTransaction();
				le.setStatus("冻结");
				session.update(le);
				session.getTransaction().commit();				
			}
		}catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"lockAccount", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally{
			session.close();
//			sessionFactory.close();
		}

	}

	@Override
	public void lockAccount(String userId) throws Exception {
		Session session = sessionFactory.openSession();
		try {
			LoginEntity le = getLoginEntityByUserId(userId);

			if (le != null) 
			{				
				session.beginTransaction();
				le.setStatus("锁定");
				session.update(le);
				session.getTransaction().commit();				
			}
		}catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"lockAccount", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally{
			session.close();
//			sessionFactory.close();
		}

	}



	@Override
	public List<Login> getUnusualAccount() throws Exception {
		List<Login> logList = null;
		Session session = sessionFactory.openSession();
		try {
			Query q = session.createQuery("select c from LoginEntity c where c.status ='冻结' or c.status ='锁定'");
			@SuppressWarnings("unchecked")
			List<LoginEntity> lelist = q.list();
			if(lelist != null && !lelist.isEmpty()){
				logList = new ArrayList<Login>();
				Login log = null;
				for (LoginEntity le : lelist) {
					log = new Login();
					log.setUserId(le.getUserId());
					log.setPassword(le.getPassword());
					log.setStatus(le.getStatus());
					log.setSelected(false);
					logList.add(log);
				}
			}
			else {
				lelist = new ArrayList<LoginEntity>();
			}
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"getUnusualAccount", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		return logList;
	}


	@Override
	public List<Login> getAllAccount() throws Exception {
		List<Login> logList = null;
		Session session = sessionFactory.openSession();
		try {
			Query q = session.createQuery("select c from LoginEntity c ");
			@SuppressWarnings("unchecked")
			List<LoginEntity> lelist = q.list();
			if(lelist != null && !lelist.isEmpty()){
				logList = new ArrayList<Login>();
				Login log = null;
				for (LoginEntity le : lelist) {
					log = new Login();
					log.setUserId(le.getUserId());
					log.setPassword(le.getPassword());
					log.setStatus(le.getStatus());
					log.setSelected(false);
					logList.add(log);
				}
			}
			else {
				lelist = new ArrayList<LoginEntity>();
			}
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"getAllAccount", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		return logList;
	}





}
