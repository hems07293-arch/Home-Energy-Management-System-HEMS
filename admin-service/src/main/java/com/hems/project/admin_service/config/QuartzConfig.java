//package com.hems.project.admin_service.config;
//
//import com.hems.project.admin_service.entity.AutowiringSpringBeanJobFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//
//@Configuration
//public class QuartzConfig {
//
//    @Autowired
//    private AutowireCapableBeanFactory beanFactory;
//
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean() {
//        SchedulerFactoryBean factory = new SchedulerFactoryBean();
//        factory.setJobFactory(new AutowiringSpringBeanJobFactory(beanFactory));
//        return factory;
//    }
//}

package com.hems.project.admin_service.config;

import com.hems.project.admin_service.entity.AutowiringSpringBeanJobFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;
@Configuration
@RequiredArgsConstructor
public class QuartzConfig {

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @Autowired
    private DataSource dataSource;

    @Autowired(required = false)
    private PlatformTransactionManager transactionManager;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(QuartzProperties quartzProperties) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();

        // Step 1: Set custom job factory for Spring bean injection
        factory.setJobFactory(new AutowiringSpringBeanJobFactory(beanFactory));

        // Step 2: CRITICAL - Configure datasource (must be before setQuartzProperties)
        factory.setDataSource(dataSource);

        // Step 3: Set transaction manager
        if (transactionManager != null) {
            factory.setTransactionManager(transactionManager);
        }

        // Step 4: Configure Quartz properties WITH datasource mapping
        factory.setQuartzProperties(buildQuartzProperties(quartzProperties, dataSource));

        // Step 5: Scheduler configuration
        factory.setSchedulerName("HemsDispatchScheduler");
        factory.setAutoStartup(true);
        factory.setWaitForJobsToCompleteOnShutdown(true);

        return factory;
    }

    /**
     * Build complete Quartz properties for JDBC-based job store
     * The datasource is provided via setDataSource() so we DON'T define it in properties
     */
    private Properties buildQuartzProperties(QuartzProperties quartzProperties, DataSource ds) {
        Properties properties = new Properties();
        properties.putAll(quartzProperties.getProperties());

        // Scheduler instance configuration
        properties.setProperty("org.quartz.scheduler.instanceName", "HemsDispatchScheduler");
        properties.setProperty("org.quartz.scheduler.instanceId", "AUTO");

        properties.setProperty("org.quartz.threadPool.threadCount", "5");
        properties.setProperty("org.quartz.threadPool.threadPriority", "5");

        // Job store configuration - JDBC-backed for PostgreSQL
        properties.setProperty("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        properties.setProperty("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.PostgreSQLDelegate");
        properties.setProperty("org.quartz.jobStore.useProperties", "false");

        // IMPORTANT: When using setDataSource(), Quartz uses the Spring datasource by default
        // DO NOT set org.quartz.jobStore.dataSource property - it will cause "Driver not specified" error

        // Clustering disabled for single-node setup
        properties.setProperty("org.quartz.jobStore.isClustered", "false");

        // Misfire handling: wait 60 seconds before marking job as misfire
        properties.setProperty("org.quartz.jobStore.misfireThreshold", "60000");

        return properties;
    }
}