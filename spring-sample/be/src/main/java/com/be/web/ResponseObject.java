package com.be.web;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
@Builder
public class ResponseObject {
    private String message;
    private String hostName;
    private String serverAddress;
    private String serverPort;
}
