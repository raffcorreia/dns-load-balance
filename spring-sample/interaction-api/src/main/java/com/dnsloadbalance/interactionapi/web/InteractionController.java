package com.dnsloadbalance.interactionapi.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@RestController
public class InteractionController {

    @Autowired
    @Qualifier("restTemplateComponents")
    RestTemplate restTemplateComponents;

    @Qualifier("restTemplateSimpleConnection")
    @Autowired
    RestTemplate restTemplateSimpleConnection;

    @Value("${beEndpointURL}")
    String beEndpointURL;

    @ResponseBody
    @PostMapping(value = "/fromHttpComponentsClientFactory", produces = MediaType.APPLICATION_JSON_VALUE)
    public InteracResponseObject getFromHttpComponentsClientFactory(@RequestBody Object request) {
        log.debug("Entering fromHttpComponentsClientFactory");
        return getFromBE(restTemplateComponents, request);
    }

    @ResponseBody
    @PostMapping(value = "/fromSimpleClientFactory", produces = MediaType.APPLICATION_JSON_VALUE)
    public InteracResponseObject getFromSimpleClientFactory(@RequestBody Object request) {
        log.debug("Entering fromSimpleClientFactory");
        return getFromBE(restTemplateSimpleConnection, request);
    }

    private InteracResponseObject getFromBE(RestTemplate restTemplate, Object request) {
        InteracResponseObject response = new InteracResponseObject();

        String hostInfo = getHostInfo(beEndpointURL);

        ResponseEntity<BEResponseObject> responseEntity;
        try {
            responseEntity = restTemplate.postForEntity(beEndpointURL, request, BEResponseObject.class);
            if (nonNull(responseEntity.getBody())) {
                BeanUtils.copyProperties(responseEntity.getBody(), response);
            }
            response.setInteractionMessage("This is from the interaction API!");
        } catch (Exception ex) {
            response.setInteractionMessage("Error: " + ex.getMessage());
        }

        response.setHostInfo(hostInfo);

        return response;
    }

    private String getHostInfo(String url) {
        if(isNull(url)) {
            return null;
        }

        String hostInfo = null;
        try {
            InetAddress address = InetAddress.getByName(url.replace("http://", "").split(":")[0]);
            hostInfo = address.getHostName() + " = {" + address.getHostAddress() + "}";
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostInfo;
    }
}
