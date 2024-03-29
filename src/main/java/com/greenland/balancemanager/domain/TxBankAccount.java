package com.greenland.balancemanager.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "TxBankAccounts", schema="public")
public class TxBankAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long txBankAccountId;
	
	private String accountName;
	private String bankName;
	
	@Transient
	private BigDecimal balance;
	
	@Column(nullable = false, columnDefinition = "boolean default true")
	private boolean active;
	
	// One to Many TxRows
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "txBankAccount")
	@JsonIgnore
	@Builder.Default
	private List<TxRow> transactionRows = new ArrayList<>();
	
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
		return Objects.hash(accountName, bankName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TxBankAccount)) {
			return false;
		}
		TxBankAccount other = (TxBankAccount) obj;
		return Objects.equals(accountName, other.accountName) && Objects.equals(bankName, other.bankName);
	}

}
