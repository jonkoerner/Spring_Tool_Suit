package com.jonathan.tv.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.jonathan.tv.models.Show;
import com.jonathan.tv.models.User;

@Entity
public class Rating {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
   	@JoinColumn(name="user_id")
	private User user;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern = "MMMM-dd-yyyy")
	private Date createdAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
   	@JoinColumn(name="show_id")
	private Show show;

	
	public Rating() {
		super();
		this.createdAt = new Date();
	}
	
	public Rating(User user, Show show) {
		super();
		this.createdAt = new Date();
		this.user = user;
		this.show = show;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}
}
