package com.ingressos.ingressos.domain.utils;

import com.ingressos.ingressos.domain.repository.entity.Sales;
import com.ingressos.ingressos.web.dto.request.SalesRequest;
import com.ingressos.ingressos.web.dto.response.SalesResponse;

public class SalesConvertUtils {
    public static SalesResponse toResponse(Sales saleEntity){
        SalesResponse response = new SalesResponse();
        response.setId(saleEntity.getId());
        response.setClientName(saleEntity.getClientName());
        response.setEventId(saleEntity.getEventId());
        return response;
    }

    public static Sales toEntity(SalesRequest salesRequest){
        Sales entity = new Sales();
        entity.setClientName(salesRequest.getClientName());
        entity.setEventId(salesRequest.getEventId());
        return entity;
    }
}
