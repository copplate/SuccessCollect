package com.shangeyun.guolinchatbubbleprac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shangeyun.guolinchatbubbleprac.adapter.MsgAdapter
import com.shangeyun.guolinchatbubbleprac.bean.Msg

class MainActivity : AppCompatActivity() {
    private val msgList = ArrayList<Msg>()
    private var adapter: MsgAdapter? = null
    private lateinit var rlv:RecyclerView
    private lateinit var btnSend:Button
    private lateinit var inputText:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        rlv = findViewById(R.id.recyclerView)
        btnSend = findViewById(R.id.send)
        inputText = findViewById(R.id.inputText)
        initMsg()
        val layoutManager = LinearLayoutManager(this)
        rlv.layoutManager = layoutManager
        adapter = MsgAdapter(msgList)
        rlv.adapter = adapter
        btnSend.setOnClickListener{
            val content = inputText.text.toString()
            if (content.isNotEmpty()) {
                val msg = Msg(content, Msg.TYPE_SENT)
                msgList.add(msg)
                adapter?.notifyItemInserted(msgList.size - 1) // 当有新消息时，
                //刷新RecyclerView中的显示
                rlv.scrollToPosition(msgList.size - 1) // 将RecyclerView
                //定位到最后一行
                inputText.setText("") // 清空输入框中的内容
            }
        }
    }

    private fun initMsg() {
        val msg1 = Msg("Hello guy.", Msg.TYPE_RECEIVED)
        msgList.add(msg1)
        val msg2 = Msg("Hello. Who is that?", Msg.TYPE_SENT)
        msgList.add(msg2)
        val msg3 = Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED)
        msgList.add(msg3)
    }
}