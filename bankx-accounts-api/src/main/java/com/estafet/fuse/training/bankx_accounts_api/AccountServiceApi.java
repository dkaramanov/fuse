package com.estafet.fuse.training.bankx_accounts_api;

import com.estafet.fuse.training.bankx_models.bean.Account;

public interface AccountServiceApi {
	Account getAccountByIban(String iban);
}
