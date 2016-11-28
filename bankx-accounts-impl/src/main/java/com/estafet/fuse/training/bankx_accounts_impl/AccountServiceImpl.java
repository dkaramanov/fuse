package com.estafet.fuse.training.bankx_accounts_impl;

import java.util.Random;

import com.estafet.fuse.training.bankx_accounts_api.AccountServiceApi;
import com.estafet.fuse.training.bankx_models.bean.Account;

public class AccountServiceImpl implements AccountServiceApi {
	
	private final static String[] names = {"Dimitar Karamanov", "Ivan Ivanov", "Peter Petrov"};
	private final static Double[] values = {120.23, 155.34, 0.98};
	private final static String[] currencies = {"BGL", "USD", "GBP"};

	public Account getAccountByIban(String iban) {

		String name = names[new Random().nextInt(names.length)];
		Double value = values[new Random().nextInt(values.length)];
		String currency = currencies[new Random().nextInt(currencies.length)];
		
		Account account = new Account();
		
		account.setName(name);
		account.setValue(value);
		account.setCurrency(currency);
		
		return account;
	}

}
