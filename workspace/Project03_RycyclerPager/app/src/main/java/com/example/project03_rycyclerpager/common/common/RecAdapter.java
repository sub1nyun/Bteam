package com.example.project03_rycyclerpager.common.common;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project03_rycyclerpager.common.R;

import java.util.ArrayList;

//2. 일반 클래스 => 리사이클러뷰 어댑터를 상속받은 객체로 만듬
public class RecAdapter extends  RecyclerView.Adapter<RecAdapter.ViewHolder>{

    // 필드 부분은 크게 두가지가 필요함 ( 절대적필요 -> LayoutInflater) Layout파일을 뷰(목록) 한칸마다 붙일수있는
    // 기능을 가진 객체
    //몇개의 아이템을 보여줄지를 결정할 Collection, Index, Length, size
    //구조를 가진 데이터 타입. (ArrayList, List. LinkedList, Vector, 배열, 등등)

    //LayoutInflater <- 어떻게 하면 RecAdapter에서 사용하게 만들 수 있을까
    ArrayList<RecDTO> list;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //뷰홀더 강제 - > inner class
    //1번으로 생각 LayoutInflater 과정을 통해서 붙여놓은 ItemView(ViewGroup)을 통해 위젯의 아이디를
    //찾아서 담아놓을 수 있는 클래스를 미리 만든다 (RecyclerView에서는 강제함)
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView rec_imgv;
        TextView rec_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rec_imgv = itemView.findViewById(R.id.rec_imgv);
            rec_tv = itemView.findViewById(R.id.rec_tv);

        }
    }
}
