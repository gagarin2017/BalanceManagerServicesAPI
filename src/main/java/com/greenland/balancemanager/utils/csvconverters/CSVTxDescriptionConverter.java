package com.greenland.balancemanager.utils.csvconverters;

import com.greenland.balancemanager.domain.TxRow;
import com.greenland.balancemanager.domain.TxDescription;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class CSVTxDescriptionConverter extends AbstractBeanField<TxRow, Integer> {

	@Override
	protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		return TxDescription.builder().description(value).build();
	}

}
