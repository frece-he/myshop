package dao;


import javax.persistence.PersistenceException;

import org.hibernate.Session;




import org.hibernate.SessionFactory;

import beans.Registration;
import resource.Factory;
import resource.HibernateUtility;
import resource.MyShopLogger;
import entities.LoginEntity;

public class ForgotPasswordDAOImpl implements ForgotPasswordDAO{
	SessionFactory sessionFactory = HibernateUtility.createSessionFactory();

	private LoginEntity getLoginEntityDetailsByUserId(String userId)
			throws Exception {
		Session session = sessionFactory.openSession();

		LoginEntity le = null;
		try {
			le = (LoginEntity) session.get(LoginEntity.class, userId);

		}catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"getLoginEntityDetailsByUserId", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.close();

		}

		return le;
	}


	@Override
	public String checkUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		LoginEntity le = null;
		String loginDetailsFound = null;
		try {
			le = getLoginEntityDetailsByUserId(userId);
			if (le == null) {
				loginDetailsFound = "Not Found";
			} else {
				loginDetailsFound = "Found";
			}
		}catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(), "checkUserId",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} 

		return  loginDetailsFound;

	}

	@Override
	public String checkStatus(String userId) throws Exception {
		LoginEntity le = null;
		String status = null;
		try {
			le = getLoginEntityDetailsByUserId(userId);
			if (le != null) {				
				status = le.getStatus();
			}
		}catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(), " checkStatus",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}


		return status;
	}



	@Override
	public Registration getProfileDetails(String userId) throws Exception {


		try {
			Registration reg = Factory.createRegistrationDAO().getProfileDetails(userId);
			
			return reg;
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(),
					"getProfileDetails", e.toString());
			throw e;
		} 
	}
	@Override
	public String changePassword(String userId, String newPassword)
			throws Exception {

		Session session = sessionFactory.openSession();
		try {

			LoginEntity en = new LoginEntity();

			session.getTransaction().begin();

			en = (LoginEntity) session.get(LoginEntity.class, userId);
			en.setPassword(newPassword);			

			session.getTransaction().commit();
		} catch (Exception e) {
			MyShopLogger.logError(this.getClass().getName(), "changePassword",
					e.toString());
			throw e;
		} finally {

			session.close();
//			sessionFactory.close();
		}

		return "success";
	}

	@Override
	public void lockAccount(String userId) throws Exception {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();

		try {
			LoginEntity le = getLoginEntityDetailsByUserId(userId);

			if (le != null) 
			{

				session.beginTransaction();
				le.setStatus("锁定");
				session.update(le);
				session.getTransaction().commit();

			}
		} catch (PersistenceException exception) {
			MyShopLogger.logError(this.getClass().getName(), "lockAccount",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");

		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(), "lockAccount",
					exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.close(); 

		}

	}
	


}
