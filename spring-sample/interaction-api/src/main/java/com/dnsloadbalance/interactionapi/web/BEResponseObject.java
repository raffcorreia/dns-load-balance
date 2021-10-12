package com.dnsloadbalance.interactionapi.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BEResponseObject {
    private String message;
    private String hostName;
    private String serverAddress;
    private String serverPort;
}
