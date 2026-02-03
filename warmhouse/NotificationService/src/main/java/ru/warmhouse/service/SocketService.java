package ru.warmhouse.service;

public interface SocketService {
    void connect();

    void sendToDevice(String message);
}
