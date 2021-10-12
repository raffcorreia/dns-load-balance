package com.dnsloadbalance.interactionapi.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class InteractionController {

    RestTemplate restTemplate;

    @GetMapping(value = "/fromdownstream", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public InteracResponseObject getFromDownStream() {


        return InteracResponseObject.builder().interactionMessage("This is from the interaction API!").build();
    }
}
