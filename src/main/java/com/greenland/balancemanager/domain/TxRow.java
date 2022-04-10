package com.greenland.balancemanager.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.greenland.balancemanager.utils.csvconverters.CSVTxAmountConverter;
import com.greenland.balancemanager.utils.csvconverters.CSVTxBankAccountConverter;
import com.greenland.balancemanager.utils.csvconverters.CSVTxCategoryConverter;
import com.greenland.balancemanager.utils.csvconverters.CSVTxDescriptionConverter;
import com.greenland.balancemanager.utils.csvconverters.CSVTxTagConverter;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import com.opencsv.bean.CsvDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "TxRows", schema="public")
public class TxRow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long txRowId;
	
	@CsvBindByPosition(position = 0, required = true)
	@CsvDate("dd/MM/yyyy")
	private LocalDate txDate;
	
	@CsvCustomBindByPosition(position = 1, required = true, converter = CSVTxBankAccountConverter.class)
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "txBankAccountId", updatable = false, nullable = false )
	private TxBankAccount txBankAccount;
	
	@CsvCustomBindByPosition(position = 2, converter = CSVTxDescriptionConverter.class)
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "txDescriptionId", updatable = false, nullable = false )
	@JsonIgnoreProperties("description")
//	@JsonManagedReference
	private TxDescription txDescription;
	
	@CsvBindByPosition(position = 3)
	private String txMemo;
	
	@CsvCustomBindByPosition(position = 4, converter = CSVTxCategoryConverter.class)
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "txCategoryId", updatable = false, nullable = false )
//	@JsonManagedReference
	private TxCategory txCategory;
	
	@CsvCustomBindByPosition(position = 5, converter = CSVTxTagConverter.class)
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "txTagId", updatable = false, nullable = false )
//	@JsonManagedReference
	private TxTag txTag;
	
	@CsvBindByPosition(position = 6)
	private String isReconsiled;
	
	@CsvCustomBindByPosition(position = 7, converter = CSVTxAmountConverter.class)
	private BigDecimal txAmount;
	
	private LocalDateTime created_At;
	private LocalDateTime updated_At;
	
	@PrePersist
	public void prePersist() {
		created_At = LocalDateTime.now();
	}
	
	@PreUpdate
	public void preUpdate() {
		updated_At = LocalDateTime.now();
	}

	@Override
	public int hashCode() {
		return Objects.hash(txAmount, txBankAccount, txCategory, txDate, txDescription, txTag);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TxRow)) {
			return false;
		}
		TxRow other = (TxRow) obj;
		return Objects.equals(txAmount, other.txAmount) && Objects.equals(txBankAccount, other.txBankAccount)
				&& Objects.equals(txCategory, other.txCategory) && Objects.equals(txDate, other.txDate)
				&& Objects.equals(txDescription, other.txDescription) && Objects.equals(txTag, other.txTag);
	}
	
	
}
