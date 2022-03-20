package com.greenland.balancemanager.utils;

import java.util.UUID;

import com.greenland.balancemanager.domain.InputTxData;
import com.greenland.balancemanager.domain.OutputTxData;
import com.greenland.balancemanager.domain.TxDataRow;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * Converter to convert DTOs to the current project POJO instances
 * 
 * @author Yura
 *
 */
public class TxDataDTOConverter {
	
	private final static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
	private final static MapperFacade mapper;
	
	static {
		mapperFactory.getConverterFactory().registerConverter(new TxDataRowCustomConverter());
		mapper = mapperFactory.getMapperFacade();
	}

	/**
	 * @param inputTxData
	 * 			{@link InputTxData}
	 * @return com.greenland.balanceManager.java.app.external.domain.InputTxData
	 */
	public static com.greenland.balanceManager.java.app.external.domain.InputTxData convert(final InputTxData inputTxData) {
		mapperFactory.classMap(TxDataRow.class, com.greenland.balanceManager.java.app.model.TxDataRow.class).byDefault().register();
		
		com.greenland.balanceManager.java.app.external.domain.InputTxData result = 
	    		mapper.map(inputTxData, com.greenland.balanceManager.java.app.external.domain.InputTxData.class);
	   
		return result;
	}
	
	/**
	 * @param outputTxDataDTO
	 * @return
	 * 		{@link OutputTxData}
	 */
	public static OutputTxData convert(
			com.greenland.balanceManager.java.app.external.domain.OutputTxData outputTxDataDTO) {
		mapperFactory.classMap(com.greenland.balanceManager.java.app.model.TxDataRow.class, TxDataRow.class).byDefault().register();
		final OutputTxData result = mapper.map(outputTxDataDTO, com.greenland.balancemanager.domain.OutputTxData.class);
		
		// setting ID here for React front end app
		
		result.getLocalTransactions().forEach(dailyTransactions -> dailyTransactions.setId(UUID.randomUUID().getMostSignificantBits()));
		result.getRemoteTransactions().forEach(dailyTransactions -> dailyTransactions.setId(UUID.randomUUID().getMostSignificantBits()));
		
		return result;
	}

}
