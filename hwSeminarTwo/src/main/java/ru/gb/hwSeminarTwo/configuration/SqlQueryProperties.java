package ru.gb.hwSeminarTwo.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "sql.query")
public class SqlQueryProperties {
    private String select;
    private String insert;
    private String delete;
    private String update;
}
