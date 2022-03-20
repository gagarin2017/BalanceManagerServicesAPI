package com.greenland.balancemanager.utils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.greenland.balanceManager.java.app.CommonUtils;
import com.greenland.balancemanager.domain.TxDataRow;

public class TxDataRowJsonDeserializer extends JsonDeserializer<TxDataRow> {
	
	@Override
	public TxDataRow deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {
		
        final TxDataRow txDataRow = TxDataRow.builder().build();
		
		final JsonNode node = jsonParser.getCodec().readTree(jsonParser);
		
		if (!node.isEmpty()) {
			
			// Date fields name is weird. Contains weird characters. Quick patch.
			
			final String date = readFieldValue(node, CommonUtils.TX_DATE_JSON_KEY);
			final String account = readFieldValue(node, CommonUtils.TX_ACCOUNT_NAME_JSON_KEY);
	        final String desription = readFieldValue(node, CommonUtils.TX_DESCRIPTION_JSON_KEY);
	        final String catgory = readFieldValue(node, CommonUtils.TX_CATEGORY_NAME_JSON_KEY);
	        final String memo = readFieldValue(node, CommonUtils.TX_MEMO_JSON_KEY);
	        final String tag = readFieldValue(node, CommonUtils.TX_TAG_JSON_KEY);
	        final String clr = readFieldValue(node, CommonUtils.TX_RECONCILED_JSON_KEY);
	        final String amount = readFieldValue(node, CommonUtils.TX_AMOUNT_JSON_KEY);
	        
	        txDataRow.setDate(LocalDate.parse(date, CommonUtils.DATE_TIME_FORMATTER));
	        txDataRow.setAccount(account);
	        txDataRow.setDescription(desription);
	        txDataRow.setCategory(catgory);
	        txDataRow.setClr(clr);
	        txDataRow.setAmount(amount);
	        txDataRow.setMemo(memo);
	        txDataRow.setTag(tag);
		}
		return txDataRow;
	}

	/**
	 * @param node
	 * @return
	 */
	private String readFieldValue(final JsonNode node, final String fieldName) {
		String result = null;
		final Iterator<String> fieldNames = node.fieldNames();

		boolean isFound = false;
		
		while(fieldNames.hasNext() && !isFound) {
		    final String foundFieldName = fieldNames.next();
		    
		    if (foundFieldName.contains(fieldName)) {
		    	result = node.get(foundFieldName).textValue();
		    	isFound = true;
		    }
		}
		
		return result;
	}

}
