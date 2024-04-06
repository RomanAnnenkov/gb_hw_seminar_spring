package com.example.hwSeminarEightSite.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("external.services")
public class ExternalServices {
    private String gatewayUri;
}
