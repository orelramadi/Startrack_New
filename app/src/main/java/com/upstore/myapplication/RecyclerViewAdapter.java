package com.upstore.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyHolder> {

    private Context mContext;
    private List<Businesses> mData;

    public  RecyclerViewAdapter(Context mContext, List<Businesses> mData ){
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_business, viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {

        myHolder.business_title.setText(mData.get(i).getBusiness_name());

        myHolder.img_business_thumbnail.setImageResource(mData.get(i).getThumbnail());

        myHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BusinessActivity.class);

                intent.putExtra("Logo",mData.get(i).getThumbnail());
                intent.putExtra("Name",mData.get(i).getBusiness_name());
                intent.putExtra("Description",mData.get(i).getBusiness_description());
                intent.putExtra("Phone",mData.get(i).getBusiness_phone());
                intent.putExtra("Cost_Star",mData.get(i).getBusiness_cost_star());
                intent.putExtra("Cost_Paypal",mData.get(i).getBusiness_cost_paypal());

                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        TextView business_title;
        CardView cardView;
        ImageView img_business_thumbnail;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            business_title = (TextView)itemView.findViewById(R.id.business_id);
            img_business_thumbnail = (ImageView)itemView.findViewById(R.id.logo);
            cardView = (CardView)itemView.findViewById(R.id.cardview);
        }
    }
}
