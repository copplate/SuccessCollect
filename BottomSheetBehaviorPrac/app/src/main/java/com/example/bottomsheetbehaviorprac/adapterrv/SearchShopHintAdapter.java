package com.example.bottomsheetbehaviorprac.adapterrv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bottomsheetbehaviorprac.R;

import java.util.List;

public class SearchShopHintAdapter extends RecyclerView.Adapter<SearchShopHintAdapter.RecySearchHintViewHolder>{
    private List<String> tips;
    private Context context;
    private OnSearchPoiHintClickListener onSearchPoiHintClickListener;


    public SearchShopHintAdapter(List<String> tips, Context context) {
        this.tips = tips;
        this.context = context;
    }

    @NonNull
    @Override
    public RecySearchHintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_shop_loc_hint, parent, false);
        RecySearchHintViewHolder holder = new RecySearchHintViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecySearchHintViewHolder holder, int position) {
        holder.tvSearchPoiHintName.setText(tips.get(position));
        holder.llSearchPoiHint.setOnClickListener(v -> {
//            Tip tip = tips.get(position);
            //LatLonPoint latLonPoint = tip.getPoint();
            //Poi poi = new Poi(tip.getName(), new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude()), tip.getPoiID());
//            onSearchPoiHintClickListener.click(tip.getName());
        });
    }

    @Override
    public int getItemCount() {
        return tips == null ? 0: tips.size();
    }

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
        void click(String tipName);
    }
}
