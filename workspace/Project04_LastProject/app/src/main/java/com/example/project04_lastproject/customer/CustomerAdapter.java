package com.example.project04_lastproject.customer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project04_lastproject.R;
import com.example.project04_lastproject.common.AskTask;
import com.example.project04_lastproject.common.CommonMethod;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {

    LayoutInflater inflater;
    Context context;
    List<CustomerVO> list = new ArrayList<>();
    CustomerMainFragment customerMainFragment;
    private String TAG = "Adapter";


    public CustomerAdapter(LayoutInflater inflater, Context context, List<CustomerVO> list, CustomerMainFragment customerMainFragment) {
        this.inflater = inflater;
        this.context = context;
        this.list = list;
        this.customerMainFragment = customerMainFragment;
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
            //바인딩이 되고나서 처리는 ▲ onBindViewHolder 이벤트를 활용해서 처리함
            holder.btn_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CustomerActivity.class);
                    CustomerVO vo = new CustomerVO();
                    //list.get(i) <= vo로 빼올 수 있음
                    //ArrayList<String> strs = strs.get(i) == 문자열 또는 NULL
                    //ArrayList<VO> list = list.get(i) == VO 또는 NULL

                    //List<CustomerVO> list
                    intent.putExtra("vo", list.get(i));
                    Log.d(TAG, "onClick: "+list.get(i).getName());
                    //액티비티를 시작하는것도 context
                    context.startActivity(intent);
                }
            });
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CustomerActivity.class);
                CustomerVO vo = new CustomerVO();
                intent.putExtra("vo", list.get(i));
                //intent.putExtra("page", "edit");
                intent.putExtra("enable", true);
                context.startActivity(intent);
//                customerMainFragment.refresh(inflater);
            }
        });

        holder.btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AlertDialog <= 만드는 식을 외우지 말것 (의미 x)
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("고객 정보 삭제").setMessage("정말로 삭제하실건가요?")
                .setIcon(android.R.drawable.ic_dialog_alert); //지정된 아이콘 존재
                //Y or n
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //구현부 삭제 처리
                        //AsnykTask 이용해서 삭제 처리 해보기
                        Toast.makeText(context, "삭제 처리 진행", Toast.LENGTH_SHORT).show();
                        AskTask task = new AskTask("delete.cu");
                       task.addParam("id", list.get(i).getId()+"");
                        CommonMethod.exuteGet(task);
                        customerMainFragment.refresh(inflater);

                    }
                });//PositiveButton

                builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "삭제 처리 하면 안 됨!", Toast.LENGTH_SHORT).show();
                    }
                });
                //빌더까지만 생김
                AlertDialog alertDialog = builder.create(); //builder.create(); -> return AlertDialog
                alertDialog.show();
            }
        });
        }


    }
}
