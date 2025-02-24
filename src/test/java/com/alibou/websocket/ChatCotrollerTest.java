package com.alibou.websocket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.alibou.websocket.chat.ChatController;
import com.alibou.websocket.chat.ChatMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

class ChatCotrollerTest {

    @InjectMocks
    private ChatController chatController;

    @Mock
    private SimpMessageHeaderAccessor headerAccessor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMessage() {
        // Given
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender("Ayoub");
        chatMessage.setContent("Hello World");

        // When
        ChatMessage result = chatController.sendMessage(chatMessage);

        // Then
        assertNotNull(result);
        assertEquals("Ayoub", result.getSender());
        assertEquals("Hello World", result.getContent());
    }

    @Test
    void testAddUser() {
        // Given
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender("Ayoub");

        // When
        ChatMessage result = chatController.addUser(chatMessage, headerAccessor);

        // Then
        assertNotNull(result);
        assertEquals("Ayoub", result.getSender());
        verify(headerAccessor, times(1)).getSessionAttributes();
    }
}
