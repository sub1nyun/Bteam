package com.example.project03_rycyclerpager.viewpagerdb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project03_rycyclerpager.R;

import java.util.ArrayList;

public class PagerDbAdapter extends RecyclerView.Adapter<PagerDbAdapter.ViewHolder> {
    ArrayList<Integer> imgs;
    LayoutInflater inflater;

    public PagerDbAdapter(ArrayList<Integer> imgs, LayoutInflater inflater) {
        this.imgs = imgs;
        this.inflater = inflater;
    }

    //화면 붙일때 필요한
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.pager_item, parent, false);
        //ViewHolder ViewHolder = new ViewHolder(itemView);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pager_imgv.setImageResource(imgs.get(position));
    }

    @Override
    public int getItemCount() {
        return imgs.size();
    }

    //Inner Class를 먼저 생성 Recycler 상속을 받아야함
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pager_imgv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pager_imgv = itemView.findViewById(R.id.pager_imgv);
        }
    }
}
