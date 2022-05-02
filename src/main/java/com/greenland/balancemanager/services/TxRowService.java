package com.greenland.balancemanager.services;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenland.balancemanager.domain.TxBankAccount;
import com.greenland.balancemanager.domain.TxCategory;
import com.greenland.balancemanager.domain.TxDataRow;
import com.greenland.balancemanager.domain.TxDescription;
import com.greenland.balancemanager.domain.TxRow;
import com.greenland.balancemanager.domain.TxTag;
import com.greenland.balancemanager.repositories.TxRowRepository;

@Service
public class TxRowService {
	
	@Autowired
	private TxRowRepository txRowRepository;
	
	@Autowired
	private TxBankAccountService bankAccountService;
	
	@Autowired
	private TxDescriptionService txDescriptionService;
	
	@Autowired
	private TxCategoryService txCategoryService;
	
	@Autowired
	private TxTagService txTagService;
	
	/**
	 * @return return all {@link TxDataRow}
	 */
	public Iterable<TxRow> getAllBankAccountTransactions(final long bankAccountId) {
		return txRowRepository.findByTxBankAccountTxBankAccountId(bankAccountId);
	}

	public Iterable<TxRow> save(final List<TxRow> inputTxData) {
		
		inputTxData.forEach(inputTx -> {
			
			final String inputDesc = inputTx.getTxDescription()!= null ? inputTx.getTxDescription().getDescription() : null;
			final String inputCategoryName = inputTx.getTxCategory() != null ? inputTx.getTxCategory().getCategoryName() : null;
			final String inputTagName = inputTx.getTxTag() != null ? inputTx.getTxTag().getTagName() : null;
			
			final Optional<TxBankAccount> savedAccount = bankAccountService
					.getBankAccountById(inputTx.getTxBankAccount().getTxBankAccountId());
			
			if(savedAccount.isPresent()) {
				inputTx.setTxBankAccount(savedAccount.get());
			}
			
			final Optional<TxDescription> savedDescription = txDescriptionService
					.getDescriptionByName(StringUtils.isAllEmpty(inputDesc) ? TxDescriptionService.DEFAULT_DESCRIPTION_NAME : inputDesc);
			
			if(savedDescription.isPresent()) {
				inputTx.setTxDescription(savedDescription.get());
			}
			
			final Optional<TxCategory> savedCategory = txCategoryService
					.getCategoryByName(StringUtils.isAllEmpty(inputCategoryName) ? TxCategoryService.DEFAULT_CATEGORY_NAME : inputCategoryName);
			
			if(savedCategory.isPresent()) {
				inputTx.setTxCategory(savedCategory.get());
			}
			
			final Optional<TxTag> savedTag = txTagService
					.getTagByName(StringUtils.isAllEmpty(inputTagName) ? TxTagService.DEFAULT_TAG_NAME : inputTagName);
			
			if(savedTag.isPresent()) {
				inputTx.setTxTag(savedTag.get());
			}
			
		});
		
		return txRowRepository.saveAll(inputTxData);
	}

}
