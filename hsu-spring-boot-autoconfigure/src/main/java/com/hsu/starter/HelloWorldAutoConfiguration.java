package com.hsu.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(HelloWorldProperties.class)
@ConditionalOnClass({HelloWorldService.class})
@Configuration
public class HelloWorldAutoConfiguration {

    @Bean
    public HelloWorldService helloWorldService() {
        HelloWorldService service = new HelloWorldService();
        return service;
    }

}
