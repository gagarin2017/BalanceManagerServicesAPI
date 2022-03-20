package com.greenland.balancemanager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greenland.balancemanager.domain.TxBankAccount;
import com.greenland.balancemanager.domain.TxDataRow;

@Repository
public interface TxBankAccountRepository extends CrudRepository<TxBankAccount, Long	>{

}
