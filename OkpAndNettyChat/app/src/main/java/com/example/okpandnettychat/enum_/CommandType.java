package com.example.okpandnettychat.enum_;

public enum CommandType {//指令的类型枚举
    //我们需要通过连接建立映射关系,建立连接的主要作用是给它放到关系表里面
    /**
     * 建立连接
     * */
    CONNECTION(10001),
    /**
     * 聊天消息
     * */
    CHAT(10002),
    /**
     * 断开连接
     * */
    DISCONNECTION(10003),
    /**
     * 向服务端发送消息给别人
     * */
    SEND(10004),
    /**
     * 从服务端接收别人的消息
     * */
    RECEIVE(10005),
    /**
     * 向服务端发送一次握手确认消息
     * */
    SEND_HAND(10006),
    /**
     * 从服务端接收一次握手确认消息
     * */
    RECEIVE_HAND(10007),
    ERROR(-1),
    ;

    private final Integer code;//根据code码,对应不同的类型

    CommandType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    //写一个match方法，根据怎么获取到我们的，去匹配一下
    public static CommandType match(Integer code) {//返回这个枚举类型
        //到这边就不去加那些缓存了，直接用一个for循环来处理
        for (CommandType value : CommandType.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return ERROR;//如果找不到匹配的枚举，就返回ERROR。
    }
}
