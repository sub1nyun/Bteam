package com.example.project02_cloneapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatAdapter extends BaseAdapter {
    // ArrayList <- xml로 만들어놓은 아이템을 몇개정도 붙일건지
    ArrayList<KakaoDTO> list;
    LayoutInflater inflater;

    public ChatAdapter(ArrayList<KakaoDTO> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;
    }

    @Override
    public int getCount() { // 몇개인지 인식하는 메소드
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ChatViewHolder viewHolder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_chat_item, parent, false);
            viewHolder = new ChatViewHolder();
            viewHolder.imv = convertView.findViewById(R.id.iv_profile);
            viewHolder.tv1 = convertView.findViewById(R.id.tv_name);
            viewHolder.tv2 = convertView.findViewById(R.id.tv_msg_state);
            viewHolder.tv3 = convertView.findViewById(R.id.tv_date);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChatViewHolder) convertView.getTag();
        }

        viewHolder.imv.setImageResource(list.get(i).getImgId());
        viewHolder.tv1.setText(list.get(i).getName());
        viewHolder.tv2.setText(list.get(i).getMsg());
        viewHolder.tv3.setText(list.get(i).getDate());

        return convertView;

    }

    public class ChatViewHolder {
        public ImageView imv;
        TextView tv1, tv2, tv3;; // tv1: 이름, tv2: 메시지, tv3: 시간
    }
}
