package com.bidding.system.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item implements Serializable {

	private static final long serialVersionUID = 1661121319L;

	@Id
	@Column(length = 15)
	private String itemCode;
	@Column(length = 50)
	private String name;
	@Column(length = 255)
	private String description;

	public Item() {
		super();
	}

	public Item(String itemCode, String name, String description) {
		this.itemCode = itemCode;
		this.name = name;
		this.description = description;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

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

	@Override
	public String toString() {
		return "Product [itemCode=" + itemCode + ", name=" + name + ", description=" + description + "]";
	}

}
