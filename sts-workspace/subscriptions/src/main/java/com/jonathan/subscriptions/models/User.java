package com.jonathan.subscriptions.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="User")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Email
	@Size(min=8, max=255, message="Email must be at least 8 charaters long")
	private String email;
	private Boolean admin;
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
	@ManyToMany
	@JoinTable(
			name = "users_subscriptions",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "subscription_id")
			)
	private List<User> users;
	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public Subscription getSubscriptions() {
		return subscriptions;
	}
	public void setSubscriptions(Subscription subscriptions) {
		this.subscriptions = subscriptions;
	}
	public User() {
		super();
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
   	@JoinColumn(name="subscription_id")
	private Subscription subscriptions;
	
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
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
}
