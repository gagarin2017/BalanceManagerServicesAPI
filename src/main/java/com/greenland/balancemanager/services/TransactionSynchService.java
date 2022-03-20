package com.greenland.balancemanager.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenland.balanceManager.java.app.exceptions.TransactionsNotFoundAtSourceException;
import com.greenland.balanceManager.java.app.external.BalanceManagerExternal;
import com.greenland.balanceManager.java.app.external.BalanceManagerExternalImpl;
import com.greenland.balancemanager.domain.TxDataRow;
import com.greenland.balancemanager.repositories.TxDataRowRepository;
import com.greenland.balancemanager.utils.TxDataRowCustomMapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Service to read the fresh data and update the database records
 * 
 * @author Jura
 *
 */
@Service
@Deprecated
public class TransactionSynchService {
	
	@Autowired
	private TxDataRowRepository txDataRowRepository;

	/**
	 * Method reads the transactions from the external app and saves them on the database.
	 * 
	 * Using Orika mapper here to convert external {@link com.greenland.balanceManager.java.app.model.TxDataRow externalTxDataRow}
	 * to {@link TxDataRow}
	 * @throws TransactionsNotFoundAtSourceException 
	 */
	public void updateTxDataRows() throws TransactionsNotFoundAtSourceException {

		final List<TxDataRow> txDataRows =  new ArrayList<>();
		final BalanceManagerExternal balanceManagerExternal = new BalanceManagerExternalImpl();
		final MapperFacade mapper = configureTxDataRowMapper();
		
		for (final com.greenland.balanceManager.java.app.model.TxDataRow externalTxDataRow : balanceManagerExternal.getAllTransactions()) {
			final TxDataRow txDataRow = mapper.map(externalTxDataRow, TxDataRow.class);
			txDataRows.add(txDataRow);
		}
	    
		txDataRowRepository.saveAll(txDataRows);
	}

	/**
	 * @return
	 */
	@Deprecated
	private MapperFacade configureTxDataRowMapper() {
		final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		
		final TxDataRowCustomMapper txDataRowCustomMapper = new TxDataRowCustomMapper();
		
		mapperFactory.classMap(com.greenland.balanceManager.java.app.model.TxDataRow.class, TxDataRow.class)
		.field("accountName", "accountName").field("categoryName", "categoryName")
		.field("debitAmount", "debitAmount").field("creditAmount", "creditAmount")
				.field("reconsiled", "reconsiled").field("remote", "remote")
				./* customize(txDataRowCustomMapper). */register();
		return mapperFactory.getMapperFacade();
	}
	
	

}
