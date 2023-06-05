package services;

import model.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageService {
    private HashMap<Long , Message> messages = new HashMap<>();

    public MessageService() {
        Message m1 = new Message(1, "First message", "Filip");
        Message m2 = new Message(2, "Second message", "Monika");
        Message m3 = new Message(3, "Third message", "Kasper");

        messages.put(m1.getId(), m1);
        messages.put(m2.getId(), m2);
        messages.put(m3.getId(), m3);
    }

    public List<Message> getAllMessages() {
        return new ArrayList<>(messages.values());
    }

    public Message getMessage(long id) {
        return messages.get(id);
    }

    public Message createMessage(Message message) {
        message.setId(messages.size() + 1);
        messages.put(message.getId(), message);
        return message;
    }

    public Message updateMessage(Message message, Long messageId) {
        Message oldMessage = messages.get(messageId);
        oldMessage.setMessage(message.getMessage());
        oldMessage.setId(message.getId());
        oldMessage.setCreated(message.getCreated());
        oldMessage.setName(message.getName());
        return message;
    }

    public void deleteMessage(Long messageId) {
        messages.remove(messageId);
    }
}
