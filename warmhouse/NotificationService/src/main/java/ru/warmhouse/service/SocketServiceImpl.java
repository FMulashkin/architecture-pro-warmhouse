package ru.warmhouse.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class SocketServiceImpl implements SocketService {

    @Value("${device.management.url}")
    private String deviceManagementUrl;

    private WebSocketSession session;

    @Override
    public void connect() {
        var client = new StandardWebSocketClient();
        var handler = new TextWebSocketHandler() {
            @Override
            public void afterConnectionEstablished(WebSocketSession session) {
                SocketServiceImpl.this.session = session;
                System.out.println("WebSocket подключён к " + deviceManagementUrl);
            }
        };

        var manager = new WebSocketConnectionManager(client, handler, deviceManagementUrl + "/ws/device-status");
        manager.start();
    }

    @Override
    public void sendToDevice(String message) {
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (Exception e) {
                System.err.println("Ошибка отправки через WebSocket: " + e.getMessage());
            }
        }
    }
}
