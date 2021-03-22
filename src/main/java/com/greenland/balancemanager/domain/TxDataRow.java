package com.greenland.balancemanager.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TxDataRow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date txDate;
	
	@NotBlank(message = "Account name is required")
	private String accountName;
	
	@NotBlank(message = "Category name is required")
	private String categoryName;

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
	
}
