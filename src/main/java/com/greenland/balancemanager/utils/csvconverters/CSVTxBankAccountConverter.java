package com.greenland.balancemanager.utils.csvconverters;

import com.greenland.balancemanager.domain.TxRow;
import com.greenland.balancemanager.domain.TxBankAccount;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class CSVTxBankAccountConverter extends AbstractBeanField<TxRow, Integer> {

	@Override
	protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		return TxBankAccount.builder().accountName(value).build();
	}

}
