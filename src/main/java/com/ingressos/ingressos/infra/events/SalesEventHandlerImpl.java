package com.ingressos.ingressos.infra.events;

import com.ingressos.ingressos.infra.email.SendEmail;
import com.ingressos.ingressos.web.dto.response.SalesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Schedulers;

@Component
public class SalesEventHandlerImpl implements SalesEventHandler {
    @Autowired
    SalesEventPublisher salesEventPublisher;

    @Autowired
    SendEmail sendEmail;

    public SalesEventHandlerImpl(SalesEventPublisher newsletterPublisher){
        newsletterPublisher.getNewsFlux().subscribeOn(Schedulers.newSingle("new thred")).subscribe(
                news -> {
                    try {
                        sendEmail.send(news);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> System.out.println("error " + error)
        );
    }

    @Override
    public void sendEvent(SalesResponse salesResponse) {
        salesEventPublisher.publish(salesResponse);
    }
}
