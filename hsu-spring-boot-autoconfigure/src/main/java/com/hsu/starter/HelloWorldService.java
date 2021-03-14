package com.hsu.starter;

import org.springframework.beans.factory.annotation.Autowired;

public class HelloWorldService {

    @Autowired
    HelloWorldProperties properties;

    public String sayHello(String name) {
        return "Hello, " + name + ", " + properties.getMessage();
    }


}
