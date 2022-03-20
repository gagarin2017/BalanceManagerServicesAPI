package com.greenland.balancemanager.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
	
	@NotBlank(message = "Account name is required")
	private String account;
	
	@NotBlank(message = "Category name is required")
	private String category;

	private BigDecimal debitAmount;
	
	private BigDecimal creditAmount;
	
	private boolean isReconsiled;
	
	@NotBlank(message = "Is remote true/false must be set")
	private boolean isRemote;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date created_At;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date updated_At;
	
	@PrePersist
	protected void onCreate() {
		this.created_At = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updated_At = new Date();
	}
	
	private String description;
	private String clr;
	private String amount;
	private String memo;
	private String tag;
	
}
