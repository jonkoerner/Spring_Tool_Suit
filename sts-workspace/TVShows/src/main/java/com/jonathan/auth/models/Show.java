package com.jonathan.auth.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="shows")
public class Show {
	@Id
	@GeneratedValue
	private int id;
	@Size
	private String title;
	@Size
	private String network;
	@Size
	private double rating;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_shows", 
			joinColumns = @JoinColumn(name = "show_id"), 
			inverseJoinColumns = @JoinColumn(name = "user_id")
			)
	private List<User> users;
	public Show() {
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
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
	public void setRating(double rating) {
		this.rating = rating;
	}
}