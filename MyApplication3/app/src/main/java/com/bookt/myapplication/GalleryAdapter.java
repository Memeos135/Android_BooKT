package com.bookt.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Gallery> galleryArrayList;
    public static int counter =0;

    public GalleryAdapter(Context context, ArrayList<Gallery> galleryArrayList) {
        this.context = context;
        this.galleryArrayList = galleryArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.gallary_card,viewGroup,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.cardView.getLayoutParams().height = (int) (ScrollingActivity.size.x / 2);



            if(counter==0) {
                myViewHolder.cardView.setContentPadding(0, 4, 2, 4);
                myViewHolder.galleryText.setBackgroundColor(Color.parseColor("#EB2748"));
                counter++;
            }
            else if (counter == 1){
                myViewHolder.cardView.setContentPadding(2, 4, 0, 4);
                myViewHolder.galleryText.setBackgroundColor(Color.parseColor("#FFFFFF"));
                counter++;
            }
            else if( counter == 2){
                myViewHolder.cardView.setContentPadding(0, 4, 2, 4);
                myViewHolder.galleryText.setBackgroundColor(Color.parseColor("#FFFFFF"));
                counter++;
            }
            else{
                myViewHolder.cardView.setContentPadding(2, 4, 0, 4);
                myViewHolder.galleryText.setBackgroundColor(Color.parseColor("#EB2748"));
                counter =0;
            }
            myViewHolder.galleryImage.setImageResource(R.drawable.gicon);




        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,RestaurantsActivity.class).addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT));
            }
        });


    }

    @Override
    public int getItemCount() {
        return galleryArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView galleryImage;
        TextView galleryText;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            galleryImage = itemView.findViewById(R.id.galleryImage);
            galleryText = itemView.findViewById(R.id.galleryName);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
