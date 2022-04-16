package com.greenland.balancemanager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greenland.balancemanager.domain.TxRow;

@Repository
public interface TxRowRepository extends CrudRepository<TxRow, Long	>{

	Iterable<TxRow> findByTxBankAccountTxBankAccountId(long bankAccountId);

}
