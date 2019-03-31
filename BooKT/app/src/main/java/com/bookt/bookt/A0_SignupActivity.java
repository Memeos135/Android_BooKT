package com.bookt.bookt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class A0_SignupActivity extends AppCompatActivity {
    private TextView emailAddress;
    private TextView personName;
    private TextView password;
    private TextView personMobile;
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a0_signup_activity);

        emailAddress = findViewById(R.id.emailText);
        personName = findViewById(R.id.emailText);
        password = findViewById(R.id.passwordText);
        personMobile = findViewById(R.id.mobileText);

        ImageView homeImage = findViewById(R.id.homeImage);
        homeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), A1_GalleryActivity.class));
            }
        });
    }

    public void signupHandler(View view){
        if(personMobile.getText().equals("") || emailAddress.getText().equals("") || password.getText().equals("")
         || personMobile.getText().equals("") || (personMobile.getText().toString().length() != 10)){

            Toast.makeText(this, "Please fill in all fields. Mobile number length is 10 digits.", Toast.LENGTH_SHORT).show();

        }else {

            if (validate(emailAddress.getText().toString())) {

                final FirebaseAuth mAuth = FirebaseAuth.getInstance();

                mAuth.createUserWithEmailAndPassword(emailAddress.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users").child(String.valueOf(mAuth.getUid()));
                                    UserProfileInformation userProfileInformation = new UserProfileInformation(personName.getText().toString(),
                                            emailAddress.getText().toString(), personMobile.getText().toString());
                                    mDatabase.child("profile_info").setValue(userProfileInformation);
                                    mDatabase.child("reservations").child("active").setValue("empty");
                                    mDatabase.child("reservations").child("in-active").setValue("empty");

                                    startActivity(new Intent(getApplicationContext(), A0_LoginActivity.class)
                                            .putExtra("email", emailAddress.getText().toString())
                                            .putExtra("password", password.getText().toString())
                                            .putExtra("activity", ""));

                                } else {

                                    Toast.makeText(A0_SignupActivity.this, "Registration failed, please try again.", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        }
    }

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
}
