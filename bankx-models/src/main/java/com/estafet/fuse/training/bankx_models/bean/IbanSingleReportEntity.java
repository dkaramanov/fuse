package com.estafet.fuse.training.bankx_models.bean;

import java.io.Serializable;

public class IbanSingleReportEntity implements Serializable {
	
	private String Iban;
	private String name;
	private Double balance;
	private String currency;
	
	public String getIban() {
		return Iban;
	}
	
	public void setIban(String iban) {
		Iban = iban;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getBalance() {
		return balance;
	}
	
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
