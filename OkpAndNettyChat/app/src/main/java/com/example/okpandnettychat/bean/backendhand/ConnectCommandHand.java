package com.example.okpandnettychat.bean.backendhand;

import com.example.okpandnettychat.bean.backend.ConnectCommand;

public class ConnectCommandHand extends ConnectCommand {
    private boolean flag;
    private String data;
    private String msg;


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
