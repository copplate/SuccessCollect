package com.example.okpandnettychat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.okpandnettychat.R;
import com.example.okpandnettychat.bean.Msg;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter{
    private List<Msg> msgList;
    private Context context;

    public MessageAdapter(List<Msg> msgList, Context context) {
        this.msgList = msgList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == Msg.TYPE_RECEIVED) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_msg_left, parent, false);
            LeftViewHolder leftViewHolder = new LeftViewHolder(view);
            return leftViewHolder;
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_msg_right, parent, false);
        RightViewHolder rightViewHolder = new RightViewHolder(view);
        return rightViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        /*int tempItemCount = msgList == null ? 0: msgList.size();
        if (position > tempItemCount - 1) {
            return;
        }*/
        if (holder instanceof LeftViewHolder) {
            ((LeftViewHolder) holder).tvLeftMsg.setText(msgList.get(position).getContent());
        } else if (holder instanceof RightViewHolder) {
            ((RightViewHolder) holder).tvRightMsg.setText(msgList.get(position).getContent());
        }




    }

    @Override
    public int getItemCount() {
        return msgList == null ? 0 : msgList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Msg msg = msgList.get(position);
        return msg.getType();
    }

    class RightViewHolder extends RecyclerView.ViewHolder{
        private TextView tvRightMsg;
        public RightViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRightMsg = itemView.findViewById(R.id.tv_right_msg);
        }
    }

    class LeftViewHolder extends RecyclerView.ViewHolder{
        private TextView tvLeftMsg;
        public LeftViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLeftMsg = itemView.findViewById(R.id.tv_left_msg);
        }
    }
}
