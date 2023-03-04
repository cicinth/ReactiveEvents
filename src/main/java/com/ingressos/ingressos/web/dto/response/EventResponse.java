package com.ingressos.ingressos.web.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventResponse {
    private Integer id;
    private String eventName;
    private Integer totalAmountTickets;
    private Integer ticketsSold;
}
