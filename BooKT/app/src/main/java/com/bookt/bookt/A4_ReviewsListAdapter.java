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
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class A4_ReviewsListAdapter extends ArrayAdapter<A4_ReviewsCard> {

    private ArrayList<A4_ReviewsCard> list;

    public A4_ReviewsListAdapter(@NonNull Context context, @NonNull ArrayList<A4_ReviewsCard> objects) {
        super(context, 0,  objects);
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.a4_reviews_card, parent, false);
        }

        TextView userName = view.findViewById(R.id.userName);
        RatingBar userRating = view.findViewById(R.id.userRating);
        TextView userReviewDate = view.findViewById(R.id.userReviewDate);
        TextView userReview = view.findViewById(R.id.userReview);

        userName.setText(list.get(position).getName());
        userRating.setRating((float)list.get(position).getStars());
        userReview.setText(list.get(position).getReview());
        userReviewDate.setText(list.get(position).getDate());

        Animation animation = AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setStartOffset(position * 100);

        view.setAnimation(animation);

        return view;
    }
}
