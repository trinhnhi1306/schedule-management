package com.nnptrinh.schedulemanagement.config;

import com.maxmind.geoip2.WebServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class GeoLocationConfig {

    @Value(value = "${geoip2.account-id}")
    private int accountId;

    @Value(value = "${geoip2.license-key}")
    private String licenseKey;

    @Bean
    public WebServiceClient webServiceClient() {
        return new WebServiceClient.Builder(accountId, licenseKey).host("geolite.info").build();
    }
}
