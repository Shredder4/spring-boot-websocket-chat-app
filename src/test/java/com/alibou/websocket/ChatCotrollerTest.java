package com.alibou.websocket;

import com.alibou.websocket.chat.ChatController;
import com.alibou.websocket.chat.ChatMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

import static org.mockito.Mockito.*;

class ChatCotrollerTest {

    @InjectMocks
    private ChatController chatController;

    @Mock
    private SimpMessagingTemplate simpMessagingTemplate;

    @Mock
    private SimpMessageHeaderAccessor headerAccessor;

    @Mock
    private WebSocketSession webSocketSession;



    @Test
    void testSendMessage() {
        // Créer un message de test
        ChatMessage testMessage = new ChatMessage();
        testMessage.setSender("Test User");
        testMessage.setContent("Hello, World!");

        // Appeler la méthode sendMessage
        ChatMessage responseMessage = chatController.sendMessage(testMessage);

        // Vérifier que la méthode convertAndSend a bien été appelée
        verify(simpMessagingTemplate, times(1)).convertAndSend("/topic/public", responseMessage);

        // Vérifier que le message reçu est correct
        assert responseMessage != null;
        assert "Hello, World!".equals(responseMessage.getContent());
    }

    @Test
    void testAddUser() {
        // Créer un message de test
        ChatMessage testMessage = new ChatMessage();
        testMessage.setSender("Test User");
        testMessage.setContent("Hello, World!");

        // Simuler l'ajout d'un attribut à la session
        when(headerAccessor.getSessionAttributes()).thenReturn(new java.util.HashMap<>());

        // Appeler la méthode addUser
        ChatMessage responseMessage = chatController.addUser(testMessage, headerAccessor);

        // Vérifier que l'attribut "username" a bien été ajouté
        verify(headerAccessor.getSessionAttributes(), times(1)).put("username", "Test User");

        // Vérifier que la méthode retourne le bon message
        assert responseMessage != null;
        assert "Test User".equals(responseMessage.getSender());
    }
}



