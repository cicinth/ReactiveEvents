package com.ingressos.ingressos.infra.events;

import com.ingressos.ingressos.web.dto.response.SalesResponse;
import org.springframework.stereotype.Component;

@Component
public interface SalesEventHandler {
    void sendEvent(SalesResponse salesResponse);
}
