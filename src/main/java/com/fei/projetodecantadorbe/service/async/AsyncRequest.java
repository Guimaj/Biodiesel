package com.fei.projetodecantadorbe.service.async;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public interface AsyncRequest {

    @Async
    CompletableFuture<Void> asyncRequest() throws InterruptedException;
}
