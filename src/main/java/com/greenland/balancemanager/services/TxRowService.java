package com.greenland.balancemanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenland.balancemanager.domain.TxDataRow;
import com.greenland.balancemanager.domain.TxRow;
import com.greenland.balancemanager.repositories.TxRowRepository;

@Service
public class TxRowService {
	
	@Autowired
	private TxRowRepository txRowRepository;
	
	/**
	 * @return return all {@link TxDataRow}
	 */
	public Iterable<TxRow> getAllTransactions() {
		return txRowRepository.findAll();
	}

}
