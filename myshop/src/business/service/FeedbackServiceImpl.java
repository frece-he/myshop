package business.service;

import java.util.List;

import resource.Factory;
import resource.MyShopLogger;
import beans.Feedback;

public class FeedbackServiceImpl implements FeedbackService {

	@Override
	public void submitFeedback(Feedback fb) throws Exception {
		try {
			Factory.createFeedbackDAO().submitFeedback(fb);
		} catch (Exception exception) {			
			MyShopLogger.logError(this.getClass().getName(), "submitFeedback",
					exception.getMessage());
			throw exception;
		}
		
	}

	@Override
	public List<Feedback> viewAllFeedback() throws Exception {
		try {
			return Factory.createFeedbackDAO().getAllFeedback();
		} catch (Exception exception) {			
			MyShopLogger.logError(this.getClass().getName(), "viewAllFeedback",
					exception.getMessage());
			throw exception;
		}
		
	}

}
