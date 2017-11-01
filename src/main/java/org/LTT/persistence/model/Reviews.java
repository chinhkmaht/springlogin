package org.LTT.persistence.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Reviews")
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String note;
  
    
    @NotEmpty(message=" plase choise rank")
    private String rank;
   
    @NotEmpty(message=" Please provide your from date")
    private String reviewsForm;
    
  
    @NotEmpty(message=" Please provide your to date")
    private String reviewTo;
    
    private Long userIdReview;
    private String userReviewName;
    private String username;
    public String getUserReviewName() {
		return userReviewName;
	}
	public void setUserReviewName(String userReviewName) {
		this.userReviewName = userReviewName;
	}
	public String getUsername() {
		return username;
	}
	public Long getUserIdrv() {
		return userIdrv;
	}
	public void setUserIdrv(Long userIdrv) {
		this.userIdrv = userIdrv;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	private Long userIdrv;
    private boolean status;
    private Date modified;
    private Date created;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	public String getReviewsForm() {
		return reviewsForm;
	}
	public void setReviewsForm(String reviewsForm) {
		this.reviewsForm = reviewsForm;
	}
	public String getReviewTo() {
		return reviewTo;
	}
	public void setReviewTo(String reviewTo) {
		this.reviewTo = reviewTo;
	}
	public Long getUserIdReview() {
		return userIdReview;
	}
	public void setUserIdReview(Long userIdReview) {
		this.userIdReview = userIdReview;
	}

	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
}
