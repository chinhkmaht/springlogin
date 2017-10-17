package org.LTT.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "org.LTT.service" })
public class ServiceConfig {
}
