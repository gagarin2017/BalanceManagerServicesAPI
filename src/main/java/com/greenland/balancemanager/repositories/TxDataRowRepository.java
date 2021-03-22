package com.greenland.balancemanager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greenland.balancemanager.domain.TxDataRow;

@Repository
public interface TxDataRowRepository extends CrudRepository<TxDataRow, Long	>{

}
