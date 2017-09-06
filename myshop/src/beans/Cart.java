package beans;

import java.util.List;

public class Cart {
	private String userId;
	private List<Integer>proIdList;
	private List<Product> productList;
	private Integer totalNum;
	private Double totalPrice;	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<Integer> getProIdList() {
		return proIdList;
	}
	public void setProIdList(List<Integer> proIdList) {
		this.proIdList = proIdList;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
