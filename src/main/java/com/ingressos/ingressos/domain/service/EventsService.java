package com.ingressos.ingressos.domain.service;

import com.ingressos.ingressos.domain.repository.EventsRepository;
import com.ingressos.ingressos.domain.repository.entity.Events;
import com.ingressos.ingressos.domain.utils.EventsConvertUtils;
import com.ingressos.ingressos.web.dto.request.EventRequest;
import com.ingressos.ingressos.web.dto.response.EventResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EventsService {

    @Autowired
    EventsRepository eventsRepository;

    public Mono<EventResponse> createEvent(EventRequest eventRequest){
        Events eventsEntity = EventsConvertUtils.toEntity(eventRequest);
        Mono<Events> createdEvent = eventsRepository.save(eventsEntity);
        return createdEvent.map(EventsConvertUtils::toResponse);
    }

    public Flux<EventResponse> getAllEvents(){
        Flux<Events> eventsFlux = eventsRepository.findAll();
        return eventsFlux.map(EventsConvertUtils::toResponse);
    }
}
