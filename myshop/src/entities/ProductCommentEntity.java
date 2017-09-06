package entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PROCOMMENT")
public class ProductCommentEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer commentId;
	private String userId;
	@Column(name="COMMENT")
	private String commentCon;
	
	
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCommentCon() {
		return commentCon;
	}
	public void setCommentCon(String commentCon) {
		this.commentCon = commentCon;
	};
	
}
