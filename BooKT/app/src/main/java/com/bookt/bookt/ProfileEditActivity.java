package com.bookt.bookt;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileEditActivity extends AppCompatActivity {

    Context context;
    private EditText password;
    private EditText mobileText;
    private EditText emailText;
    private EditText passwordText;
    private Button verifyButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_edit_dialog);


        password = findViewById(R.id.editText);

        mobileText = findViewById(R.id.mobileText);
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        verifyButton = findViewById(R.id.verifyButton);

        context = this;
    }

    public void verificationButton(View v){
        if(password.getText().toString().equals("")){
            Toast.makeText(context, "Please provide your password first.", Toast.LENGTH_SHORT).show();
        }else{
            if(findViewById(R.id.waitProgressBar) == null) {
                showWaiting();

                FirebaseAuth.getInstance().signInWithEmailAndPassword(FirebaseAuth.getInstance().getCurrentUser().getEmail(), password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    ConstraintLayout constraintLayout = findViewById(R.id.constraint);
                                    constraintLayout.setAlpha(1f);

                                    password.setAlpha(0.1f);
                                    password.setEnabled(false);
                                    verifyButton.setAlpha(0.1f);
                                    verifyButton.setEnabled(false);

                                    mobileText.setEnabled(true);
                                    emailText.setEnabled(true);
                                    passwordText.setEnabled(true);

                                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("profile_info");

                                    mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.exists()) {
                                                mobileText.setHint(dataSnapshot.child("mobile").getValue().toString());
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                    emailText.setHint(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                                    passwordText.setHint(password.getText().toString());
                                } else {
                                    Toast.makeText(context, "Please provide the correct password.", Toast.LENGTH_SHORT).show();
                                }
                                cancelWaiting();
                            }
                        });

            }
        }
    }

    public void changeCredentials(View v){
        if(v.getTag().equals("cancelButton")){
            onBackPressed();
        }else {
            if (findViewById(R.id.waitProgressBar) == null) {
                if (!emailText.getText().toString().equals(emailText.getHint().toString()) && !emailText.getText().toString().equals("")) {
                    AuthCredential authCredential = EmailAuthProvider.getCredential(emailText.getHint().toString(), passwordText.getHint().toString());
                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                    showWaiting();

                    firebaseUser.reauthenticate(authCredential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            FirebaseUser firebaseUser1 = FirebaseAuth.getInstance().getCurrentUser();

                            firebaseUser1.updateEmail(emailText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        emailText.setHint("changed successfully > " + emailText.getText().toString());

                                        FirebaseAuth mAuth = FirebaseAuth.getInstance();

                                        mAuth.signInWithEmailAndPassword(emailText.getText().toString(), passwordText.getHint().toString());
                                    } else {
                                        Toast.makeText(ProfileEditActivity.this, "Failed to update email.", Toast.LENGTH_SHORT).show();
                                    }
                                    emailText.setText("");
                                    cancelWaiting();
                                }
                            });
                        }
                    });
                }
                if (!passwordText.getText().toString().equals(passwordText.getHint().toString()) && !passwordText.getText().toString().equals("")) {

                    if (!emailText.getText().toString().equals("")) {
                        Toast.makeText(context, "Please update password separately from email.", Toast.LENGTH_SHORT).show();
                    } else {

                        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

                            AuthCredential authCredential = EmailAuthProvider.getCredential(emailText.getHint().toString(), passwordText.getHint().toString());
                            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                            showWaiting();

                            firebaseUser.reauthenticate(authCredential).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    final FirebaseUser firebaseUser1 = FirebaseAuth.getInstance().getCurrentUser();

                                    firebaseUser1.updatePassword(passwordText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                passwordText.setHint("changed successfully > " + passwordText.getText().toString());
                                            } else {
                                                Toast.makeText(ProfileEditActivity.this, "Failed to password.", Toast.LENGTH_SHORT).show();
                                            }
                                            passwordText.setText("");
                                            cancelWaiting();
                                        }
                                    });
                                }
                            });
                        } else {
                            Toast.makeText(context, "Please sign in before changing password.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            if (!mobileText.getText().toString().equals(mobileText.getHint().toString()) && !mobileText.getText().toString().equals("")) {

                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("profile_info").child("mobile");

                mDatabase.setValue(mobileText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            mobileText.setHint("changed successfully > " + mobileText.getText().toString());
                        } else {
                            Toast.makeText(context, "Failed to change mobile number.", Toast.LENGTH_SHORT).show();
                        }
                        mobileText.setText("");
                    }
                });
            }
        }
    }


    public void showWaiting(){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup parent = (ViewGroup)findViewById(R.id.constraint);
        inflater.inflate(R.layout.waiting_animation, parent);
    }

    public void cancelWaiting(){
        ProgressBar progressBar = findViewById(R.id.waitProgressBar);
        ((ViewGroup)progressBar.getParent()).removeView(progressBar);
    }
}
