package com.fei.projetodecantadorbe.queue;

public interface Publisher<T> {

    void send(T payload);
}
