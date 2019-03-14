package com.bookt.bookt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;

import java.util.Collections;
import java.util.List;

public class A0_LoginActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a0_login_activity);

        ImageView homeImage = findViewById(R.id.homeImage);
        homeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), A1_GalleryActivity.class));
            }
        });

        context = this;
    }

    // Sign in Method
    public void signinMethod(View v){
        // app signin
        if("loginSignupButton".equals(v.getTag())){
            Toast.makeText(context, "Firebase Authentication Check", Toast.LENGTH_SHORT).show();

            // google signin
        }else if("googleLogin".equals(v.getTag())){
            Button button = (Button) v;

            List<AuthUI.IdpConfig> providers = Collections.singletonList(new AuthUI.IdpConfig.GoogleBuilder().build());

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    100);

            // facebook signin
        }else if("facebookLogin".equals(v.getTag())){
            Button button = (Button) v;

            List<AuthUI.IdpConfig> providers = Collections.singletonList(new AuthUI.IdpConfig.FacebookBuilder().build());

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    100);
            // forgot password
        }else{
            Toast.makeText(context, "Forgot Password Dialog", Toast.LENGTH_SHORT).show();
        }
    }
}
