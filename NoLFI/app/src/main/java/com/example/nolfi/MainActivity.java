package com.example.nolfi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Picture;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayProduct(View v){
        int id = v.getId();
        LinearLayout layout =(LinearLayout) v.findViewById(id);
        String tag =(String) layout.getTag();

        Intent it= new Intent(this, Product.class);
        it.putExtra("it_tag", tag);
        startActivity(it);
    }

    //navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
    //BottomNavigationViewHelper.disableShiftMode(navigation);
}