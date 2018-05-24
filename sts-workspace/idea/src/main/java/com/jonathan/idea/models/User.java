package com.jonathan.idea.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Email
	@Size(min=8, max=255, message="Email must be at least 8 charaters long")
	private String email;
	@Size(min=1, max=255, message="Username cannot be blank.")
	private String name;
	@Size(min=8, max=255, message="Password must be at least 8 characters long")
	private String password;
	
	@Transient
	@Size(min=8, max=255, message="Confirmation must be at least 8 charaters long")
	private String confirm; 
	
	@Column(updatable=false)
	@DateTimeFormat(pattern = "MMMM-DD-yyyy")
	private Date createdAt;
	
	@DateTimeFormat(pattern = "MMMM-DD-yyyy")
	private Date updatedAt;
	
	
	@OneToMany(mappedBy="creator",fetch=FetchType.LAZY)
	private List<Idea> ideas;
	
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
	private List<Liker> likers;
	
	public User() {
		super();
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
	

	public List<Idea> getIdeas() {
		return ideas;
	}
	public void setIdeas(List<Idea> ideas) {
		this.ideas = ideas;
	}
	

	public List<Liker> getLikes() {
		return likers;
	}
	public void setLikes(List<Liker> likers) {
		this.likers = likers;
	}
	
	public Long getId() {
	return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
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


	
}
