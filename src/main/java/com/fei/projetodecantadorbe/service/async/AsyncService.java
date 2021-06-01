package com.fei.projetodecantadorbe.service.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AsyncService {

    private List<AsyncRequest> asyncRequests;

    public AsyncService(List<AsyncRequest> asyncRequests){
        this.asyncRequests = asyncRequests;
    }

    public void runAsyncRequests(){
        this.asyncRequests.forEach(asyncRequest -> {
            try {
                asyncRequest.asyncRequest();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
