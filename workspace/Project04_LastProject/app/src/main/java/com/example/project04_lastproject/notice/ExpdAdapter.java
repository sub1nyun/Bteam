package com.example.project04_lastproject.notice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.project04_lastproject.R;

import java.util.ArrayList;

public class ExpdAdapter extends BaseExpandableListAdapter {



    //기존 리사이클러뷰, 리스트뷰, 그리드뷰 등 기본 베이스 어댑터 또는 리사이클러뷰 어댑터 x2 있다고 생각

    ArrayList<GroupDTO> list;
    LayoutInflater inflater;

    public ExpdAdapter(ArrayList<GroupDTO> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;
    }

    @Override   //부모(그룹)의 갯수를 지정
    public int getGroupCount() {
        return list.size();
    }

    //그룹마다 자식의 갯수가 달라져야함
    //서브리스트의 사이즈
    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getSubList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        //ArrayList(0번째DTO).Sublist(0).get -> SUBDTO를 리턴
        return list.get(groupPosition).getSubList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override //getView <= 아이템 붙이는 처리를 했음 inflarter -> xml 필요함 부모, 자식
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder viewHolder;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.group_item, parent,false);
            viewHolder = new GroupViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (GroupViewHolder) convertView.getTag();
        }
        viewHolder.bind(viewHolder, groupPosition);
        //convertView <= itemView를 만들고나서 여기에 Tag속성을 이용해서 Class를 저장 시켜둠
        //이미 view가 만들어진 상태라면 다시 ViewHolder를 만드는게 아니라 만들어진것을 재활용할수있게 처리함



        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        SubViewHolder viewHolder;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.child_item, parent, false);
            viewHolder = new SubViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (SubViewHolder) convertView.getTag();
        }
        viewHolder.bind(viewHolder,groupPosition,childPosition);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public class GroupViewHolder {
        //위젯을 찾아야함
        TextView tv_title, tv_content;

        public GroupViewHolder(View itemView) {
            this.tv_title = itemView.findViewById(R.id.grp_tvtitle);
            this.tv_content = itemView.findViewById(R.id.grp_tvcontent);
        }
        public void bind(ExpdAdapter.GroupViewHolder holder, int i) {
            holder.tv_content.setText(list.get(i).getContent());
            holder.tv_title.setText(list.get(i).getTitle());
        }
    }

    public class SubViewHolder {
        //위젯을 찾아야함
        TextView tv_title, tv_content;

        public SubViewHolder(View itemView) {
            this.tv_title = itemView.findViewById(R.id.sub_tvtitle);
            this.tv_content = itemView.findViewById(R.id.sub_tvcontent);
        }
        public void bind(ExpdAdapter.SubViewHolder holder, int i, int j) {
            holder.tv_content.setText(list.get(i).getSubList().get(j).getContent());
            holder.tv_title.setText(list.get(i).getSubList().get(j).getTitle());
        }
    }
}


