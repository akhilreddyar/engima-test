package com.example.demo.config;

import com.example.demo.enigma.DefaultImplementation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

@EnableAutoConfiguration
public class DefaultConfiguration {


    @Bean
    public DefaultImplementation getImp(){
        return new DefaultImplementation();
    }

}
