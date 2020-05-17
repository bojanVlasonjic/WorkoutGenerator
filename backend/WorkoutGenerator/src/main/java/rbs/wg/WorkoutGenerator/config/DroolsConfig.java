package rbs.wg.WorkoutGenerator.config;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class DroolsConfig {

    @Bean
    public KieContainer getKieContainer() {
        return KieServices.Factory.get().getKieClasspathContainer();
    }

    @Bean
    public KieSession getKieSession() {
        return getKieContainer().newKieSession("WGSession");
    }


    /** Session global variables */
   /* @Bean
    public Random getRandom() {
        return new Random();
    }*/

}
