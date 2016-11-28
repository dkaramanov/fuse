package com.estafet.fuse.training.bankx_models.bean;

import java.io.Serializable;

public class Account implements Serializable {
	
	private String name;
	
	private Double value;
	
	private String currency;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
