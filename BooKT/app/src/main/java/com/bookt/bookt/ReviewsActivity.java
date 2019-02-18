package com.bookt.bookt;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ReviewsActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reviews_activity_layout);

        context = this;

        TextView textView = findViewById(R.id.textView9);
        textView.setVisibility(View.INVISIBLE);
        textView.setEnabled(false);

        ListView listView = findViewById(R.id.listViewReview);

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
}
