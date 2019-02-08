package com.bookt.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splashActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_actvity);


        Intent intent = new Intent(getApplicationContext(),
                ScrollingActivity.class);
        startActivity(intent);
        finish();
    }
}
