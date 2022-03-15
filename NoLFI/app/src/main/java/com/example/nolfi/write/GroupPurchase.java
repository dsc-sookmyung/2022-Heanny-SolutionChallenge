package com.example.nolfi.write;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
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

public class GroupPurchase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_purchase);
        ImageView imageView2=(ImageView) findViewById(R.id.down_arrow2);
        registerForContextMenu(imageView2);
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
            //sell 클릭
            case R.id.write_menu1:
                Intent it0 = new Intent(GroupPurchase.this, SellActivity.class);
                it0.putExtra("page_value", "Sell");
                Toast.makeText(getApplicationContext(), "Sell", Toast.LENGTH_LONG).show();
                startActivity(it0);
                finish();
                return true;

                //donate 클릭
            case R.id.write_menu3:
                Intent it2 = new Intent(GroupPurchase.this, Donate.class);
                it2.putExtra("page_value", "Donate");
                startActivity(it2);
                finish();
                return true;

        }

        return super.onContextItemSelected(item);
    }
}