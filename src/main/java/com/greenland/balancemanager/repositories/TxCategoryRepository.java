package com.greenland.balancemanager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greenland.balancemanager.domain.TxCategory;

@Repository
public interface TxCategoryRepository extends CrudRepository<TxCategory, Long	>{
	
	public List<TxCategory> findByCategoryName(String categoryName);

}
