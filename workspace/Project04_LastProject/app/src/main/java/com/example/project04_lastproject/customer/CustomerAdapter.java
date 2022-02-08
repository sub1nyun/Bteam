package com.example.project04_lastproject.customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project04_lastproject.R;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {

    LayoutInflater inflater;
    Context context;
    List<CustomerVO> list = new ArrayList<>();

    public CustomerAdapter(LayoutInflater inflater, Context context, List<CustomerVO> list) {
        this.inflater = inflater;
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater <- 칸마다 보여줄 데이터를 붙이기 위해서 (customer_main_item.xml)
        //뷰홀더가 필요함(리턴)
        View itemView = inflater.inflate(R.layout.customer_main_item, parent, false);
        //생성자 만들때 넘겨줄것 만듬 ▲
        //생성자 넘겨줌 ▼
        return new ViewHolder(itemView);
    }

    //xml이 연결되고 나서 bind 데이터 연결 부
    //이벤트  처리, 부모의 몇 번째 칸에 붙어있는지
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.bind(holder,position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
            TextView cus_no, cus_name, cus_phone;
            ImageView cus_img;
            Button btn_detail, btn_edit, btn_del;
        public ViewHolder(@NonNull View itemView) { //View 타입을 입력 받는 생성자가 존재함
            super(itemView);
            cus_no = itemView.findViewById(R.id.cus_no);
            cus_name = itemView.findViewById(R.id.cus_name);
            cus_phone = itemView.findViewById(R.id.cus_phone);
            cus_img = itemView.findViewById(R.id.cus_img);
            btn_detail = itemView.findViewById(R.id.btn_detail);
            btn_edit = itemView.findViewById(R.id.btn_edit);
            btn_del = itemView.findViewById(R.id.btn_del);
        }
        public void bind(ViewHolder holder, int i) {
            holder.cus_no.setText(list.get(i).getNo()+"");
            holder.cus_name.setText(list.get(i).getName()+"");
            holder.cus_phone.setText(list.get(i).getPhone()+"");
        }
    }
}
