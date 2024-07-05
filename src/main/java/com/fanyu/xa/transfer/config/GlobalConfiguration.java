package com.fanyu.xa.transfer.config;

import com.fanyu.xa.transfer.controller.MyErrorController;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableConfigurationProperties({ServerProperties.class})
public class GlobalConfiguration {

    private final ServerProperties serverProperties;

    public GlobalConfiguration(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Bean
    public MyErrorController myErrorController(
            ErrorAttributes errorAttributes, ObjectProvider<ErrorViewResolver> errorViewResolvers) {
        return new MyErrorController(errorAttributes, this.serverProperties.getError(), (List)errorViewResolvers.orderedStream().collect(
                Collectors.toList()));
    }
}
