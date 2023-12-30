package com.example.socialnetwork.service;

import com.example.socialnetwork.entity.Message;
import com.example.socialnetwork.entity.User;
import com.example.socialnetwork.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getMessagesBetweenUsers(Integer firstId, Integer secondId) {
        return messageRepository.findMessagesBySendersAndRecipients(firstId,secondId,secondId,firstId);
    }
    public void sendMessage(User sender, User recipient, String messageText) {
        Message message = new Message();
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setContent(messageText);
        messageRepository.save(message);
    }
}
