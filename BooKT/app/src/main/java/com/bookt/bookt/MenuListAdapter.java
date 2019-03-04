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

import java.util.ArrayList;

public class MenuListAdapter extends ArrayAdapter<ExpandableChildItem> {

    private ArrayList<ExpandableChildItem> list;
    private int lastPosition = -1;

    public MenuListAdapter(@NonNull Context context, @NonNull ArrayList<ExpandableChildItem> objects) {
        super(context, 0,  objects);
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.expandable_listview_child, parent, false);
        }

        TextView foodTitle = view.findViewById(R.id.foodTitle);
        TextView foodDescription = view.findViewById(R.id.foodDescription);
        TextView foodPrice = view.findViewById(R.id.foodPrice);

        foodTitle.setText(list.get(position).getFoodTitle());
        foodDescription.setText(list.get(position).getFoodDescription());
        foodPrice.setText(list.get(position).getFoodPrice());

        setAnimation(view, position);
        return view;
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left);
            animation.setInterpolator(new DecelerateInterpolator());
            animation.setStartOffset(position * 100);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
