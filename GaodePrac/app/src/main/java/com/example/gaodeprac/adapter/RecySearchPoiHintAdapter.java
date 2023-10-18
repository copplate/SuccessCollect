package com.example.gaodeprac.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.AmapPageType;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.help.Tip;
import com.example.gaodeprac.R;

import java.util.List;

public class RecySearchPoiHintAdapter extends RecyclerView.Adapter<RecySearchPoiHintAdapter.RecySearchHintViewHolder>{
    private List<Tip> tips;
    private Context context;
    private OnSearchPoiHintClickListener onSearchPoiHintClickListener;

    public RecySearchPoiHintAdapter(List<Tip> tips, Context context) {
        this.tips = tips;
        this.context = context;
    }

    @NonNull
    @Override
    public RecySearchHintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_poi_hint, parent, false);
        RecySearchPoiHintAdapter.RecySearchHintViewHolder holder = new RecySearchPoiHintAdapter.RecySearchHintViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecySearchHintViewHolder holder, int position) {
        holder.tvSearchPoiHintName.setText(tips.get(position).getName());
        holder.llSearchPoiHint.setOnClickListener(v -> {
            Tip tip = tips.get(position);
            LatLonPoint latLonPoint = tip.getPoint();
            Poi poi = new Poi(tip.getName(), new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude()), tip.getPoiID());
            onSearchPoiHintClickListener.click(poi,latLonPoint);
        });
    }

    @Override
    public int getItemCount() {
        return tips == null ? 0 : tips.size();
    }

    /*public void setData(List<Tip> tips) {//可能是java的对象问题吧，导致这个适配器感受不到列表变化
        if (tips == null) {
            return;
        }
//        this.tips.clear();
        this.tips.addAll(tips);
        this.notifyDataSetChanged();
    }*/

    /*public void setTips(List<Tip> tips) {
        this.tips = tips;
    }*/

    class RecySearchHintViewHolder extends RecyclerView.ViewHolder{
        TextView tvSearchPoiHintName;
        LinearLayout llSearchPoiHint;
        public RecySearchHintViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSearchPoiHintName = itemView.findViewById(R.id.tv_search_poi_hint_name);
            llSearchPoiHint = itemView.findViewById(R.id.ll_search_poi_hint);
        }
    }

    public void setOnSearchPoiHintClickListener(OnSearchPoiHintClickListener onSearchPoiHintClickListener) {
        this.onSearchPoiHintClickListener = onSearchPoiHintClickListener;
    }

    public interface OnSearchPoiHintClickListener{
        void click(Poi poi,LatLonPoint latLonPoint);
    }

}
