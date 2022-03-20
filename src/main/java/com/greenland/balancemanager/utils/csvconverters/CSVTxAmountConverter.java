package com.greenland.balancemanager.utils.csvconverters;

import java.math.BigDecimal;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import com.greenland.balancemanager.domain.TxRow;

public class CSVTxAmountConverter extends AbstractBeanField<TxRow, Integer> {

	@Override
	protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		return new BigDecimal(value.replaceAll(",", ""));
	}

}
