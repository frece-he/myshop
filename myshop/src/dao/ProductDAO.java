package dao;

import java.util.List;

import beans.ProComment;
import beans.Product;

public interface ProductDAO {	
	public Product getProductWithoutId(String proName) throws Exception;
	public List<Product> getProductsWithId(String proName,Integer proNum) throws Exception;
	public String doPurchase(List<Product> prolist, String userId) throws Exception;
	public List<Product> getproductById(List<String> proIdList)throws Exception;
	public void returnToStock(List<String> proIdList)throws Exception;
	public void addProductsToCart(List<Product> prolist)throws Exception;
	public void addProductToStock(String proName, Integer proNum)throws Exception;
//	public void releaseNewProduct(List<Product> proList, List<Integer> proNumList)throws Exception;
	public Integer getStock(String proName) throws Exception;
	
	public void addStock(String proName, Integer stock) throws Exception;
	public List<Product>getIndexProducts() throws Exception;
	public void changeIndexProducts(Integer proNo, Product pro) throws Exception;
	public List<String>getAllProducts() throws Exception;	
	public void releaseNewPro(Product pro, Integer stock)throws Exception;
	
	public void addComment(ProComment comment)throws Exception;
	public List<ProComment> checkComment()throws Exception;
}
