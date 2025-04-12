package com.chat.chatcommon;

import java.io.Serializable;

/**
 * @FileName Massage
 * @Description
 * Использоваться для передачи информации между сервером и клиентом
 **/
public class Message implements Serializable, MessageType {

    private String sender;
    private String getter;
    private String content;
    private String sendTime;
    private String messageType;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
