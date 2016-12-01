package com.estafet.fuse.training.bankx_models.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.OneToMany;

@CsvRecord(separator = ",", crlf="WINDOWS", generateHeaderColumns = true)
public class AccountsWrapper implements Serializable {

	@OneToMany(mappedTo = "com.estafet.fuse.training.bankx_models.bean.IbanSingleReportEntity")
	private List<IbanSingleReportEntity> accounts = new ArrayList<IbanSingleReportEntity>();

	public List<IbanSingleReportEntity> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<IbanSingleReportEntity> accounts) {
		this.accounts = accounts;
	}

}
