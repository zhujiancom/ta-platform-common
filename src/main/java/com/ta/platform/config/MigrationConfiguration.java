package com.ta.platform.config;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Flyway.class)
@Slf4j
public class MigrationConfiguration {

//    @Value("${spring.flyway.out-of-order}")
//    private boolean outOfOrder;
//
//    @Value("${spring.flyway.validate-on-migrate}")
//    private boolean validateOnMigrate;

    @Bean
    public FlywayMigrationInitializer flywayInitializer(Flyway flyway){
//        flyway.setOutOfOrder(outOfOrder);
//        flyway.setValidateOnMigrate(false);
        return new FlywayMigrationInitializer(flyway, new FlywayMigrationStrategy() {
            @Override
            public void migrate(Flyway flyway) {
                try {
                    flyway.migrate();
                } catch (FlywayException e) {
                    log.error("Flyway migration failed, doing a repair and retrying ...");
                    flyway.repair();
                    flyway.migrate();
                }
            }
        });
    }
}