package com.dnsloadbalance.interactionapi.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseObject {
    private String interactionMessage;
}
