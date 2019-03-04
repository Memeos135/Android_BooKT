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

public class ReviewsListAdapter extends ArrayAdapter<ReviewsCard> {

    private ArrayList<ReviewsCard> list;

    public ReviewsListAdapter(@NonNull Context context, @NonNull ArrayList<ReviewsCard> objects) {
        super(context, 0,  objects);
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.reviews_card, parent, false);
        }

        TextView userName = view.findViewById(R.id.userName);
        RatingBar userRating = view.findViewById(R.id.userRating);
        TextView userReviewDate = view.findViewById(R.id.userReviewDate);
        TextView userReview = view.findViewById(R.id.userReview);

        userName.setText(list.get(position).getName());
        userRating.setRating((float)list.get(position).getStars());
        userReview.setText(list.get(position).getReview());
        userReviewDate.setText(list.get(position).getDate());

        return view;
    }

}
