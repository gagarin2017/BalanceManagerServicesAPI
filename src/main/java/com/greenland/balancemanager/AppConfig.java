package com.greenland.balancemanager;

import static com.greenland.balancemanager.Constants.LOCAL_DATETIME_SERIALIZER;
import static com.greenland.balancemanager.Constants.LOCAL_DATE_DESERIALIZER;
import static com.greenland.balancemanager.Constants.LOCAL_DATE_SERIALIZER;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.greenland.balanceManager.java.app.external.BalanceManagerExternal;
import com.greenland.balanceManager.java.app.external.BalanceManagerExternalImpl;

@Configuration
public class AppConfig {
	
	public static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@Bean
    public BalanceManagerExternal balanceManager() {
        return new BalanceManagerExternalImpl();
    }
	
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LOCAL_DATETIME_SERIALIZER);
        module.addSerializer(LOCAL_DATE_SERIALIZER);
        module.addDeserializer(LocalDate.class, LOCAL_DATE_DESERIALIZER);
        return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .registerModule(module);
    }
    
}
