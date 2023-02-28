package com.ingressos.ingressos.domain.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Table("events")
public class Events {
    @Id
    @Column("id")
    private Integer id;

    @Column("event_name")
    private String eventName;

    @Column("total_amount_tickets")
    private Integer totalAmountTickets;

    @Column("tickets_sold")
    private Integer ticketsSold;
}
