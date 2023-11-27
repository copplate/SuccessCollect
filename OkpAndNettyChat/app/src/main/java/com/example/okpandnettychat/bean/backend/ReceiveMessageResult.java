package com.example.okpandnettychat.bean.backend;

public class ReceiveMessageResult extends ChatMessage {//接收到服务端的消息后，给它返回一个结果
    private boolean flag;
    private String data;
    private String msg;

    public ReceiveMessageResult(ChatMessage chatMessage, boolean flag, String data, String msg) {
        super(new ConnectCommand(chatMessage.getCode(),chatMessage.getNickName()), chatMessage.getType(), chatMessage.getTarget(), chatMessage.getContent());
        this.flag = flag;
        this.data = data;
        this.msg = msg;
    }

    /*public static String success(ChatMessage chatMessage, String message) {

        return new TextWebSocketFrame(JSON.toJSONString(new ReceiveMessageResult(chatMessage,true, message,null)));
    }*/

    public boolean isFlag() {
        return flag;
    }

    public String getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }
}
