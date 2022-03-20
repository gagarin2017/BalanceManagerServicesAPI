package com.greenland.balancemanager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.greenland.balanceManager.java.app.external.BalanceManagerExternal;
import com.greenland.balanceManager.java.app.external.BalanceManagerExternalImpl;

@Configuration
public class AppConfig {

	@Bean
    public BalanceManagerExternal balanceManager() {
        return new BalanceManagerExternalImpl();
    }
	
}
