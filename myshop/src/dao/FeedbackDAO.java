package dao;

import java.util.List;

import beans.Feedback;

public interface FeedbackDAO {
	public void submitFeedback(Feedback fb) throws Exception;
	public List<Feedback>getAllFeedback() throws Exception;
}
