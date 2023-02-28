package com.ingressos.ingressos.infra.events;

import com.ingressos.ingressos.web.dto.response.SalesResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
public class SalesEventPublisher {
    private Sinks.Many<SalesResponse> saleSinks;

    public SalesEventPublisher(){
        this.saleSinks = Sinks.many().multicast().onBackpressureBuffer();
    }
    public void publish(SalesResponse salesResponse){
        this.saleSinks.tryEmitNext(salesResponse);
    }
    public Flux<SalesResponse> getNewsFlux() {
        return this.saleSinks.asFlux();
    }
}
