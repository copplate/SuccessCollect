package com.example.okpsocketprac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import okhttp3.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val wsHttpClient by lazy {
        OkHttpClient.Builder()
            .pingInterval(10, TimeUnit.SECONDS) // 设置 PING 帧发送间隔
            .build()
    }
    private lateinit var btnEmitJsonAgain:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnEmitJsonAgain = findViewById(R.id.btn_emit_json_again)
        val request = Request.Builder()
            .url("ws://192.168.3.234:8080")
            .build()

        val webSocket1 = wsHttpClient.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                Log.i("tiktok", "WS connection successful")
                // WebSocket 连接建立
            }


            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                Log.i("tiktok", "openWs onMessage $text")
                // 收到服务端发送来的 String 类型消息
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                Log.i("tiktok", "onFailure openWs onMessage --response$response --t$t")
            }
        })
        btnEmitJsonAgain.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                webSocket1.send("{\"content\":\"你好啊\"," +
                        "\"target\":\"山重水复疑无路\",\"type\":1," +
                        "\"code\":10002,\"nickName\":\"谢逊\"}")
            }
        })

        webSocket1.send("{\n" +
                "    \"code\": 10001,\n" +
                "    \"nickName\": \"山重水复疑无路\"\n" +
                "}")
        /*webSocket1.send("{\"content\":\"你好啊\"," +
                "\"target\":\"山重水复疑无路\",\"type\":1," +
                "\"code\":10002,\"nickName\":\"谢逊\"}")*/

    }
}