package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private final String KEY="userId";
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("loginFile", MODE_PRIVATE);
        setContentView(R.layout.main_activity);
        String userId = sharedPreferences.getString("userId", null);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (userId != null)
        {
            ((EditText)findViewById(R.id.txtName)).setText(sharedPreferences.getString("email", null));
            ((EditText)findViewById(R.id.passTxt)).setText(sharedPreferences.getString("pass", null));
            EditText passText = findViewById(R.id.passTxt);
            String password = passText.getText().toString();
            go_calc(userId);

        }
    }
    public void onStart()
    {
        super.onStart();
    }

    public void loginFunc(View view)
    {
        final SharedPreferences sharedPreferences = getSharedPreferences("loginFile", MODE_PRIVATE);
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        EditText emailText = findViewById(R.id.txtName);
        String email = emailText.getText().toString();
        EditText passText = findViewById(R.id.passTxt);
        String password = passText.getText().toString();
        if(!email.equals("")&& !password.equals(""))
        {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                edit.putString("userId", user.getUid());
                                edit.putString("email", email);
                                edit.putString("pass", password);
                                edit.apply();
                                go_calc(null);

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(MainActivity.this, "Login failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else
        {
            Toast.makeText(MainActivity.this, "Wrong Values - Please Enter Details.",
                    Toast.LENGTH_SHORT).show();
        }
    }


    public void go_calc(String userId)
    {
        Intent intent = new Intent(this, Calculator.class);
        intent.putExtra(KEY,userId);
        startActivity(intent);
    }
    public void goRegWindow(View view)
    {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

}