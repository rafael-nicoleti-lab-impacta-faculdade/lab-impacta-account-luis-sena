package br.com.lab.impacta.account.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "lab.account.exceptions")
public class AccountExceptionsProperties {
    private String accountDontExistsMessage;
    private String accountDontExistsDescription;
    private String accountWithoutBalanceMessage;
    private String accountWithoutBalanceDescription;
}
