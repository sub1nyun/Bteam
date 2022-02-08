package com.example.project02_cloneapp.recyclerview;

//Recycler Adapter (마스터) ==> ViewPager2 (똑같은 어댑터를 사용하는 목록뷰를 사용가능)
//RecyclerView의 특성 (list,grid) ViewHolder 강제한다. (미리 만들어놔야만 어댑터를 상속받을 수 있다)
//viewHolder (개발자가 만들어놓은 위젯들을 묶어놓은 클래스 ) == 필드의 갯수는 xml의 위젯수와 같음(대부분)

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project02_cloneapp.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder>{
//VH (뷰홀더)가 있는 것으로 받고 <>안에 만들어놓은 어뎁터와 뷰홀더 넣으면 됨
    //안에서 쓸거
    LayoutInflater inflater; //레이아웃 규칙
    ArrayList<RecDTO> list;
    Context context; //지금은 null 상태 null이 아니게 null.~ 하면 오류

//    public RecAdapter(LayoutInflater inflater) {
//        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }

    //list == null, inflater == null 선언만 해서 null
    //사용할 곳에서 보내줘야함 Fragment
    public RecAdapter(ArrayList<RecDTO> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;
    }
    //뷰홀더부터 작업

    //xml을 인플레이트 시키는 처리 (listview) tag를 통해서 ViewHolder를 담아두는 행위 등
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.rec_item, parent, false);
        //항상 부모
        //여기서 디자인 붙혀서 넘겨줌
        return new ViewHolder(itemView); //칸마다 만드는 처리를 여기서함
    }

    //on 처리가 끝나고 나서 ViewHolder가 null이 아닐때 이벤트 처리를 하기위해 만들어주는 메소드
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    //void 형태
//        holder.profile_image.setImageResource(list.get(position).getResId());
//        holder.tv_str.setText(list.get(position).getTextStr());
        //bind 메소드로 이동해서 함
        holder.bind(holder, position);
        //holder.bind(null, p~); null 넣으면 바로 오류 발생함
    }

    @Override
    public int getItemCount() {
        return list.size();
        // ▲ 만 하면 끝남 무조건 바꿔조야함
    }

    //1.ViewHolder를 만든다(무조건) Inner Class 어뎁터 안 에서
    public class ViewHolder extends RecyclerView.ViewHolder {

        //커스터 마이징 할 수 있게 ▼
        ImageView profile_image;
        TextView tv_str;
        //ViewHolder에 있는 변수(객체)와 디자인에 있는 위젯 연결부 (getView)
        //메소드 상속 받아야함 ▼
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_image = itemView.findViewById(R.id.profile_image);
            tv_str = itemView.findViewById(R.id.tv_str);
        }
        //Nulllness Annotations
        //@NonNull <= null을 허용하지 않는 경우 (변수에 Null이 들어오면 Critical한 오류가 발생할 수 있음)
        //@Nullable <= null을 허용하는 경우 (Null이 들어와도 알아서 처리를 하거나 오류가 적은 경우)
        //holder에서 작업을하는데 holder가 null이면 종료됨
        public void bind(@NonNull ViewHolder holder, int position) {
            holder.profile_image.setImageResource(list.get(position).getResId());
            holder.tv_str.setText(list.get(position).getTextStr());
        }
    }
}
