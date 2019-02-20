package com.bookt.bookt;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;


public class ReviewsActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reviews_activity_layout);

        context = this;

        RatingBar ratingBar = findViewById(R.id.ratingBar);
        Drawable drawable = ratingBar.getProgressDrawable();
        drawable.setColorFilter(Color.parseColor("#eb2748"), PorterDuff.Mode.SRC_ATOP);

        ListView listView = findViewById(R.id.listViewReview);

        removeRatingCardExtraViews();

        setupListView(listView);
    }

    public void backImageReview(View v){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void setupListView(ListView listView){

        ArrayList<ReviewsCard> list = new ArrayList<>();

        list.add(new ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));
        list.add(new ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));
        list.add(new ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));
        list.add(new ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));
        list.add(new ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));
        list.add(new ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));
        list.add(new ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));
        list.add(new ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));
        list.add(new ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));
        list.add(new ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));
        list.add(new ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));
        list.add(new ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));

        ReviewsListAdapter reviewsListAdapter = new ReviewsListAdapter(context, list);

        listView.setAdapter(reviewsListAdapter);

    }

    public void removeRatingCardExtraViews(){
        View textView1 = findViewById(R.id.ratingsTitle);
        View textView2 = findViewById(R.id.seeAll);

        ViewGroup parent = (ViewGroup) textView1.getParent();

        if (parent != null) {
            parent.removeView(textView1);
            parent.removeView(textView2);
        }
    }
}
