package com.dnsloadbalance.interactionapi.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InteracResponseObject {
    private String interactionMessage;
    private String message;
    private String hostName;
    private String serverAddress;
    private String serverPort;
}
