package hfe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

@Configuration
@EnableTransactionManagement
public class HfeTransaction {
    @Inject
    private LocalContainerEntityManagerFactoryBean lef;

    @Bean
    public EntityManager getEntityManager() {
        return lef.getObject().createEntityManager();
    }

    @Bean()
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(lef.getObject());
        Logger.getLogger(getClass().getSimpleName()).info(transactionManager.getJpaDialect().toString());
        return transactionManager;
    }
}
