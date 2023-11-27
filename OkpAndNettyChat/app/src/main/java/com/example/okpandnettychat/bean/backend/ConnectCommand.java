package com.example.okpandnettychat.bean.backend;

public class ConnectCommand {

    /**
     * 连接信息编码
     * */
    private Integer code;//状态码1、发群消息2、建立连接。根据这些指令，才能进行相对应的操作
    /**
     * 昵称
     * */
    private String nickName;
    /**
     * 创建时间
     */
    private String time;

    public ConnectCommand(Integer code, String nickName) {
        this.code = code;
        this.nickName = nickName;
        this.time = System.currentTimeMillis() + "";
    }

    public ConnectCommand() {

    }

    public Integer getCode() {
        return code;
    }

    public String getNickName() {
        return nickName;
    }

    public String getTime() {
        return time;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
