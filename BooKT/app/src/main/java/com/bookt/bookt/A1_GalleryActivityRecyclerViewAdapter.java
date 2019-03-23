package com.bookt.bookt;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class A1_GalleryActivityRecyclerViewAdapter extends RecyclerView.Adapter<A1_GalleryActivityRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<A1_GalleryActivityCard> list;
    private int lastPosition = -1;

    public A1_GalleryActivityRecyclerViewAdapter(Context context, ArrayList<A1_GalleryActivityCard> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public A1_GalleryActivityRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.a1_gallery_card, parent, false);

        return new A1_GalleryActivityRecyclerViewAdapter.MyViewHolder(view);

    }

    public void updateList(ArrayList<A1_GalleryActivityCard> list){
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull final A1_GalleryActivityRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.cuisineImage.setImageResource(R.drawable.test_cat);
        holder.cuisineText.setText(list.get(position).getType());

        holder.cuisineImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,
                        A2_RestaurantsActivity.class).putExtra("restaurant_name", holder.cuisineText.getText()));
            }
        });

        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // Set views from Resource folder to their corresponding IDs
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView cuisineText;
        ImageView cuisineImage;
        ConstraintLayout constraintLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            cuisineText = itemView.findViewById(R.id.cuisineText);
            cuisineImage = itemView.findViewById(R.id.cuisineImage);
            constraintLayout = itemView.findViewById(R.id.constraint);
        }
    }
    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
            animation.setInterpolator(new DecelerateInterpolator());
            animation.setStartOffset(position * 100);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
