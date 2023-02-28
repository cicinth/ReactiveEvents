package com.ingressos.ingressos.web.functional;

import com.ingressos.ingressos.domain.service.SalesService;
import com.ingressos.ingressos.web.dto.request.SalesRequest;
import com.ingressos.ingressos.web.dto.response.SalesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SalesHandler {

    @Autowired
    private SalesService salesService;

    public Mono<ServerResponse> createSale(ServerRequest serverRequest){
        final Mono<SalesRequest> salesRequestMono = serverRequest.bodyToMono(SalesRequest.class);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(salesRequestMono.flatMap(salesService::createSale), SalesResponse.class));
    }

    public Mono<ServerResponse> getAllSales(ServerRequest serverRequest){
        Flux<SalesResponse> salesResponseFlux = salesService.getAllSales();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(salesResponseFlux, SalesResponse.class);
    }
}
