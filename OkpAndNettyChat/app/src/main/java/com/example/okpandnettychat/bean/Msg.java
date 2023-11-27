package com.example.okpandnettychat.bean;

public class Msg {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;

    private String Content;
    private int type;
    private boolean isSuccessSend;//是否成功发送

    public Msg(String content, int type) {
        Content = content;
        this.type = type;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isSuccessSend() {
        return isSuccessSend;
    }

    public void setSuccessSend(boolean successSend) {
        isSuccessSend = successSend;
    }
}
