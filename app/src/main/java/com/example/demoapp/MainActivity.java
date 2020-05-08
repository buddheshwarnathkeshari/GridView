package com.example.demoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.demoapp.fragment.earningWays;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        earningWays earnfragment = new earningWays();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, earnfragment).commit();

    }
}
