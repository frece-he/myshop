package entities;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCT")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer proId;
	private String proName;
	private String proCategory;
	private Double proPrice;
	private String imgUrl;
	private String proDes;
	private Date selledDate;
	private String proLink;
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
		return proPrice;
	}
	public void setPrice(Double price) {
		this.proPrice = price;
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
	
}
