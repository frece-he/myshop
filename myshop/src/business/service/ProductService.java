package business.service;

import java.util.List;

import beans.ProComment;
import beans.Product;

public interface ProductService {
	public String checkProduct(String proName)throws Exception;
	
	public Product getNewProduct(String proName) throws Exception;
	public List<Product>getProductsWithId(String proName,Integer proNum) throws Exception;
	public String doPurchase(List<Product> prolist, String userId)throws Exception;
	public void addToCart(List<Product> prolist, String userId)throws Exception;
	public Integer getStock(String proName)throws Exception;
	
	public void addStock(String proName, Integer stock) throws Exception;
	public List<Product>getIndexProducts() throws Exception;
	public void changeIndexProducts(Integer proNo, String proName) throws Exception;
	public List<String>getAllProducts() throws Exception;	
	public void releaseNewPro(Product pro, Integer stock)throws Exception;
	
	public void addComment(ProComment comment)throws Exception;
	public List<ProComment> checkComment()throws Exception;
}
