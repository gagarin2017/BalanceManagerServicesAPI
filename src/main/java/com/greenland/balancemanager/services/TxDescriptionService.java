package com.greenland.balancemanager.services;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenland.balancemanager.domain.TxDescription;
import com.greenland.balancemanager.repositories.TxDescriptionRepository;

@Service
public class TxDescriptionService {
	
	public static final String DEFAULT_DESCRIPTION_NAME=StringUtils.EMPTY;
	
	@Autowired
	private TxDescriptionRepository descriptionRepository;
	

	public Optional<TxDescription> getDescriptionByName(final String description) {
		return descriptionRepository.findByDescription(description).stream().findFirst();
	}

}
