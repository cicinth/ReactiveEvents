package com.ingressos.ingressos.infra.email;

import com.ingressos.ingressos.web.dto.response.SalesResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendEmail {

    public void send(SalesResponse salesResponse) throws InterruptedException {
        Thread.sleep(8000);
        System.out.println("enviando email para "+ salesResponse.getClientName());
    }
}
