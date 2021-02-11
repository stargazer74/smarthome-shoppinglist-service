package de.smarthome.assistant.shoppinglist.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("barcode.db")
public class BareCodeDbConfig {

    private String url;

    private String id;

    @Bean
    public BareCodeDbConfig getElectronicInsuranceApplicationConfig(){
        return this;
    }
}
