package com.example.nolfi.write;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nolfi.R;

public class Donate extends AppCompatActivity {
    ImageView img_donate;
    Uri selectedImageURi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        ImageView imageView3=(ImageView) findViewById(R.id.down_arrow3);
        //image 클릭시
        img_donate = (ImageView) findViewById(R.id.donate_product_photo);
        img_donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //갤러리에서 이미지 클릭해서 이미지 뷰에 보여주기
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/");
                startActivityForResult(intent, 1);
            }
        });

        registerForContextMenu(imageView3);
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
                Intent it0 = new Intent(Donate.this, SellActivity.class);
                it0.putExtra("page_value", "Sell");
                Toast.makeText(getApplicationContext(), "Sell", Toast.LENGTH_LONG).show();
                startActivity(it0);
                finish();
                return true;

                //group purchase 클릭
            case R.id.write_menu2:
                Intent it1 = new Intent(Donate.this, GroupPurchase.class);
                it1.putExtra("page_value", "Group Purchase");
                Toast.makeText(getApplicationContext(), "Group buying click", Toast.LENGTH_LONG).show();
                startActivity(it1);
                finish();
                return true;

        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null & data.getData() != null) {
            selectedImageURi = data.getData();
            img_donate.setImageURI(selectedImageURi);
        }
    }
}
