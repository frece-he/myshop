package resource;


import business.service.FeedbackService;
import business.service.FeedbackServiceImpl;
import business.service.ForgotPosswordService;
import business.service.ForgotPosswordServiceImpl;
import business.service.LoginService;
import business.service.LoginServiceImpl;
import business.service.OrderService;
import business.service.OrderServiceImpl;
import business.service.ProductService;
import business.service.ProductServiceImpl;
import business.service.RegistrationService;
import business.service.RegistrationServiceImpl;
import dao.FeedbackDAO;
import dao.FeedbackDAOImpl;
import dao.ForgotPasswordDAO;
import dao.ForgotPasswordDAOImpl;
import dao.LoginDAO;
import dao.LoginDAOImpl;
import dao.OrderDAO;
import dao.OrderDAOImpl;
import dao.ProductDAO;
import dao.ProductDAOImpl;
import dao.RegistrationDAO;
import dao.RegistrationDAOImpl;



public class Factory {
	

	
	public static RegistrationService createRegistrationService()	{
		return new RegistrationServiceImpl();
	}
	public static RegistrationDAO createRegistrationDAO(){
		return new RegistrationDAOImpl();
	}
	
	public static LoginService createLoginService(){
		return new LoginServiceImpl();
	}
	public static LoginDAO createLoginDAO(){
		return new LoginDAOImpl();
	}
	public static ForgotPosswordService createForgotPasswordService(){
		return new ForgotPosswordServiceImpl();
	}
	public static ForgotPasswordDAO createForgotPasswordDAO(){
		return new ForgotPasswordDAOImpl();
	}
	public static ProductService createProductService(){
		return new ProductServiceImpl();
	}
	public static ProductDAO createProductDAO(){
		return new ProductDAOImpl();
	}
	public static OrderService createOrderService(){
		return new OrderServiceImpl();
	}
	public static OrderDAO createOrderDAO(){
		return new OrderDAOImpl();
	}
	public static FeedbackService createFeedbackService(){
		return new FeedbackServiceImpl();
	}
	public static FeedbackDAO createFeedbackDAO(){
		return new FeedbackDAOImpl();
	}

}
