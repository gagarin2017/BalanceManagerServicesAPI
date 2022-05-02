package com.greenland.balancemanager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greenland.balancemanager.domain.TxDescription;

@Repository
public interface TxDescriptionRepository extends CrudRepository<TxDescription, Long	>{
	
	List<TxDescription> findByDescription(final String description);

}
