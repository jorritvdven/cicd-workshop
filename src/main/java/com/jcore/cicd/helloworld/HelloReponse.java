package com.jcore.cicd.helloworld;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HelloReponse {
    private final String message;
}
