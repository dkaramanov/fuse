package com.estafet.fuse.training.bankx_models.bean;

import java.io.Serializable;
import java.util.List;

public class IbanWrapper implements Serializable {
	
	private List<String> ibans;

	public void setIbans(List<String> ibans) {
		this.ibans = ibans;
	}
	
	public List<String> getIbans() {
		return ibans;
	}
}
