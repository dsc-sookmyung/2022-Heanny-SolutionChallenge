package com.example.nolfi.write;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;


import com.example.nolfi.R;

public class SellActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, category);
        AutoCompleteTextView top_bar = findViewById(R.id.sell_topbar);
        top_bar.setAdapter(adapter);
    }

    private static final String[] category = new String[]{
            "Sell", "Group Buying", "Donate"
    };
}