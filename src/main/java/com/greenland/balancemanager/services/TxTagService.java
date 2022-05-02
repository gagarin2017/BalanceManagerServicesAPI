package com.greenland.balancemanager.services;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenland.balancemanager.domain.TxTag;
import com.greenland.balancemanager.repositories.TxTagRepository;

@Service
public class TxTagService {
	
	public static final String DEFAULT_TAG_NAME = StringUtils.EMPTY;
	
	@Autowired
	private TxTagRepository tagRepository;
	

	public Optional<TxTag> getTagByName(final String tagName) {
		return tagRepository.findByTagName(tagName).stream().findFirst();
	}

}
