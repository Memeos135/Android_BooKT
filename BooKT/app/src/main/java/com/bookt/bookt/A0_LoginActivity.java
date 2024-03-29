package com.bookt.bookt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Collections;
import java.util.List;

public class A0_LoginActivity extends AppCompatActivity {

    Context context;
    private TextView emailAddress;
    private TextView password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a0_login_activity);

        context = this;

        emailAddress = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);

        if(getIntent().getStringExtra("email") != null){
            Toast.makeText(context, "Registration Successful. Please proceed by logging in.", Toast.LENGTH_SHORT).show();
            emailAddress.setText(getIntent().getStringExtra("email"));
            password.setText(getIntent().getStringExtra("password"));
        }

        ImageView homeImage = findViewById(R.id.homeImage);
        homeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), A1_GalleryActivity.class));
            }
        });
    }

    // Sign in Method
    public void signinMethod(View v){

        // forgot password
        if(v.getTag().equals("forgotPassword")){

            if(emailAddress.getText().toString().equals("")){
                Toast.makeText(context, "Please provide an email address.", Toast.LENGTH_SHORT).show();
            }else{
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.sendPasswordResetEmail(emailAddress.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(context, "A password reset link has been sent to your email address.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Password reset email failed to send.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

        }else {
                // app signin
                if ("loginSignupButton".equals(v.getTag())) {
                    if (!emailAddress.getText().toString().equals("") && !password.getText().toString().equals("")) {
                        FirebaseAuth mAuth = FirebaseAuth.getInstance();

                        if(findViewById(R.id.waitProgressBar) == null) {
                            showWaiting();

                            mAuth.signInWithEmailAndPassword(emailAddress.getText().toString(), password.getText().toString())
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {

                                                switch (getIntent().getStringExtra("activity")) {
                                                    case "A0_ProfileActivity":

                                                        startActivity(new Intent(context, A0_ProfileActivity.class));

                                                        break;
                                                    case "A1_GalleryActivity":

                                                        startActivity(new Intent(context, A1_GalleryActivity.class));

                                                        break;
                                                    case "A2_RestaurantsActivity":

                                                        startActivity(new Intent(context, A2_RestaurantsActivity.class));

                                                        break;
                                                    case "A3_RestaurantDetailsActivity":

                                                        startActivity(new Intent(context, A3_RestaurantDetailsActivity.class));

                                                        break;

                                                    default:

                                                        startActivity(new Intent(context, A1_GalleryActivity.class));

                                                        break;
                                                }

                                            } else {

                                                Toast.makeText(context, "Authentication failed, please try again.", Toast.LENGTH_SHORT).show();

                                            }
                                            cancelWaiting();
                                        }
                                    });
                        }
                    }else{
                        Toast.makeText(context, "Please fill in all required fields.", Toast.LENGTH_SHORT).show();
                    }

                    // google signin
                } else if ("googleLogin".equals(v.getTag())) {
                    Button button = (Button) v;

                    List<AuthUI.IdpConfig> providers = Collections.singletonList(new AuthUI.IdpConfig.GoogleBuilder().build());

                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(providers)
                                    .build(),
                            100);

                    // facebook signin
                } else if ("facebookLogin".equals(v.getTag())) {
                    Button button = (Button) v;

                    List<AuthUI.IdpConfig> providers = Collections.singletonList(new AuthUI.IdpConfig.FacebookBuilder().build());

                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(providers)
                                    .build(),
                            100);
                }
            }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100){
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if(requestCode == RESULT_OK){
                Toast.makeText(context, "Successful signin", Toast.LENGTH_SHORT).show();
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            }else{
                Toast.makeText(context, response.getError().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void showWaiting(){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup parent = (ViewGroup)findViewById(R.id.drawer_layout);
        inflater.inflate(R.layout.waiting_animation, parent);
    }

    public void cancelWaiting(){
        ProgressBar progressBar = findViewById(R.id.waitProgressBar);
        ((ViewGroup)progressBar.getParent()).removeView(progressBar);
    }
}
