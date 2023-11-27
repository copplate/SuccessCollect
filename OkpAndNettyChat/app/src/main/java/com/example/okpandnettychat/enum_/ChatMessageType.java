package com.example.okpandnettychat.enum_;

public enum ChatMessageType {//私聊消息和群聊消息
    /**
     * 私聊
     */
    PRIVATE(1),
    /**
     * 群聊
     */
    GROUP(2),
    /**
     * 向服务端发送消息给别人
     * */
    SEND(3),
    /**
     * 从服务端接收别人的消息
     * */
    RECEIVE(4),
    /**
     * 不支持的类型
     */
    ERROR(-1);

    private final Integer type;//根据code码,对应不同的类型

    ChatMessageType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }


}
