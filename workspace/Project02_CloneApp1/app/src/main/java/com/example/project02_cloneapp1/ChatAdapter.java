package com.example.project02_cloneapp1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChatAdapter extends BaseAdapter {
    //그냥 클래스는 어뎁터로 사용불가 - 상속받아야함
    //ArrayList가 있어야함 <- xml로 만들어놓은 아이템을 몇개 붙일건지
    ArrayList<KaKaoDTO> list; //리스트 선언
    LayoutInflater inflater; //화면 붙일때 필요

    //LayoutInflater 를 만들어서 보냄
    //생성할때 필요한 것 ▼
    public ChatAdapter(ArrayList<KaKaoDTO> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;
    }

    //메소드가 어떤 역할을 했는지 아는 게 중요
    //아이템 갯수가 몇개인지 결정함 ▼
    @Override
    public int getCount() {
        return list.size();
    }
    //아이템의 내용을 받을때 객체타입이라 문제 없음
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    //고유한 값 position == index
    @Override
    public long getItemId(int position) {
        return position;
    }
    //실제 화면을 만드는 부분으로 가장중요함
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ChatViewHolder viewHolder;
        //초기상태 확인
        if(convertView == null) {//한 칸을 말함 ▼
            convertView = inflater.inflate(R.layout.list_chat_item, parent, false);
            viewHolder = new ChatViewHolder(); //viewHolder도 null 이기때문에 초기하ㅗ
            viewHolder.imgv = convertView.findViewById(R.id.list_imgv);
            viewHolder.tv1 = convertView.findViewById(R.id.list_tv1);
            viewHolder.tv2 = convertView.findViewById(R.id.list_tv2);
            viewHolder.tv3 = convertView.findViewById(R.id.list_tv3);
            //null이 아니라면 재상요할 수 있게
            convertView.setTag(viewHolder);
        }else {
            //null이 아니면 가지고 올 수 있음 get으로
            viewHolder = (ChatViewHolder) convertView.getTag();
        }
        //연결
        viewHolder.imgv.setImageResource(list.get(i).getImageId());
        viewHolder.tv1.setText(list.get(i).getName());
        viewHolder.tv2.setText(list.get(i).getMsg());
        viewHolder.tv3.setText(list.get(i).getDate());


        return convertView; //만들어진 converView 그대로 리턴
    }


    //화면 만들때 ViewHolder필요 innerClass로
    public class  ChatViewHolder{
        public ImageView imgv;
        TextView tv1, tv2, tv3; //tv1=이름, tv2=메세지, tv=3시간
    }
}
