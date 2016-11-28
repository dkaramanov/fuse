package com.estafet.fuse.training.bankx_routes.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.estafet.fuse.training.bankx_accounts_api.AccountServiceApi;
import com.estafet.fuse.training.bankx_models.bean.Account;
import com.estafet.fuse.training.bankx_models.bean.IbanSingleReportEntity;

public class EnrichProcessor implements Processor {
	
	private AccountServiceApi accountEnricherService;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		if (accountEnricherService != null) {
			if (exchange.getIn().getBody() instanceof IbanSingleReportEntity) {
				IbanSingleReportEntity entity = (IbanSingleReportEntity) exchange.getIn().getBody();
				Account account = accountEnricherService.getAccountByIban(entity.getIban());
				exchange.getIn().setBody(account);
			} else {
				throw new Exception("Missing Iban ID !");
			}
		} else {
			throw new Exception("Account Service is not available !"); 
		}
	}

	public AccountServiceApi getAccountEnricherService() {
		return accountEnricherService;
	}

	public void setAccountEnricherService(AccountServiceApi accountEnricherService) {
		this.accountEnricherService = accountEnricherService;
	}

}
