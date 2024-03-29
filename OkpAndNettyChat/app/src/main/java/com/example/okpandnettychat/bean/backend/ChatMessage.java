package com.example.okpandnettychat.bean.backend;

public class ChatMessage extends ConnectCommand {

    /**
     * 消息类型
     * */
    private Integer type;//有可能是私聊消息，也有可能是群聊消息

    /**
     * 目标接收对象
     * */
    private String target;//接收人是谁，把消息发给谁

    /**
     * 内容
     * */
    private String content;

    public ChatMessage(ConnectCommand connectCommand, Integer type, String target, String content) {
        super(connectCommand.getCode(), connectCommand.getNickName());
        this.type = type;
        this.target = target;
        this.content = content;
    }

    public ChatMessage() {

    }

    public Integer getType() {
        return type;
    }

    public String getTarget() {
        return target;
    }

    public String getContent() {
        return content;
    }
}
