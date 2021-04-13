package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    public void goLoginWindow(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //register firestore
    public void registerUser1(View view) {
        EditText userText = findViewById(R.id.txtName), emailText = findViewById(R.id.emailTxt);
        EditText passText = findViewById(R.id.passTxt);
        EditText addressText = findViewById(R.id.addressTxt);
        EditText phoneText = findViewById(R.id.phoneTxt);

        String name = userText.getText().toString();
        String email = emailText.getText().toString();
        String password = passText.getText().toString();
        String addressName = addressText.getText().toString();
        String phoneNumber = phoneText.getText().toString();


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            HashMap<String, Person> usersTable = new HashMap<>();
                            FirebaseUser user = mAuth.getCurrentUser();
                            String uid = user.getUid();
                            Person p = new Person(name, email, addressName, phoneNumber);
                            usersTable.put(uid, p);
                            db.collection("users").document("users").set(usersTable, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Register.this, "Register success.",
                                            Toast.LENGTH_LONG).show();
                                }
                            });
                        } else {
                            Toast.makeText(Register.this, "Register failed.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    //Register realtime Data Base- On
    public void registerUser(View view) {
        EditText userText = findViewById(R.id.txtName);
        String name = userText.getText().toString();

        EditText emailText = findViewById(R.id.emailTxt);
        String email = emailText.getText().toString();

        EditText passText = findViewById(R.id.passTxt);
        String password = passText.getText().toString();

        EditText addressText = findViewById(R.id.addressTxt);
        String addressName = addressText.getText().toString();

        EditText phoneText = findViewById(R.id.phoneTxt);
        String phoneNumber = phoneText.getText().toString();
        if (!name.equals("") && !email.equals("") && !password.equals("")  && !addressName.equals("") && !phoneNumber.equals(""))
        {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(Register.this, "Register success.",
                                        Toast.LENGTH_SHORT).show();

                                FirebaseUser user = mAuth.getCurrentUser();
                                String uid = user.getUid();
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("persons").child(uid);
                                Person p = new Person(name, email, addressName, phoneNumber);
                                myRef.setValue(p);


                            } else
                                {
                                    if(password.length()<6)
                                    {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Register.this, "Register failed - Password must contain at least 6 chars .",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                        Toast.makeText(Register.this, "Register failed.",
                                                Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else
        {
            Toast.makeText(Register.this, "Wrong Values.",
                    Toast.LENGTH_SHORT).show();
        }

    }
}