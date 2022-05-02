package com.greenland.balancemanager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greenland.balancemanager.domain.TxTag;

@Repository
public interface TxTagRepository extends CrudRepository<TxTag, Long	>{

	public List<TxTag> findByTagName(final String tagName);

}
