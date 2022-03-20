package com.greenland.balancemanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenland.balanceManager.java.app.external.BalanceManagerExternal;
import com.greenland.balancemanager.domain.InputTxData;
import com.greenland.balancemanager.domain.OutputTxData;
import com.greenland.balancemanager.utils.TxDataDTOConverter;

@Service
public class AnalyzeTransactionsService {
	
	@Autowired
	private BalanceManagerExternal balanceManager;
	
	/**
	 * @return return the results from the external app
	 */
	public OutputTxData analyzeTransactions(final InputTxData inputTxData) {
		
		final com.greenland.balanceManager.java.app.external.domain.InputTxData data = TxDataDTOConverter.convert(inputTxData);
		final com.greenland.balanceManager.java.app.external.domain.OutputTxData output = balanceManager.analyzeTransactions(data);
		
		System.out.println(String.format("Data from the API. Remote: %d, Local: %d, Missing: %s ", output.getRemoteTransactions().size(),output.getLocalTransactions().size(),
				output.getMissingTransactions().toString()));
		
		return TxDataDTOConverter.convert(output);
	}

}
