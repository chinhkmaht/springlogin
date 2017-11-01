package org.LTT.web.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class MessageModel {
    public final static String TYPE_MESSAGE_SUCCESS = "1";
    public final static String TYPE_MESSAGE_ERROR = "2";

    private String typeMessage;
    private List<String> messages;

    public MessageModel() {
        messages = new ArrayList<>();
    }

    public String getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(String typeMessage) {
        this.typeMessage = typeMessage;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
