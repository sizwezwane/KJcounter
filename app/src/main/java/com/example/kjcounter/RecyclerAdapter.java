package com.example.kjcounter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.kjcounter.MainActivity.ll;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    protected static ArrayList<String> list;
    TextView textView=null;
    static String ent;
    static int currentPosition;


    public RecyclerAdapter(ArrayList<String> list){
        this.list=ll;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        final int lkj=i;
        textView=(TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_view_layout,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPosition=lkj;
                Intent intent=new Intent(textView.getContext(),tapEntry.class);
                textView.getContext().startActivity(intent);
            }
        });

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        currentPosition=i;
        viewHolder.VersionName.setText(list.get(i));
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public static void stringNext(){
        if(list.size()-1==currentPosition){
            currentPosition=0;
        }
        else {
            currentPosition=currentPosition+1;
        }
        ent=list.get(currentPosition);
    }
    public static void stringPrevious(){
        if(currentPosition==0){
            currentPosition=list.size()-1;
        }
        else {
            currentPosition=currentPosition-1;
        }
        ent=list.get(currentPosition);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView VersionName;

        public MyViewHolder(TextView itemView) {
            super(itemView);
            VersionName=itemView;
        }

    }
}
