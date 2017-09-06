package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;






import beans.Feedback;
import entities.FeedbackEntity;
import resource.HibernateUtility;
import resource.MyShopLogger;



public class FeedbackDAOImpl  implements FeedbackDAO{
	SessionFactory sessionFactory = HibernateUtility.createSessionFactory();
	

	@Override
	public void submitFeedback(Feedback fb) throws Exception {
		Session session = sessionFactory.openSession();
		try {
			FeedbackEntity fe = new FeedbackEntity();			
			session.beginTransaction();
			fe.setFeedbackComment(fb.getFeedbackComment());
			fe.setUserId(fb.getUserId());
//			System.out.println("FeedbackDAOImpl");
//			System.out.println(fe.getUserId());
//			System.out.println(fe.getFeedbackComment());
			session.save(fe);
			session.getTransaction().commit();
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"submitFeedback", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		}finally {
			session.close();
//			sessionFactory.close();
		}
		
	}

	@Override
	public List<Feedback> getAllFeedback() throws Exception {
		List<Feedback> fbList = new ArrayList<Feedback>();
		Session session = sessionFactory.openSession();		
		try {
//			Query query = session.createQuery("Select f from FeedbackEntity f where f.feedbackId =?");
			Query query = session.createQuery("Select f from FeedbackEntity f");
			@SuppressWarnings("unchecked")
			List<FeedbackEntity> felist = query.list();			
			if (felist.size() != 0) {
				Feedback fb = null;
				for (FeedbackEntity fe : felist) {
					fb = new Feedback();
					fb.setFeedbackId(fe.getFeedbackId());
					fb.setFeedbackComment(fe.getFeedbackComment());
					fb.setUserId(fe.getUserId());
					fbList.add(fb);
				}
			}
			
		} catch (Exception exception) {
			MyShopLogger.logError(this.getClass().getName(),
					"getLoginEntityDetailsByUserId", exception.getMessage());
			throw new Exception("DAO.TECHNICAL_ERROR");
		} finally {
			session.close();
//			sessionFactory.close();
		}
		return fbList;
	}
	
//	public static void main(String[] args) {
//		FeedbackDAOImpl a = new FeedbackDAOImpl();
//		a.getComment(10000);
//	}
//	
}
