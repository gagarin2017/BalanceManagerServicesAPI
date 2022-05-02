package com.greenland.balancemanager.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenland.balancemanager.domain.TxBankAccount;
import com.greenland.balancemanager.repositories.TxBankAccountRepository;

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

	public Optional<TxBankAccount> getBankAccountById(final Long txBankAccountId) {
		return txBankAccountRepository.findById(txBankAccountId);
	}

}
