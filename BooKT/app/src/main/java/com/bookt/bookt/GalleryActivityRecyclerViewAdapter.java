package com.bookt.bookt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class GalleryActivityRecyclerViewAdapter extends RecyclerView.Adapter<GalleryActivityRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<GalleryActivityCard> list;
    private int x;

    /*
    RecyclerView Arraylist Positions
    (used onCreate to track RecyclerView index values (even/odd) to do dynamic card assignments)
    */


    public GalleryActivityRecyclerViewAdapter(Context context, ArrayList<GalleryActivityCard> list, int x) {
        this.context = context;
        this.list = list;
        this.x = x;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // If card position is ZERO or multiple of THREE (big card)


        // Inflate the XML associated with FULL SCREEN-WIDTH card
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.gallery_view_card, parent, false);
        return new MyViewHolder(view);


    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.imageViewOne.setImageResource(R.drawable.icon);
        holder.imageViewOne.getLayoutParams().height = (int)(x*0.5);
        holder.imageViewOne.getLayoutParams().width = (int)(x*0.5);
        holder.nameOne.setText(list.get(position).getRestaurantTypeName());


    }

    @Override
    public int getItemViewType(int position) {

        // Save position of card in RecyclerView to use in onCreate and onBindViewHolder
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // Set views from Resource folder to their corresponding IDs
    public class MyViewHolder extends RecyclerView.ViewHolder {

        // imageViewOne and nameOne are the LEFT side image and text of dynamic splits
        // imageViewTwo and nameTwo are the RIGHT side image and text of dynamic splits
        // imageViewThree and nameThree are for the BIG image

        ImageView imageViewOne;


        TextView nameOne;


        public MyViewHolder(View itemView) {
            super(itemView);


            imageViewOne = itemView.findViewById(R.id.imageViewLeft);
            nameOne = itemView.findViewById(R.id.textViewLeft);


        }
    }
}

