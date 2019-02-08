package com.bookt.bookt;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;

import java.util.Collections;
import java.util.List;


public class ReservationConfirmationActivity extends AppCompatActivity{
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmation_activity);
        context = this;

        animateButtons();

    }

    // Back image handler
    public void backHandler(View v){
        onBackPressed();
    }

    // animation login buttons
    public void animateButtons(){
        Button loginButton = findViewById(R.id.loginSignupButton);
        Button googleLogin = findViewById(R.id.googleLogin);
        Button facebookLogin = findViewById(R.id.facebookLogin);
        TextView textView = findViewById(R.id.textLogin);

        loginButton.animate().alpha(1.0F).setDuration(700L);
        googleLogin.animate().alpha(1.0F).setDuration(700L);
        facebookLogin.animate().alpha(1.0F).setDuration(700L);
        textView.animate().alpha(1.0F).setDuration(700L);
    }

    // Sign in Method
    public void signinMethod(View v){
        // app signin & signup
        if("loginSignupButton".equals(v.getTag())){
            Button button = (Button) v;

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
            // no signin
        }else{
            TextView textView = (TextView) v;
        }
    }
}
