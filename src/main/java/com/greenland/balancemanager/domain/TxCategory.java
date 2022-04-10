package com.greenland.balancemanager.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "TxCategories", schema="public")
public class TxCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long txCategoryId;
	
	private String categoryName;
	
	// One to Many TxRows
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "txCategory")
	@Builder.Default
	@JsonBackReference
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
		return Objects.hash(categoryName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TxCategory)) {
			return false;
		}
		TxCategory other = (TxCategory) obj;
		return Objects.equals(categoryName, other.categoryName);
	}

}