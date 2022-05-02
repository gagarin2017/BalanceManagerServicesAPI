package com.greenland.balancemanager.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenland.balancemanager.domain.TxCategory;
import com.greenland.balancemanager.domain.TxRow;
import com.greenland.balancemanager.domain.TxTag;

@Repository
public interface TxRowRepository extends CrudRepository<TxRow, Long	>{

	Iterable<TxRow> findByTxBankAccountTxBankAccountId(long bankAccountId);

	Optional<TxRow> findByTxBankAccountTxBankAccountIdAndTxDateAndTxDescriptionTxDescriptionIdAndTxAmount(final Long txBankAccountId, final LocalDate txDate,
			final Long txDescriptionId, final BigDecimal txAmount);

	@Modifying
	@Query("update TxRow u set u.txMemo = :memo, u.txCategory = :category, u.txTag = :tag where u.txRowId = :id")
	void updateTransaction(@Param(value = "id") long id, @Param(value = "memo") String memo,
			@Param(value = "category") TxCategory category, @Param(value = "tag") TxTag tag);
}
