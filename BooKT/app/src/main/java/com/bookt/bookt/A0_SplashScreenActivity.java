package com.bookt.bookt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class A0_SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a0_splash_screen_activity);

        Intent intent = new Intent(getApplicationContext(),
                A1_GalleryActivity.class);
        startActivity(intent);
        finish();
    }
}
