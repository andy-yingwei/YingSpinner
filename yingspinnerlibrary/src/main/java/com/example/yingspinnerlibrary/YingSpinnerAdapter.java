package com.example.yingspinnerlibrary;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class YingSpinnerAdapter extends RecyclerView.Adapter<YingSpinnerAdapter.ViewHolder> {

    private int textSize;

    private ArrayList<String> itemList;
    private ViewHolder.OnItemClickListener myOnItemClickListener = null;

    public YingSpinnerAdapter(ArrayList<String> itemList){
        this.itemList=itemList;
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        TextView myTextView;

        public ViewHolder(View view){
            super(view);
            myTextView=view.findViewById(R.id.item_TextView);

        }
        public interface OnItemClickListener {
            void onItemClick(View view, TextView textView,int position);
        }
    }

    public void setOnItemClickLitener(ViewHolder.OnItemClickListener mOnItemClickListener) {
        this.myOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(final  ViewHolder viewHolder, final int position) {
        viewHolder.myTextView.setText(itemList.get(position));
        if(textSize!=0){
            viewHolder.myTextView.setTextSize(textSize);
        }else
            viewHolder.myTextView.setTextSize(15);

        if (myOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myOnItemClickListener.onItemClick(viewHolder.itemView, viewHolder.myTextView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setTextSize(int textSize){
        this.textSize=textSize;
    }
}