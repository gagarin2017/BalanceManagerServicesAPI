package com.greenland.balancemanager.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.greenland.balancemanager.utils.TxDataRowJsonDeserializer;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * The original POJO from the JSON coming from the outside via web service.
 * 
 * @author Yura
 *
 */
@Data
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@JsonDeserialize(using = TxDataRowJsonDeserializer.class)
@Deprecated
public class TxDataRowJson {
	
	private @Id @GeneratedValue Long id;
	private String date;
	private String account;
	private String description;
	private String memo;
	private String category;
	private String tag;
	private String clr;
	private String debitAmount;
	private String creditAmount;
	private String amount;
}
