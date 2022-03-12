package com.example.nolfi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ListClick extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_click);
    }

    public void displayStoreClick(View view){
        Intent intent = new Intent(this, StoreClick.class);
        startActivity(intent);
    }

    public void list_click_phone(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Contact").setMessage("02-123-4567");

        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }
}