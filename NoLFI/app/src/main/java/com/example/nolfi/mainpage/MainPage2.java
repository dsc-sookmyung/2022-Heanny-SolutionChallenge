package com.example.nolfi.mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nolfi.R;

public class MainPage2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanveState){
        super.onCreate(savedInstanveState);
        setContentView(R.layout.fragment_mainpage2);
    }

    public void displayStoreClick(View view){
        Intent intent = new Intent(this, MainPage3.class);
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
