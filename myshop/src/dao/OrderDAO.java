package dao;

import java.util.List;

import beans.Order;
import beans.Product;

public interface OrderDAO {
	public void addToCart(List<Product> prolist, String userId) throws Exception;	
	public void removeFromCart(List<Product> prolist, String userId) throws Exception;	
	public void clearCart(String userId)throws Exception;	
	public void settlementCart(String userId) throws Exception;	
	public String generateOrder(List<Product> prolist, String userId) throws Exception;	
	public void changeToSent(String orderId)throws Exception;
	public void finishOrder(String orderId)throws Exception;
	public void closeOrder(String orderId)throws Exception;
	public List<Product> checkCart(String userId)throws Exception;
	public List<Order>checkOrderById(String userId)throws Exception;
	public List<Order>checkAllUnfinishedOrders()throws Exception;
	public List<Order>checkAllFinishedOrders()throws Exception;
}
