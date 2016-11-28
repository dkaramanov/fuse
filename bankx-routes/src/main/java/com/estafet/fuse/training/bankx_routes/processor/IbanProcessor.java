package com.estafet.fuse.training.bankx_routes.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.estafet.fuse.training.bankx_models.bean.IbanSingleReportEntity;

public class IbanProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		String iban = exchange.getIn().getBody().toString();
		IbanSingleReportEntity ibanEntity = new IbanSingleReportEntity();
		ibanEntity.setIban(iban);
		
		exchange.getIn().setBody(ibanEntity);
	}
}
