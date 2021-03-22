package com.greenland.balancemanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.greenland.balancemanager.services.TransactionSynchService;

/**
 * Executing Code on Spring Boot Application Startup
 * 
 * @author Jura
 * @deprecated -- Uncomment the annotations below to enable the listener to do something on the application startup
 */
//@Component
//@Order(0)
public class TxUploaderListener implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private TransactionSynchService txSynchronizer;
	
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		txSynchronizer.updateTxDataRows();
	}

}
