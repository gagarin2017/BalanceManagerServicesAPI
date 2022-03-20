package com.greenland.balancemanager.utils;

import java.math.BigDecimal;
import java.util.UUID;

import com.greenland.balancemanager.domain.TxDataRow;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class TxDataRowCustomConverter extends BidirectionalConverter<TxDataRow, com.greenland.balanceManager.java.app.model.TxDataRow> {

	@Override
	public com.greenland.balanceManager.java.app.model.TxDataRow convertTo(final TxDataRow source,
			final Type<com.greenland.balanceManager.java.app.model.TxDataRow> destinationType,
			final MappingContext mappingContext) {
		
		final com.greenland.balanceManager.java.app.model.TxDataRow dto = new com.greenland.balanceManager.java.app.model.TxDataRow();
		
		dto.setTxDate(source.getDate());
		dto.setAccountName(source.getAccount());
		dto.setDescription(source.getDescription());
		dto.setMemo(source.getMemo());
		dto.setCategoryName(source.getCategory());
		dto.setClr(source.getClr());
		dto.setTag(source.getTag());
		
		try {
			if (source.getAmount() != null) {
				dto.setAmount(new BigDecimal(source.getAmount().replace(",", "").trim()).setScale(2));
			}
		} catch (ArithmeticException | NumberFormatException ex) {
			dto.setCreditAmount(new BigDecimal(Integer.MIN_VALUE).setScale(2, BigDecimal.ROUND_HALF_DOWN));
		}
		
		return dto;
	}

	@Override
	public TxDataRow convertFrom(final com.greenland.balanceManager.java.app.model.TxDataRow source,
			final Type<TxDataRow> destinationType, final MappingContext mappingContext) {
		final TxDataRow txDataRow = TxDataRow.builder()
				.id(UUID.randomUUID().getMostSignificantBits())
				.date(source.getTxDate())
				.account(source.getAccountName())
				.amount(source.getAmountAsNumber().toString())
				.category(source.getCategoryName())
				.clr(source.getClr())
				.creditAmount(source.getCreditAmount())
				.debitAmount(source.getDebitAmount())
				.description(source.getDescription())
				.isReconsiled(source.isReconsiled())
				.isRemote(source.isRemote())
				.memo(source.getMemo())
				.tag(source.getTag())
				.build();
		return txDataRow;
	}

}
