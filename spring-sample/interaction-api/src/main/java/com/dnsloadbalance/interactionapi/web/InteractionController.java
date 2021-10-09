package com.dnsloadbalance.interactionapi.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InteractionController {

        @GetMapping(value = "/fromdownstream", produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public ResponseObject getFromDownStream() {
            return ResponseObject.builder().interactionMessage("This is from the interaction API!").build();
        }
}
