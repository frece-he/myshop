package dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.RegistrationEntity;
import resource.Factory;
import resource.HibernateUtility;
import resource.MyShopLogger;
import beans.Login;
import beans.Registration;

public class RegistrationDAOImpl implements RegistrationDAO {
	private SessionFactory sessionFactory = HibernateUtility.createSessionFactory();

	public RegistrationEntity getRegistrationEntityByUserId(String userId) throws Exception{
		Session session = sessionFactory.openSession();
		RegistrationEntity re = null;
		try {
			
			Query q = session.createQuery("select c from RegistrationEntity c where c.userId=?");
			q.setParameter(0, userId);
			List<RegistrationEntity> relist = q.list();
			if(relist!= null && relist.size() != 0){
				re = relist.get(0);
			}
			
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"getRegistrationEntityByUserId", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}finally{
			session.close(); 
		}		
		return re;

	}

	@Override
	public String checkUserId(String userId) throws Exception {

		RegistrationEntity re = null;
		String checkUserId = null;
		try {			
			re = getRegistrationEntityByUserId(userId);
			if(re == null){				
				checkUserId="Not Found";
			}
			else {
				checkUserId="Found";
			}

		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"checkUser", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		return checkUserId;
	}

	@Override
	public Registration registerNewMember(Registration reg) throws Exception {
		Session session = sessionFactory.openSession();
		
		Login log = new Login();
		log.setUserId(reg.getUserId());
		log.setPassword(reg.getPassword());
		log.setStatus("正常");
		try {
			session.beginTransaction();		
			RegistrationEntity re = new RegistrationEntity();
			re.setUserId(reg.getUserId());
			re.setSecurityQuestion(reg.getSecurityQuestion());
			re.setSecurityAnswer(reg.getSecurityAnswer());
			
			re.setCustomerName(reg.getCustomerName());
			re.setPhoneNumber(reg.getPhoneNumber());
			re.setAddress(reg.getAddress());		
			re.setDateOfRegist(new Date());
			re.setCertification("否");
			session.save(re);
			session.getTransaction().commit();
			Factory.createLoginDAO().saveLoginDetails(log);
			reg.setRegistrationId(re.getRegistrationId());
//			System.out.println(reg.getRegistrationId());
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"registerNewMember", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally{
			if(session != null && session.isOpen()){
				session.close();
			}
		
//			sessionFactory.close();
		}
		return reg;
	}

	@Override
	public Registration getProfileDetails(String userId) throws Exception {
		
			RegistrationEntity re = null;
			Registration r = new Registration();
			try {
				re = getRegistrationEntityByUserId(userId);
				if(re != null){
					
					r.setRegistrationId(re.getRegistrationId());
					r.setUserId(userId);
					r.setCustomerName(re.getCustomerName());
					r.setGender(re.getGender());
					r.setSecurityQuestion(re.getSecurityQuestion());
					r.setSecurityAnswer(re.getSecurityAnswer());
					r.setPhoneNumber(re.getPhoneNumber());				
					r.setDateOfRegist(re.getDateOfRegist());
					r.setCertification(re.getCertification());		
					
					r.setEmailAddress(re.getEmailAddress());
					r.setAddress(re.getAddress());
					r.setDateOfBirth(re.getDateOfBirth());					
				}else{
					throw new Exception("无效的用户名");
				}
				
			} catch (Exception exception) {
				MyShopLogger.logError(this.getClass().getName(),
						"getProfileDetails", exception.getMessage());
				throw exception;
			}
			return r;
		}

	@Override
	public void editProfileDetails(Registration reg) throws Exception {
		Session session = sessionFactory.openSession();
		try {
			RegistrationEntity re = (RegistrationEntity)session.get(RegistrationEntity.class, reg.getRegistrationId());		
			session.beginTransaction();
			re.setCustomerName(reg.getCustomerName());
			re.setSecurityQuestion(reg.getSecurityQuestion());
			re.setSecurityAnswer(reg.getSecurityAnswer());
			re.setGender(reg.getGender());
			re.setPhoneNumber(reg.getPhoneNumber());		
			re.setCertification(reg.getCertification());	
			re.setEmailAddress(reg.getEmailAddress());
			re.setAddress(reg.getAddress());
			re.setDateOfBirth(reg.getDateOfBirth());				
			session.update(re);
			session.getTransaction().commit();
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"editProfileDetails", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		finally{
			session.close();
//			sessionFactory.close();
		}

	}

	@Override
	public String getSecurityQstnforUser(String userId) throws Exception {
		String question = null;
		RegistrationEntity re = null;
		try {
			re = getRegistrationEntityByUserId(userId);
			if(re != null){
				question = re.getSecurityQuestion();
			}
			else{
				throw new Exception("Invalid UserId");
			}
			
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"getSecurityQstnforUser", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}
		
		return question;
	}

	@Override
	public Boolean validateSecurityAnswer(String userId, String answer)
			throws Exception {
		
		Boolean valid = null;
		RegistrationEntity re = null;
		try {
			re = getRegistrationEntityByUserId(userId);
			if(re != null){
				if(re.getSecurityAnswer().equals(answer)){
					valid = true;
				}
				else {
					valid = false;
				}
			}
			else {
				throw new Exception("用户名不存在");
			}
			
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"validateSecurityAnswer", exception.getMessage());
			throw exception;
		}
		return valid;
	}



}
