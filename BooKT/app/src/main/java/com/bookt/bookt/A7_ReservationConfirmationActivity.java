package com.bookt.bookt;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;

import java.util.Collections;
import java.util.List;


public class A7_ReservationConfirmationActivity extends AppCompatActivity{
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a7_confirmation_activity);
        context = this;

        animateButtons();

    }

    // Back image handler
    public void backHandler(View v){
        onBackPressed();
    }

    // animation login buttons
    public void animateButtons(){
        Button loginButton = findViewById(R.id.loginButton);
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
        if("loginSignupButton".equals(v.getTag()) || "textLogin".equals(v.getTag())){

            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.a7_confirmed_dialog);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setCancelable(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.create();
            }
            dialog.show();

            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    dialog.cancel();
                    startActivity(new Intent(context, A1_GalleryActivity.class));
                }
            });

            dialog.findViewById(R.id.constraintLayoutConfirmed).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                    startActivity(new Intent(context, A1_GalleryActivity.class));
                }
            });

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
        }
    }
}
