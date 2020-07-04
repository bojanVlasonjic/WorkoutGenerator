package rbs.wg.WorkoutGenerator.config;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rbs.wg.WorkoutGenerator.util.KieSessionDynamic;

import java.util.Random;

@Configuration
public class DroolsConfig {

    @Bean
    public KieServices getKieServices() {
        return KieServices.Factory.get();
    }

    @Bean
    public KieContainer getKieContainer() {
        return getKieServices().getKieClasspathContainer();
    }

    @Bean
    public KieSessionDynamic getKieSessionDynamic() {
        return new KieSessionDynamic();
    }

    /** Session global variables */
    @Bean
    public Random getRandom() {
        return new Random();
    }

}
