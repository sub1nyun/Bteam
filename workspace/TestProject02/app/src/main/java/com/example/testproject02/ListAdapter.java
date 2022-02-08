package com.example.testproject02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter{
    ArrayList<ListDTO> list;
    Context context;
    LayoutInflater inflater;

    public ListAdapter(ArrayList<ListDTO> list, Context context) {
        this.list = list;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
    }

    //갯수
    @Override
    public int getCount() {
        return list.size();
    }
    //어떤 List의 값인지
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    ListViewHolder viewHolder;
    if(convertView == null) {
        convertView = inflater.inflate(R.layout.list_item, parent, false);
        viewHolder = new ListViewHolder();
        viewHolder.imgView1 = convertView.findViewById(R.id.list_img1);
        viewHolder.imgView2 = convertView.findViewById(R.id.list_img2);
        viewHolder.tv1 = convertView.findViewById(R.id.list_tv1);
        viewHolder.tv2 = convertView.findViewById(R.id.list_tv2);
        convertView.setTag(viewHolder);
    }else{
        viewHolder = (ListViewHolder) convertView.getTag();
    }
    viewHolder.imgView1.setImageResource(list.get(position).getImg1());
    viewHolder.imgView2.setImageResource(list.get(position).getImg2());
    viewHolder.tv1.setText(list.get(position).getTv1());
    viewHolder.tv2.setText(list.get(position).getTv2());
        return convertView;
    }

    public class ListViewHolder{
        public ImageView imgView1, imgView2;
        public TextView tv1, tv2;
    }
}
