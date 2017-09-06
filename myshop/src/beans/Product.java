package beans;

import java.util.Date;


public class Product {
	private Integer proId;
	private String proName;
	private String proCategory;
	private Double price;
	private String imgUrl;
	private String proDes;
	private Date selledDate;
	private String proLink;
	private Boolean selected;
	private Integer proNum;

	public Integer getProId() {
		return proId;
	}
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProCategory() {
		return proCategory;
	}
	public void setProCategory(String proCategory) {
		this.proCategory = proCategory;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getProDes() {
		return proDes;
	}
	public void setProDes(String proDes) {
		this.proDes = proDes;
	}
	public Date getSelledDate() {
		return selledDate;
	}
	public void setSelledDate(Date selledDate) {
		this.selledDate = selledDate;
	}
	public String getProLink() {
		return proLink;
	}
	public void setProLink(String proLink) {
		this.proLink = proLink;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public Integer getProNum() {
		return proNum;
	}
	public void setProNum(Integer proNum) {
		this.proNum = proNum;
	}
	
}
