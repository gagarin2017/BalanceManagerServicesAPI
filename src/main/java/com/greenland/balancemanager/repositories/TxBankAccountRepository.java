package com.greenland.balancemanager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greenland.balancemanager.domain.TxBankAccount;

@Repository
public interface TxBankAccountRepository extends CrudRepository<TxBankAccount, Long	>{

}
