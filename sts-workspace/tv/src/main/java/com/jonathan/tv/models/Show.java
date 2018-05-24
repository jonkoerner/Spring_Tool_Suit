package com.jonathan.tv.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.jonathan.tv.models.*;

@Entity
@Table(name="shows")
public class Show {
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String title;
	@NotNull
	private String network;
	@Size(min=1, max=5, message="rating must be between 1 and 5")
	private int rating;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern = "MMMM-dd-yyyy")
	private Date createdAt;
	
	@DateTimeFormat(pattern = "MMMM-dd-yyyy")
	private Date updatedAt;
	
	@OneToMany(mappedBy="show", fetch=FetchType.LAZY)
	private List<Rating> ratings;
	
	public Show() {
		super();
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
   	@JoinColumn(name="user_id")
	private User creator;


	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public double getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
}
