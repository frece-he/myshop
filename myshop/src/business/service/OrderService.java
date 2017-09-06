package business.service;

import java.util.List;

import beans.Order;
import beans.Product;

public interface OrderService {
	public List<Product> checkCart(String userId)throws Exception;
	public void removeFromCart(List<Product> prolist, String userId) throws Exception;
	public void clearCart(String userId)throws Exception;	
	public String settlementCart(String userId) throws Exception;	
	public void changeToSent(String orderId)throws Exception;
	public void finishOrder(String orderId)throws Exception;
	public void closeOrder(Order order)throws Exception;
	public List<Order>checkOrderById(String userId)throws Exception;
	public List<Order>checkAllUnfinishedOrders()throws Exception;
	public List<Order>checkAllFinishedOrders()throws Exception;
}
