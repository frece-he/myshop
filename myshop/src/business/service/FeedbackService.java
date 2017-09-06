package business.service;

import java.util.List;

import beans.Feedback;

public interface FeedbackService {
	public void submitFeedback(Feedback fb) throws Exception;
	public List<Feedback> viewAllFeedback() throws Exception;
}
