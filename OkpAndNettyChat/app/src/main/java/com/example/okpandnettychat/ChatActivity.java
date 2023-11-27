package com.example.okpandnettychat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.okpandnettychat.adapter.MessageAdapter;
import com.example.okpandnettychat.bean.Msg;
import com.example.okpandnettychat.bean.backend.ChatMessage;
import com.example.okpandnettychat.bean.backend.ConnectCommand;
import com.example.okpandnettychat.bean.backend.ReceiveMessageResult;
import com.example.okpandnettychat.bean.backendhand.ConnectCommandHand;
import com.example.okpandnettychat.enum_.ChatMessageType;
import com.example.okpandnettychat.enum_.CommandType;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class ChatActivity extends AppCompatActivity {
    private EditText etInput;
    private Button btnSend;
    private RecyclerView rlvChat;
    private MessageAdapter messageAdapter;
    private List<Msg> msgList = new ArrayList<>();
    private String userName;
    private String friendName;
    private OkHttpClient wsHttpClient;
    private WebSocket webSocket;
    private TextView tvFriendName;
    private boolean isLogin = false;//是否和服务器建立了通信
    private List<Integer> indexList = new ArrayList<>();//我发出的消息在总消息列表的下标，列表
    private int lastSendMessageIndex;//上一个发送的消息，在indexList的下标

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        etInput = findViewById(R.id.et_input);
        btnSend = findViewById(R.id.btn_send);
        rlvChat = findViewById(R.id.rlv_chat);
        tvFriendName = findViewById(R.id.tv_friend_name);
        Intent intent = getIntent();
        userName = intent.getStringExtra("username");
        if ("张无忌".equals(userName)) {
            friendName = "谢逊";
        }else{
            friendName = "张无忌";
        }
        tvFriendName.setText(friendName);
        initMsgList();
        messageAdapter = new MessageAdapter(msgList, this);
        rlvChat.setLayoutManager(new LinearLayoutManager(this));
        rlvChat.setAdapter(messageAdapter);

        wsHttpClient = new OkHttpClient.Builder()
                .pingInterval(10, TimeUnit.SECONDS) // 设置 PING 帧发送间隔
                .build();
        Request request = new Request.Builder()
                .url("ws://192.168.3.234:8080")
                .build();
        
        webSocket = wsHttpClient.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onClosed(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
                super.onClosed(webSocket, code, reason);
                Log.d("tiktok", "onClosed: -------code---" + code + "--reason---" + reason);
            }

            @Override
            public void onClosing(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
                super.onClosing(webSocket, code, reason);
                Log.d("tiktok", "onClosing: -------code---" + code + "--reason---" + reason);

            }

            @Override
            public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable t, @Nullable Response response) {
                super.onFailure(webSocket, t, response);
                Log.d("tiktok", "onFailure: -------" + t);


            }

            @Override
            public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
                super.onMessage(webSocket, text);
                String receiveMessage = text;
                Log.d("tiktok", "onMessage: ----text---" + receiveMessage);
                ConnectCommand connectCommand = new Gson().fromJson(receiveMessage, ConnectCommand.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        switch (CommandType.match(connectCommand.getCode())) {
                            case CONNECTION:
                                ConnectCommandHand connectCommandHand = new Gson().fromJson(text, ConnectCommandHand.class);
                                if (connectCommandHand.isFlag()) {
                                    isLogin = true;
                                    Toast.makeText(ChatActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    isLogin = false;
                                    Toast.makeText(ChatActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case DISCONNECTION:
                                ConnectCommandHand connectCommandHand1 = new Gson().fromJson(text, ConnectCommandHand.class);
                                if (connectCommandHand1.isFlag()) {
                                    isLogin = false;
                                    Log.d("tiktok", "run: -----下线成功----");
                                    Toast.makeText(ChatActivity.this, "下线成功", Toast.LENGTH_SHORT).show();
                                    webSocket.close(1000, "Closing connection");
                                } else {
                                    isLogin = true;
                                    Toast.makeText(ChatActivity.this, "下线失败", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case RECEIVE_HAND://发送消息后，准备接受服务端发送的握手确认消息(p.s:可以用时间戳 + 消息来保证消息的唯一性)
                                ReceiveMessageResult receiveMessageResult =
                                        new Gson().fromJson(receiveMessage, ReceiveMessageResult.class);
                                if (receiveMessageResult.isFlag()) {
                                    Log.d("tiktok", "SEND_HAND: ---消息:" + receiveMessageResult.getContent() + "--发送成功--");
                                }
                                break;
                            case RECEIVE:
                                ChatMessage chatMessage = new Gson().fromJson(receiveMessage, ChatMessage.class);
                                String content = chatMessage.getContent();
                                Msg msg = new Msg(content, Msg.TYPE_RECEIVED);
                                msgList.add(msg);
                                messageAdapter.notifyItemInserted(msgList.size() - 1);
                                rlvChat.scrollToPosition(msgList.size());
                                chatMessage.setCode(CommandType.SEND_HAND.getCode());
                                ReceiveMessageResult receiveMessageResult2 = new ReceiveMessageResult(chatMessage,
                                        true,"客户端已接收到消息",null);
                                // 创建Gson对象
                                Gson gson = new Gson();
                                // 将对象转换为JSON字符串
                                String json = gson.toJson(receiveMessageResult2);
                                webSocket.send(json);
                                break;
                        }
                    }
                });

                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Msg msg = new Msg(text, Msg.TYPE_RECEIVED);
                        msgList.add(msg);
                        messageAdapter.notifyItemInserted(msgList.size() - 1);
                    }
                });*/


            }

            @Override
            public void onMessage(@NonNull WebSocket webSocket, @NonNull ByteString bytes) {
                super.onMessage(webSocket, bytes);
                Log.d("tiktok", "onMessage: ----bytes---" + bytes);
            }

            @Override
            public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
                super.onOpen(webSocket, response);
                try {
                    Log.d("tiktok", "onOpen: -------" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnSend.setOnClickListener(v -> {
            String s = etInput.getText().toString();
            if (s != null && s.length() > 0) {
                Msg msg = new Msg(s, Msg.TYPE_SENT);
                int size = msgList.size();
                indexList.add(size);
                lastSendMessageIndex = indexList.size() - 1;
                msgList.add(size,msg);
                messageAdapter.notifyItemInserted(size);
                rlvChat.scrollToPosition(size);

                ChatMessage chatMessage = new ChatMessage(new ConnectCommand(CommandType.SEND.getCode(),userName),
                        ChatMessageType.PRIVATE.getType(),
                        friendName, s);
                // 创建Gson对象
                Gson gson = new Gson();
                // 将对象转换为JSON字符串
                String json = gson.toJson(chatMessage);
                webSocket.send(json);
                etInput.setText("");
            }
        });

        connect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("tiktok", "onDestroy: --ChatActivity-------");
        disConnect();
//        webSocket.close(1000, "Closing connection");
    }

    private void initMsgList() {
        msgList.add(new Msg("Hello guy.", Msg.TYPE_RECEIVED));
        /*msgList.add(new Msg("Hello. Who are you?", Msg.TYPE_SENT));
        msgList.add(new Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED));*/

    }

    private void connect() {
        ConnectCommand connectCommand = new ConnectCommand(CommandType.CONNECTION.getCode(), userName);
        // 创建Gson对象
        Gson gson = new Gson();
        // 将对象转换为JSON字符串
        String json = gson.toJson(connectCommand);
        webSocket.send(json);

    }

    private void disConnect() {
        ConnectCommand connectCommand = new ConnectCommand(CommandType.DISCONNECTION.getCode(), userName);
        // 创建Gson对象
        Gson gson = new Gson();
        // 将对象转换为JSON字符串
        String json = gson.toJson(connectCommand);
        webSocket.send(json);
    }


}