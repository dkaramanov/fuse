package com.estafet.fuse.training.bankx_routes.processor;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import com.estafet.fuse.training.bankx_models.bean.Account;
import com.estafet.fuse.training.bankx_models.bean.IbanSingleReportEntity;

public class IbanEnrichStrategy  implements AggregationStrategy {
	
	public Exchange aggregate(Exchange original, Exchange resource) {
		Account account = resource.getIn().getBody(Account.class);
	
		IbanSingleReportEntity entity = original.getIn().getBody(IbanSingleReportEntity.class);
		entity.setName(account.getName());
		entity.setBalance(Double.valueOf(account.getValue()));
		entity.setCurrency(account.getCurrency());
		
		return original;
	}
}
