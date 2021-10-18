package com.be.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;

@RestController
public class SampleController {

    @Autowired
    private Environment environment;


    @ResponseBody
    @PostMapping(value = "/sample", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject getFromDownStream(@RequestBody RequestObject request) throws InterruptedException {

        Thread.sleep(request.getWaitForMilliseconds());

        String serverAddress = null;
        String hostName = null;
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            hostName = localHost.getHostName();
            serverAddress = localHost.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return ResponseObject.builder()
                .message("This is from the BE API!")
                .hostName(hostName)
                .serverAddress(serverAddress)
                .serverPort(environment.getProperty("local.server.port"))
                .serverDataTime(Instant.now())
                .build();
    }
}
