package com.be.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseObject {
    private String message;
    private String hostName;
    private String serverAddress;
    private String serverPort;
    private String serverDataTime;
}
