package com.greenland.balancemanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenland.balancemanager.domain.TxDataRow;
import com.greenland.balancemanager.repositories.TxDataRowRepository;

@Service
public class TxDataRowService {
	
	@Autowired
	private TxDataRowRepository txDataRowRepository;
	
	/**
	 * @return return all {@link TxDataRow}
	 */
	public Iterable<TxDataRow> getAllTxDataRows() {
		return txDataRowRepository.findAll();
	}

}
