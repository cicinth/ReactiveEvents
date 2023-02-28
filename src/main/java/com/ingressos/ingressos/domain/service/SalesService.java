package com.ingressos.ingressos.domain.service;

import com.ingressos.ingressos.domain.exceptions.TicketsNotAvailableException;
import com.ingressos.ingressos.domain.repository.EventsRepository;
import com.ingressos.ingressos.domain.repository.SalesRepository;
import com.ingressos.ingressos.domain.repository.entity.Events;
import com.ingressos.ingressos.domain.repository.entity.Sales;
import com.ingressos.ingressos.domain.utils.SalesConvertUtils;
import com.ingressos.ingressos.infra.events.SalesEventHandler;
import com.ingressos.ingressos.web.dto.request.SalesRequest;
import com.ingressos.ingressos.web.dto.response.SalesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class SalesService {

    @Autowired
    SalesRepository salesRepository;

    @Autowired
    EventsRepository eventsRepository;

    @Autowired
    SalesEventHandler salesEventHandler;
    public Mono<SalesResponse> createSale(SalesRequest salesRequest){
        Mono<Events> eventMono = eventsRepository.findById(salesRequest.getEventId());

        Mono<SalesResponse> salesResponseMono =  eventMono.flatMap( event -> {
            if(event.getTicketsSold() >= event.getTotalAmountTickets()) return Mono.error(new TicketsNotAvailableException("O evento esta esgotado"));
            event.setTicketsSold(event.getTicketsSold() + 1);
            Sales salesEntity = SalesConvertUtils.toEntity(salesRequest);
            Mono<Sales> createdSale =  salesRepository.save(salesEntity);
            createdSale.map(SalesConvertUtils::toResponse);
            return createdSale.flatMap(sales -> eventsRepository.save(event).thenReturn(SalesConvertUtils.toResponse(sales)));
        }).onErrorResume(TicketsNotAvailableException.class, exception -> Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage())));

        salesResponseMono.subscribeOn(Schedulers.newSingle("nova")).subscribe(salesEventHandler::sendEvent);
      return salesResponseMono;
    }

    public Flux<SalesResponse> getAllSales(){
        Flux<Sales> salesFlux = salesRepository.findAll();
        return salesFlux.map(SalesConvertUtils::toResponse);
    }
}
