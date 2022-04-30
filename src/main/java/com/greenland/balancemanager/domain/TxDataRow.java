package com.greenland.balancemanager.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.greenland.balancemanager.utils.TxDataRowJsonDeserializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@JsonIdentityReference(alwaysAsId = true)
@JsonDeserialize(using = TxDataRowJsonDeserializer.class)
@ToString
public class TxDataRow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate date;
	
	private String account;
	
	private String category;

	private BigDecimal debitAmount;
	
	private BigDecimal creditAmount;
	
	private boolean isReconsiled;
	
	private boolean isRemote;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate created_At;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate updated_At;
	
	@PrePersist
	protected void onCreate() {
		this.created_At = LocalDate.now();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updated_At = LocalDate.now();
	}
	
	private String description;
	private String clr;
	private String amount;
	private String memo;
	private String tag;
	
}
