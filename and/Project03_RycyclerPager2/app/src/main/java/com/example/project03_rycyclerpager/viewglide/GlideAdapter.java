package com.example.project03_rycyclerpager.viewglide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project03_rycyclerpager.R;
import com.example.project03_rycyclerpager.recycler.RecDTO;

import java.util.ArrayList;

//2.↓ 일반 클래스 => 리사이클러뷰 어댑터를 상속받은 객체로 만듬.
public class GlideAdapter extends RecyclerView.Adapter<GlideAdapter.ViewHolder> {

    // 필드부분은 두가지가 필요함 ( LayoutInflater ) Layout파일을 뷰(목록) 한칸마다 붙일수있는
    //기능을 가진 객체

    // 몇개의 아이템을 보여줄지를 결정할 Collection , Index , Length , size
    //구조를 가진 데이터 타입. ( ArrayList , List , LinkedList , Vector , 배열 , 등등 )

    //LayoutInflater <- 어떻게 하면 RecAdapter에서 사용하게 만들수있을까.
    //ArrayList<RecDTO> list;
    LayoutInflater layoutInflater;
    //ArrayList<Integer> imglist;
    ArrayList<AndImgDTO> list;
    Context context;

    //생성자로 받아오기

//    public GlideAdapter(LayoutInflater layoutInflater) {
//        this.layoutInflater = layoutInflater;
//    }

    public GlideAdapter(LayoutInflater layoutInflater, ArrayList<AndImgDTO> list, Context context) {
        this.layoutInflater = layoutInflater;
        this.list = list;
        this.context = context;
    }


    //3. 뷰홀더를 만들어내는 과정 (inflater객체를 이용해서 칸마다 아이템을 붙여주는 처리를 함)

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.glide_item, parent, false);
        //ViewHolder v = new ViewHolder(itemView);
        return new ViewHolder(itemView);
    }
    //4. 아이템이 세팅되고나서 (Inflater되고나서의 처리)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.gli_imgv);
//        holder.gli_imgv.setImageResource(imglist.get(position));
    }
    //.5 몇개의 아이템을 가진 RecyclerView인지를 정하는 부분
    @Override
    public int getItemCount() {
        return list.size();
    }

    //1.LayoutInflater과정을 통해서 붙여놓은 ItemView(ViewGroup)를 통해 위젯의 아이디를
    //찾아서 담아놓을수있는 클래스를 미리만든다 ( RecyclerView에서는 강제함 )
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView gli_imgv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gli_imgv = itemView.findViewById(R.id.gli_imgv);
        }
    }

}
