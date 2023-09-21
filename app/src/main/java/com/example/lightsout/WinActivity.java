package com.example.lightsout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        Intent intent = getIntent();
        int clicks = intent.getIntExtra("TAPS", -1);

        TextView click_counter = findViewById(R.id.click_count_TV);
        click_counter.setText("Taps done: " + clicks);
    }
}