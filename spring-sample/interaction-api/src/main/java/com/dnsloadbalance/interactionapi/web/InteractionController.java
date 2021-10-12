package com.dnsloadbalance.interactionapi.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

import static java.util.Objects.nonNull;

@RestController
public class InteractionController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${beEndpointURL}")
    String beEndpointURL;

    @GetMapping(value = "/fromdownstream", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public InteracResponseObject getFromDownStream() {
        InteracResponseObject response = new InteracResponseObject();

        ResponseEntity<BEResponseObject> responseEntity;
        responseEntity = restTemplate.getForEntity(beEndpointURL, BEResponseObject.class);

        if(nonNull(responseEntity.getBody())) {
            BeanUtils.copyProperties(responseEntity.getBody(), response);
        }

        response.setInteractionMessage("This is from the interaction API!");

        return response;
    }
}
