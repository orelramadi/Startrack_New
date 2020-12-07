package com.upstore.myapplication;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter {

    List<Services> servicesList;
    Context context;


    public ServiceAdapter(List<Services> servicesList) {
        this.servicesList=servicesList;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_business,parent,false);
        ViewHolderClass viewHolderClass= new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolderClass viewHolderClass=(ViewHolderClass)holder;
        final Services services=servicesList.get(position);
        Glide.with(((ViewHolderClass) holder).imageView.getContext()).load(servicesList.get(position).getServices()).into(viewHolderClass.imageView);
        viewHolderClass.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),BusinessActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("key",services);
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }


    public static class ViewHolderClass extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_card_view_service);

        }
    }
}