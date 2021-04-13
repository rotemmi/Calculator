package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

import Fragments.FregmantSimple;
import Fragments.ScienceFragment;

public class Calculator extends AppCompatActivity
{
    private final String KEY="userId";
    private SharedPreferences.Editor edit;
    private FragmentManager fManger;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_activity);

        if ( getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        {
            String result = getIntent().getStringExtra(KEY);

            if(result!=null)
            {
                Toast.makeText(this,"Uid: "+result,Toast.LENGTH_LONG).show(); // showUid SharedPrefeerence Sent By Main Activity
            }
            read_from_real_data_base(); // say Hello

            fManger = getSupportFragmentManager();
            FragmentTransaction fT = fManger.beginTransaction();
            fT.add(R.id.FrId, new FregmantSimple()).commit();

        }
    }


    public void read_from_real_data_base()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference myRef = database.getReference("persons");
        myRef.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Person value = dataSnapshot.getValue(Person.class);
                Toast.makeText(Calculator.this, "Hello " + value.getName(),
                        Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
            }
        });
    }

    public void loadScience()
    {
        FragmentTransaction fT =fManger.beginTransaction();
        fT.replace(R.id.FrId, new ScienceFragment()).addToBackStack(null).commit();
    }
}