package com.example.nolfi.write;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.nolfi.R;

public class SellActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        //topbar의 글씨 바꾸는 dropdown 아래 화살표
        ImageView imageView1=(ImageView) findViewById(R.id.down_arrow1);
        registerForContextMenu(imageView1);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.write_menu, menu);
    }

    public boolean onContextItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
                //group purchase 클릭
            case R.id.write_menu2:
                Intent it1 = new Intent(SellActivity.this, GroupPurchase.class);
                it1.putExtra("page_value", "Group Purchase");
                Toast.makeText(getApplicationContext(), "Group buying click", Toast.LENGTH_LONG).show();
                startActivity(it1);
                finish();
                return true;

                //donate 클릭
            case R.id.write_menu3:
                Intent it2 = new Intent(SellActivity.this, Donate.class);
                it2.putExtra("page_value", "Donate");
                startActivity(it2);
                finish();
                return true;
        }

        return super.onContextItemSelected(item);
    }
}