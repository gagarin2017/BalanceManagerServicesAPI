package com.greenland.balancemanager.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenland.balancemanager.domain.InputTxData;
import com.greenland.balancemanager.domain.OutputTxData;
import com.greenland.balancemanager.domain.TxBankAccount;
import com.greenland.balancemanager.domain.TxRow;
import com.greenland.balancemanager.services.AnalyzeTransactionsService;
import com.greenland.balancemanager.services.TxBankAccountService;
import com.greenland.balancemanager.services.TxRowService;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/api")
@Log4j2
public class TxDataRowController {
	
	@Autowired
	private TxBankAccountService txBankAccountService;
	
	@Autowired
	private TxRowService txRowService;
	
	@Autowired
	private AnalyzeTransactionsService analyzeTransactionsService;

	@GetMapping(path="/bank-accounts")
	public Iterable<TxBankAccount> getAllBankAccounts() {
		return txBankAccountService.getAllTxBankAccounts();
	}
	
	@GetMapping(path="/bank-account/{bankAccountId}/transactions")
	public Iterable<TxRow> getTransactions(@PathVariable int bankAccountId) {
		log.debug("Getting transactions for the account ", bankAccountId);
		final Iterable<TxRow> bankTransactions = txRowService.getAllBankAccountTransactions(bankAccountId);
		return bankTransactions;
	}
	
	@PostMapping(value = "/transactions", consumes = { MediaType.APPLICATION_JSON_VALUE,	MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Iterable<TxRow>> saveTransactions(@RequestBody List<TxRow> inputTxData) {
		
		System.out.println(String.format("Number of txs received: [%d]",
				inputTxData.size()));
		inputTxData.forEach(txData -> System.out.println("txData row: "+txData));
		
		final Iterable<TxRow> savedData = txRowService.save(inputTxData);

		return new ResponseEntity<Iterable<TxRow>>(savedData, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/analyzeTransactions", consumes = { MediaType.APPLICATION_JSON_VALUE,	MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<OutputTxData> analyzeTheTransactions(@RequestBody InputTxData inputTxData) {
		
		System.out.println(String.format("Number of remote txs received [%d]. Number of local txs received [%d]",
				inputTxData.getRemoteAccountTxsData().size(), inputTxData.getLocalAccountTxsData().size()));

		OutputTxData response = OutputTxData.builder().build();
		response = analyzeTransactionsService.analyzeTransactions(inputTxData);
		
		return new ResponseEntity<OutputTxData>(response, HttpStatus.ACCEPTED);
	}
}
