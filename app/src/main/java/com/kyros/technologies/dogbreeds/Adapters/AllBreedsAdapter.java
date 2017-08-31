package com.kyros.technologies.dogbreeds.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kyros.technologies.dogbreeds.Activity.BreedPictureActivity;
import com.kyros.technologies.dogbreeds.R;

import java.util.List;

/**
 * Created by kyros on 23-08-2017.
 */

public class AllBreedsAdapter extends RecyclerView.Adapter<AllBreedsAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> breed_list ;

    public AllBreedsAdapter (Context mContext,List<String> breed_list){
        this.mContext=mContext;
        this.breed_list=breed_list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_allbreeds,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.card_breed_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, BreedPictureActivity.class);
                intent.putExtra("breed",breed_list.get(position));
                mContext.startActivity(intent);
                Toast.makeText(mContext.getApplicationContext(),"Item Clicked : "+position,Toast.LENGTH_SHORT).show();
            }
        });
        holder.text_breedname.setText(breed_list.get(position));
    }

    @Override
    public int getItemCount() {
        if(breed_list==null){
            return 0;
        }
        return breed_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public CardView card_breed_item;
        public TextView text_breedname;
        public MyViewHolder(View itemView) {
            super(itemView);
            card_breed_item=(CardView)itemView.findViewById(R.id.card_breed_item);
            text_breedname=(TextView)itemView.findViewById(R.id.text_breedname);


        }
    }
}
