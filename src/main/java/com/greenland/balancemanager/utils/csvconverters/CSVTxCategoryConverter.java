package com.greenland.balancemanager.utils.csvconverters;

import com.greenland.balancemanager.domain.TxRow;
import com.greenland.balancemanager.domain.TxCategory;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class CSVTxCategoryConverter extends AbstractBeanField<TxRow, Integer> {

	@Override
	protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		return TxCategory.builder().categoryName(value).build();
	}

}
