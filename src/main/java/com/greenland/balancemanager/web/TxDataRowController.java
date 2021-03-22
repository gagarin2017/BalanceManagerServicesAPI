package com.greenland.balancemanager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenland.balancemanager.domain.TxDataRow;
import com.greenland.balancemanager.services.TxDataRowService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/api")
@Api( description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Transaction Rows.")
public class TxDataRowController {
	
	@Autowired
	private TxDataRowService txDataRowService;

	@GetMapping(path="/all")
	@ApiOperation("Returns list of all Transaction rows found in the system.")
	public Iterable<TxDataRow> getAllProjects() {
		return txDataRowService.getAllTxDataRows();
	}
}
