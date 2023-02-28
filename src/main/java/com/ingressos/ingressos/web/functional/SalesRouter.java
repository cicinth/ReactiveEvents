package com.ingressos.ingressos.web.functional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class SalesRouter {

    @Bean
    public RouterFunction<ServerResponse> salesRoute(SalesHandler salesHandler){
        return RouterFunctions.route(
                RequestPredicates.GET("/sales"), salesHandler::getAllSales
        ).andRoute(RequestPredicates.POST("/sales"), salesHandler::createSale);
    }
}
