package com.ingressos.ingressos.domain.utils;

import com.ingressos.ingressos.domain.repository.entity.Events;
import com.ingressos.ingressos.web.dto.request.EventRequest;
import com.ingressos.ingressos.web.dto.response.EventResponse;

public class EventsConvertUtils {

    public static EventResponse toResponse(Events entity){
        EventResponse response = new EventResponse();
        response.setId(entity.getId());
        response.setEventName(entity.getEventName());
        response.setTotalAmountTickets(entity.getTotalAmountTickets());
        return  response;
    }
    public static Events toEntity(EventRequest eventRequest){
        Events eventsEntity = new Events();
        eventsEntity.setEventName(eventRequest.getEventName());
        eventsEntity.setTotalAmountTickets(eventRequest.getTotalAmountTickets());
        eventsEntity.setTicketsSold(0);
        return eventsEntity;
    }

}
