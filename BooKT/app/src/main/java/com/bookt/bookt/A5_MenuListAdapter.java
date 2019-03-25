package com.bookt.bookt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class A5_MenuListAdapter extends ArrayAdapter<MenuItemElement> {

    private ArrayList<MenuItemElement> list;
    private int lastPosition = -1;

    public A5_MenuListAdapter(@NonNull Context context, @NonNull ArrayList<MenuItemElement> objects) {
        super(context, 0,  objects);
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.a5_menu_card, parent, false);
        }

        TextView foodTitle = view.findViewById(R.id.foodTitle);
        TextView foodDescription = view.findViewById(R.id.foodDescription);
        TextView foodPrice = view.findViewById(R.id.foodPrice);
        ImageView foodImage = view.findViewById(R.id.foodImage);


        Picasso.get()
                .load(list.get(position).getImage())
                .error(R.drawable.icon)
                .fit()
                .centerCrop()
                .into(foodImage);

        foodTitle.setText(list.get(position).getName());
        foodDescription.setText(list.get(position).getDescription());
        foodPrice.setText(list.get(position).getPrice());

        setAnimation(view, position);
        return view;
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left);
            animation.setInterpolator(new DecelerateInterpolator());
            if(position > 8){
                animation.setStartOffset(8 * 50);
            }else{
                animation.setStartOffset(position * 75);
            }
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
