package com.ingressos.ingressos.web.dto.request;

import lombok.Getter;

@Getter
public class EventRequest {
    private String eventName;
    private Integer totalAmountTickets;
    private Integer ticketsSold;
}
