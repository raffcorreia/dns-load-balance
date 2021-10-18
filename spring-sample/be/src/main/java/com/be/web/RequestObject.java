package com.be.web;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
public class RequestObject {

    @NonNull
    private Long waitForMilliseconds;
}
