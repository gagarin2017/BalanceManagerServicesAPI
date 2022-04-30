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
import javax.persistence.Transient;

import com.greenland.balancemanager.utils.csvconverters.CSVTxAmountConverter;
import com.greenland.balancemanager.utils.csvconverters.CSVTxBankAccountConverter;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;

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
	
	private LocalDate txDate;
	
	@CsvCustomBindByPosition(position = 1, required = true, converter = CSVTxBankAccountConverter.class)
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "txBankAccountId", updatable = false, nullable = false )
	private TxBankAccount txBankAccount;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "txDescriptionId", updatable = false, nullable = false )
	private TxDescription txDescription;
	
	private String txMemo;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "txCategoryId", updatable = false, nullable = false )
	private TxCategory txCategory;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "txTagId", updatable = false, nullable = false )
	private TxTag txTag;
	
	@CsvBindByPosition(position = 6)
	private String isReconsiled;
	
	@CsvCustomBindByPosition(position = 7, converter = CSVTxAmountConverter.class)
	private BigDecimal txAmount;
	
	@Transient
	private BigDecimal balance;
	
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
