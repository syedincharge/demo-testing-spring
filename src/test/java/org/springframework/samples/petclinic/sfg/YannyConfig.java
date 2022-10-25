package org.springframework.samples.petclinic.sfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class YannyConfig {

    @Bean
    public YannyWordProducer yannyWordProducer(){
        return new YannyWordProducer();
    }

}
