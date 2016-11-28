package com.estafet.fuse.training.bankx_models.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AccountsWrapper implements Serializable {

	private List<IbanSingleReportEntity> accounts = new ArrayList<IbanSingleReportEntity>();

	public List<IbanSingleReportEntity> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<IbanSingleReportEntity> accounts) {
		this.accounts = accounts;
	}

}
