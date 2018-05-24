package com.swilliams.ideas.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Idea {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	private String name;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern = "MMMM-dd-yyyy")
	private Date createdAt;
	
	@DateTimeFormat(pattern = "MMMM-dd-yyyy")
	private Date updatedAt;

//	REMEMBER YOU CALLED THIS 'CREATOR' NOT 'USER'
//	THIS USER CREATED THE IDEA
	@ManyToOne(fetch=FetchType.LAZY)
   	@JoinColumn(name="user_id")
	private User creator;
	
	@OneToMany(mappedBy="idea",fetch=FetchType.LAZY)
	private List<Liker> likers;
	
	public Idea() {
		super();
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

	public Idea(@NotNull String name, User creator) {
	super();
	this.name = name;
	this.createdAt = new Date();
	this.updatedAt = new Date();
	this.creator = creator;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<Liker> getLikers() {
		return likers;
	}

	public void setLikers(List<Liker> likers) {
		this.likers = likers;
	}
	
}
