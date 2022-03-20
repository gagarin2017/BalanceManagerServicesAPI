package com.greenland.balancemanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenland.balancemanager.domain.TxBankAccount;
import com.greenland.balancemanager.domain.TxDataRow;
import com.greenland.balancemanager.repositories.TxBankAccountRepository;
import com.greenland.balancemanager.repositories.TxDataRowRepository;

@Service
public class TxBankAccountService {
	
	@Autowired
	private TxBankAccountRepository txBankAccountRepository;
	
	/**
	 * @return return all {@link TxBankAccount}
	 */
	public Iterable<TxBankAccount> getAllTxBankAccounts() {
		return txBankAccountRepository.findAll();
	}

}
