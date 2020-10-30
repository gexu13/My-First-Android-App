package com.gexu.dayscountdown;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>{
    List<DateAndEvent> myList = new ArrayList<>();

    public void setDateAndEvent(List<DateAndEvent> myList){
        this.myList = myList;
    }
    public List<DateAndEvent> getDateAndEvent(){
        return this.myList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_card , null ,false);
        return new MyViewHolder(itemView);
    }
    //关联
    //对数据库进行操作
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DateAndEvent dateAndEvent = myList.get(position);
        holder.textViewEvent.setText(dateAndEvent.getEvent());
        holder.textViewDays.setText(String.valueOf(dateAndEvent.getDays()));
        holder.textViewUntilOrSince.setText(dateAndEvent.getSinceOrUntil());
        holder.textViewDate.setText(dateAndEvent.getDate());
        holder.textViewStartOrTarget.setText(dateAndEvent.getStartDateOrTargetDate());
        //转跳网站
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }


    //设置ViewHolder管理recyclerView的内容
    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewDays , textViewUntilOrSince , textViewEvent, textViewDate , textViewStartOrTarget;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUntilOrSince = itemView.findViewById(R.id.textViewUntilOrSince);
            textViewDays = itemView.findViewById(R.id.textViewDays);
            textViewEvent = itemView.findViewById(R.id.textViewEvent);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewStartOrTarget = itemView.findViewById(R.id.textViewStartDateOrTargetDay);


        }
    }


}
