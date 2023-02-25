package com.ingressos.ingressos.domain.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Table("sales")
public class Sales {
    @Id
    private Integer id;
    private String clientName;
    private Integer eventId;
}
