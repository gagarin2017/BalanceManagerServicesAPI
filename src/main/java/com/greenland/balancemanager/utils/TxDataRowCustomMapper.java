package com.greenland.balancemanager.utils;

import java.math.BigDecimal;
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
@Deprecated
public class TxDataRowCustomMapper extends CustomMapper<TxDataRow, com.greenland.balanceManager.java.app.model.TxDataRow> {
	 
	@Override
	public void mapAtoB(final TxDataRow a, final com.greenland.balanceManager.java.app.model.TxDataRow b,
			final MappingContext context) {
		
		b.setTxDate(a.getDate());
//		b.setAmount(new BigDecimal(a.getAmount()).setScale(2));
		
//		This number is causing troubles
//		2,150.00
		
		System.out.println(a.getAmount());
		try {
			if (a.getAmount() != null) {
				b.setAmount(new BigDecimal(a.getAmount()).setScale(2, BigDecimal.ROUND_HALF_DOWN));
			}
		} catch (ArithmeticException | NumberFormatException ex) {
			b.setCreditAmount(new BigDecimal(Double.MIN_VALUE).setScale(2, BigDecimal.ROUND_HALF_DOWN));
		}
//		
//		try {
//			b.setDebitAmount(a.getDebitAmount() == null ? BigDecimal.ZERO : a.getDebitAmount());
//		} catch (ArithmeticException ex) {
//			b.setDebitAmount(new BigDecimal(Double.MIN_VALUE).setScale(2, BigDecimal.ROUND_HALF_DOWN));
//		}
		
//		final LocalDate localDate = a.getTxDate();
//
//		// default time zone
//		ZoneId defaultZoneId = ZoneId.systemDefault();
//
//		// local date + atStartOfDay() + default time zone + toInstant() = Date
//		final Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
//		b.setTxDate(date);
	}
 
	@Override
	public void mapBtoA(final com.greenland.balanceManager.java.app.model.TxDataRow a, final TxDataRow b,
			final MappingContext context) {
		
		System.out.println();
		// default time zone
//		ZoneId defaultZoneId = ZoneId.systemDefault();
//		final LocalDate localDate = b.getTxDate().toInstant().atZone(defaultZoneId).toLocalDate();
//		a.setTxDate(localDate);
	}
};