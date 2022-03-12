package com.example.nolfi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class BuyMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_main);
    }

    public void displayListClick(View view){
        Intent intent = new Intent(this, ListClick.class);
        startActivity(intent);
    }
}