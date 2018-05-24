package com.jonathan.auth.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="courses")
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    @Size(min=1, message="Course name is reqired")
    private String name;
    @Size(min=1, message="Instructor name is reqired")
    private String instructor;
    @Size(min=1, message="Class limit must be greater than 1")
    private int classLimit;
    private int signups = 0;
    @ManyToMany(mappedBy = "courses")
    private List<User> users;
    
    public Course() {
    	this.signups =+ 1;
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
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

	public int getSignups() {
		return signups;
	}

	public void setSignups(int signups) {
		this.signups = signups;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

}
