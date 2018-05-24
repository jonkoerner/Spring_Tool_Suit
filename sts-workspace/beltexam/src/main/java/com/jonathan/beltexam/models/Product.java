package com.jonathan.beltexam.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Size(min=1, max=255, message="Product name cannot be blank.")
	private String name;
	@NotNull
	private Float amount;
	private Boolean sold = false; 
	@Column(updatable=false)
	@DateTimeFormat(pattern = "MMMM-DD-yyyy")
	private Date createdAt;
	@DateTimeFormat(pattern = "MMMM-DD-yyyy")
	private Date updatedAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
   	@JoinColumn(name="user_id")
	private User seller;
	
	@OneToOne(mappedBy="buyer")
    @JoinColumn(name="user_id")
    private User buyer;

	public Product() {
	}
	
	public Product(@NotNull String name, @NotNull Float amount , User seller) {
		super();
		this.name = name;
		this.amount = amount;
		this.createdAt = new Date();
		this.updatedAt = new Date();
		}

	public Boolean getSold() {
		return sold;
	}

	public void setSold(Boolean sold) {
		this.sold = sold;
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

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
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

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	
	}
