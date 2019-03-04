package com.bookt.bookt;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;


public class A4_ReviewsActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a4_reviews_activity);

        context = this;

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

        ArrayList<A4_ReviewsCard> list = new ArrayList<>();

        list.add(new A4_ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));
        list.add(new A4_ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));
        list.add(new A4_ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));
        list.add(new A4_ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));
        list.add(new A4_ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));
        list.add(new A4_ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));
        list.add(new A4_ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));
        list.add(new A4_ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));
        list.add(new A4_ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));
        list.add(new A4_ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));
        list.add(new A4_ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));
        list.add(new A4_ReviewsCard("Mohammed Bokhari", 4.5, "02/12/2018",
                "Hello, my name is mohammed and I tried this restaurant. It was amazing!", null));

        A4_ReviewsListAdapter a4ReviewsListAdapter = new A4_ReviewsListAdapter(context, list);

        listView.setAdapter(a4ReviewsListAdapter);

    }
}
