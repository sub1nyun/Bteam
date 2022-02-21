package com.example.project04_lastproject.employees;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project04_lastproject.R;
import com.example.project04_lastproject.customer.CustomerVO;


import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>{

    List<EmployeeVO> list;
    LayoutInflater inflater;
    Context context;

    public EmployeeAdapter(List<EmployeeVO> list, LayoutInflater inflater, Context context) {
        this.list = list;
        this.inflater = inflater;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itmeView = inflater.inflate(R.layout.emp_item,parent,false);
        return new ViewHolder(itmeView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.binding(holder, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView empid, empname, depart, city, country;
        LinearLayout emp_lnlayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            empid =itemView.findViewById(R.id.empid);
            empname =itemView.findViewById(R.id.empname);
            depart =itemView.findViewById(R.id.depart);
            city =itemView.findViewById(R.id.city);
            country =itemView.findViewById(R.id.country);
            emp_lnlayout= itemView.findViewById(R.id.emp_lnlayout);

        }
        public void binding (ViewHolder holder, int position) {
            holder.empname.setText(list.get(position).getName());
            holder.empid.setText(list.get(position).getEmployee_id()+"");
            holder.depart.setText(list.get(position).getDepartment_name());
            holder.city.setText(list.get(position).getCity());
            holder.country.setText(list.get(position).getCountry_name());

            holder.emp_lnlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EmployeeDialog dialog = new EmployeeDialog(context);
                    dialog.show();
                }
            });
        }

    }

}
