package com.example.project02_cloneapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    // ImageView 1, TextView 2 <- DTO
    // R.id.? = ?, ?
    // KakaoDTO 구현하기.
    // ArrayAdapter (context, xml, items(ArrayList));
    // 어댑터를 구현하기.!
    // 1. extends로 원하는 형태의 어댑터를 상속받기. (종류가 다양함) BaseAdapter
    // 2. 원하는 형태의 데이터를 묶어놓을 수 있는 DTO를 만들기.
    // 3. 생성자를 이용해서 데이터를 묶어놓은 ArrayList또는 컬렉션구조를 가진
    // (즉, 데이터의 갯수가 정해진 == 리스트에 보여줄 목록의 갯수) 필드를 만든다.
    ArrayList<KakaoDTO> list;
    Context context;
    LayoutInflater inflater;

    public GridAdapter(ArrayList<KakaoDTO> list, Context context) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // Adapter를 사용하는 클래스에서 LayoutInflater자체를 만들어서 가져오기
    //public KakaoAdapter(ArrayList<KakaoDTO> list, LayoutInflater inflater) {
    //    this.list = list;
    //    this.inflater = inflater;
    //}

    //ArrayList<KakaoDTO> <= DTO를 여러개 집어넣음. (size)
    // 몇 개의 데이터가 목록에 (List) 나올건지를 정함.

    @Override
    public int getCount() {
        return list.size();
    }

    // Object 최상위 객체 개념
    // 선택한 아이템이 어떤 ArrayList의 아이템인지 받을 수 있는 메소드.
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    // id를 따로 넣었을 때 고유하게 식별할 수 있는 값.
    @Override
    public long getItemId(int position) {
        return position;
    }

    // * getView화면에 붙일 레이아웃을 실제로 붙이고,
    // (ListView에 들어가는 칸마다 들어갈 xml을 붙이는 처리)
    // Inflater.inflate 시키는 처리.
    // ViewHolder <- 여러개의 위젯이 묶인 ViewGroup을 따로 관리할 수 있는 클래스.
    // (우리가 만들어놓은 list_item.xml에 있는 위젯들을 따로 모아놓은 클래스)
    // RecyclerView <- ListView의 확장판 ( ViewHolder를 강제함 )
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convertView == viewHolder ( 디자인 처리가 최초인지 아니면 최초가 아닌지 )
        KakaoViewHolder viewHolder;

        // 캐시된 뷰가 없을 경우 새로 뷰홀더를 만들고 (생성)
        // 있으면 이미 만들어놓은 뷰홀더를 재활용한다. (Recycler)
        if(convertView == null) {
            // 최초로 디자인을 연결하는 과정(뷰홀더를 만드는 과정)
            // list_item ( 미리 지정해놓은 칸마다 들어갈 xml을 convertView 칸에 붙임 )

            convertView = inflater.inflate(R.layout.grid_item, parent, false);
            viewHolder = new KakaoViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.imv_goods);
            viewHolder.tv1 = convertView.findViewById(R.id.tv_content);
            viewHolder.tv2 = convertView.findViewById(R.id.tv_price);
            //* 재활용을 위한 처리, Tag(꼬리표)
            convertView.setTag(viewHolder); //
        } else {
            viewHolder = (KakaoViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setImageResource(list.get(position).getImgId());
        viewHolder.tv1.setText(list.get(position).getName());
        viewHolder.tv2.setText(list.get(position).getMsg());

        return convertView;
    }

    // 4. Inner 클래스로 클래스 내부에 ViewHolder(위젯 묶음) 클래스를 만든다.
    public class KakaoViewHolder{
        public ImageView imageView;
        public TextView tv1, tv2;
    }

}
