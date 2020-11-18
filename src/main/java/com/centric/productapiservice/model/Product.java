package com.centric.productapiservice.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import com.centric.productapiservice.common.convertor.StringToListConverter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product extends AbstractBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column
    @NotNull(message = "Name cannot be null")
	private String name;

	@Column
	private String description;
	
	@Column
	private String brand;

	@Column
	@NotNull(message = "Category cannot be null")
	private String category;
	
	// @ElementCollection - we can use for separate tag tables
	@Convert(converter = StringToListConverter.class)
	private List<String> tags = new ArrayList<String>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
