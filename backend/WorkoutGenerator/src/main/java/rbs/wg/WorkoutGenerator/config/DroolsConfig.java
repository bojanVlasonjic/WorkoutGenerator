package rbs.wg.WorkoutGenerator.config;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {

    private KieServices getKieServices() {
        return KieServices.Factory.get();
    }

    private KieContainer getKieContainer() {
        return getKieServices().getKieClasspathContainer();
    }

    @Bean
    public KieSession getKieSession() {
        return getKieContainer().newKieSession("WGSession");
    }
}
