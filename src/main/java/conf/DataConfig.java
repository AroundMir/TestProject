package conf;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("entity")
@EnableJpaRepositories("DAO")
@EnableTransactionManagement
@PropertySource("classpath:data.application.properties")
@EnableJpaAuditing
public class DataConfig {
}
