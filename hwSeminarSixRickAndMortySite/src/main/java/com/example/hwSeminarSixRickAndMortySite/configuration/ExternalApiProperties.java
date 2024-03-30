package com.example.hwSeminarSixRickAndMortySite.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "external.api")
public class ExternalApiProperties {
    private String url;
}
