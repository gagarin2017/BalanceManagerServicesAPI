package com.greenland.balancemanager.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenland.balancemanager.domain.TxCategory;
import com.greenland.balancemanager.repositories.TxCategoryRepository;

@Service
public class TxCategoryService {
	
	public static final String DEFAULT_CATEGORY_NAME = "Not Sure";
	
	@Autowired
	private TxCategoryRepository txCategoryRepository;
	
	public Optional<TxCategory> getCategoryByName(final String categoryName) {
		return txCategoryRepository.findByCategoryName(categoryName).stream().findFirst();
	}

}
