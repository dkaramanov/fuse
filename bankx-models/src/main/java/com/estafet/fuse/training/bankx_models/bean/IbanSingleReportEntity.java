package com.estafet.fuse.training.bankx_models.bean;

import java.io.Serializable;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",", crlf="WINDOWS", generateHeaderColumns = true)
public class IbanSingleReportEntity implements Serializable {
	
	@DataField(pos = 2)
	private String Iban;
	@DataField(pos = 1)
	private String name;
	@DataField(pos = 3, precision = 2)
	private Double balance;
	@DataField(pos = 4)
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
