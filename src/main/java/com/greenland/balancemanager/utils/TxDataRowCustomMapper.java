package com.greenland.balancemanager.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.greenland.balancemanager.domain.TxDataRow;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

/**
 * Custom {@link TxDataRow} mapper to convert the {@link LocalDate} field to {@link Date} and vice versa
 * 
 * @author Jura
 *
 */
public class TxDataRowCustomMapper extends CustomMapper<com.greenland.balanceManager.java.app.model.TxDataRow, TxDataRow> {
	 
	@Override
	public void mapAtoB(final com.greenland.balanceManager.java.app.model.TxDataRow a, final TxDataRow b,
			final MappingContext context) {
		
		final LocalDate localDate = a.getTxDate();

		// default time zone
		ZoneId defaultZoneId = ZoneId.systemDefault();

		// local date + atStartOfDay() + default time zone + toInstant() = Date
		final Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		b.setTxDate(date);
	}
 
	@Override
	public void mapBtoA(final TxDataRow b, final com.greenland.balanceManager.java.app.model.TxDataRow a,
			final MappingContext context) {
		
		// default time zone
		ZoneId defaultZoneId = ZoneId.systemDefault();
		final LocalDate localDate = b.getTxDate().toInstant().atZone(defaultZoneId).toLocalDate();
		a.setTxDate(localDate);
	}
};