package com.upstore.myapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.upstore.myapplication.BusinessActivity;
import com.upstore.myapplication.Businesses;
import com.upstore.myapplication.R;

import java.util.ArrayList;

public class MainBusinessesAdapter extends RecyclerView.Adapter<MainBusinessesAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Businesses> businessesList;
    private DatabaseReference myRef;



    public MainBusinessesAdapter(Context mContext, ArrayList<Businesses> businessesList) {
        this.mContext = mContext;
        this.businessesList = businessesList;
    }

    @NonNull
    @Override
    public MainBusinessesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_business, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Glide.with(mContext).load(businessesList.get(position).getBanner()).into(holder.imageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BusinessActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return businessesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public ViewHolder (@NonNull final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.banner);
        }
    }
}