package com.estafet.fuse.training.bankx_routes.processor;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import com.estafet.fuse.training.bankx_models.bean.AccountsWrapper;
import com.estafet.fuse.training.bankx_models.bean.IbanSingleReportEntity;

public class IbanAggregationStrategy implements AggregationStrategy {

	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		Exchange result;
		AccountsWrapper wrapper;
		
		if (oldExchange == null) {
			wrapper = new AccountsWrapper();
			result = newExchange;
		} else {
			wrapper = oldExchange.getIn().getBody(AccountsWrapper.class);
			result = oldExchange;
		}
		
		IbanSingleReportEntity entity = newExchange.getIn().getBody(IbanSingleReportEntity.class);
		wrapper.getAccounts().add(entity);
		newExchange.getIn().setBody(wrapper);
		
		return result;
	}
}