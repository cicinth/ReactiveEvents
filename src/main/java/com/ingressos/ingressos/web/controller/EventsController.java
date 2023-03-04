package com.ingressos.ingressos.web.controller;

import com.ingressos.ingressos.domain.service.EventsService;
import com.ingressos.ingressos.web.dto.request.EventRequest;
import com.ingressos.ingressos.web.dto.response.EventResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/events")
public class EventsController {
    @Autowired
    EventsService eventsService;

    @PostMapping
    public Mono<ResponseEntity<EventResponse>> createEvent(@RequestBody EventRequest eventRequest){
        Mono<EventResponse> createdEvent = eventsService.createEvent(eventRequest);
        return createdEvent.map(
                event -> ResponseEntity.created(URI.create("/events/"+ event.getId())).body(event)
        );
    }

    @GetMapping
    public ResponseEntity<Flux<EventResponse>> getAllEvents(){
        Flux<EventResponse> events = eventsService.getAllEvents();
        return ResponseEntity.ok(events);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Mono<EventResponse>> getEventById(@PathVariable Integer id){
        Mono<EventResponse> eventResponseMono = eventsService.getEventById(id);
        return ResponseEntity.ok().body(eventResponseMono);
    }
}
