package com.dnsloadbalance.interactionapi.web;

import lombok.Data;

import java.time.Instant;

@Data
public class BEResponseObject {
    private String message;
    private String hostName;
    private String serverAddress;
    private String serverPort;
    private Instant serverDataTime;
}
