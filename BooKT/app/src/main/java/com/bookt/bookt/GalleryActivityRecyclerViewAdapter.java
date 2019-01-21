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

    // RecyclerView Arraylist Size
    private int size = 0;

    /*
    RecyclerView Arraylist Positions
    (used onCreate to track RecyclerView index values (even/odd) to do dynamic card assignments)
    */
    private int position = 0;

    public GalleryActivityRecyclerViewAdapter(Context context, ArrayList<GalleryActivityCard> list) {
        this.context = context;
        this.list = list;
        this.size = list.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Subtract position from RecyclerView arraylist size to get current position
        size = size - position;

        // If card position is ZERO or multiple of THREE (big card)
        if(size%3 == 0 || position == 0){

            // Inflate the XML associated with FULL SCREEN-WIDTH card
            View view;
            LayoutInflater mInflater = LayoutInflater.from(context);
            view = mInflater.inflate(R.layout.gallery_view_card_three,parent,false);
            size = 0;
            return new MyViewHolder(view);

        }else{

            // If card position is multiple of TWO
            if(size%2 == 0) {

                // Inflate card associated with (0.55, 0.5) split
                View view;
                LayoutInflater mInflater = LayoutInflater.from(context);
                view = mInflater.inflate(R.layout.gallery_view_card, parent, false);
                size = 0;
                return new MyViewHolder(view);

            } else{

                // Inflate card associated with (0.5, 0.55) split
                View view;
                LayoutInflater mInflater = LayoutInflater.from(context);
                view = mInflater.inflate(R.layout.gallery_view_card_two, parent, false);
                size = 0;
                return new MyViewHolder(view);

            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        /*
        After onCreate Inflation of specific XMLs, check position of card.
        If card is at ZERO position or multiple of THREE, set image and text for BIG IMAGE
        */
        if(position%3 == 0 || position == 0){

            holder.imageViewThree.setImageResource(R.drawable.icon);
            holder.nameThree.setText(list.get(position).getRestaurantTypeName());

        /*
        If card is NOT ZERO or multiple of THREE, set image of (0.55, 0.5) OR (0.5, 0.55) image splits
        */
        }else{

            holder.imageViewTwo.setImageResource(R.drawable.icon);
            holder.nameTwo.setText(list.get(position).getRestaurantTypeName());

            holder.imageViewOne.setImageResource(R.drawable.icon);
            holder.nameOne.setText(list.get(position).getRestaurantTypeName());

            }

    }

    @Override
    public int getItemViewType(int position) {

        // Save position of card in RecyclerView to use in onCreate and onBindViewHolder
        this.position = position;

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
        ImageView imageViewTwo;
        ImageView imageViewThree;

        TextView nameOne;
        TextView nameTwo;
        TextView nameThree;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageViewOne = itemView.findViewById(R.id.imageViewLeft);
            nameOne = itemView.findViewById(R.id.textViewLeft);

            imageViewTwo = itemView.findViewById(R.id.imageViewRight);
            nameTwo = itemView.findViewById(R.id.textViewRight);

            imageViewThree = itemView.findViewById(R.id.imageViewBig);
            nameThree = itemView.findViewById(R.id.textViewBig);

        }
    }
}
