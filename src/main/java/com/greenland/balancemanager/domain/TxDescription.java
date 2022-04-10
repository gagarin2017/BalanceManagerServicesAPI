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
@Table(name = "TxDescriptions", schema="public")
public class TxDescription {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long txDescriptionId;
	
	private String description;
	
	// One to Many TxRows
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "txDescription")
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
		return Objects.hash(description);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TxDescription)) {
			return false;
		}
		TxDescription other = (TxDescription) obj;
		return Objects.equals(description, other.description);
	}

}
