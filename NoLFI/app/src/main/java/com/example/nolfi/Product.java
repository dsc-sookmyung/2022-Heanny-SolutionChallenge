package com.example.nolfi;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Product extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);

        ImageView iv_product = (ImageView)findViewById(R.id.product);
        TextView tv_livingarea = (TextView)findViewById(R.id.livingarea);
        TextView tv_productname = (TextView)findViewById(R.id.productname);
        TextView tv_uploadtime = (TextView)findViewById(R.id.uploadtime);
        TextView tv_productprice = (TextView)findViewById(R.id.productprice);

        Intent it=getIntent();
        String tag=it.getStringExtra("it_tag");

        Resources res = getResources();

        int id_product=res.getIdentifier("product_name"+tag, "string", getPackageName());
        String product=res.getString(id_product);
        int id_img=res.getIdentifier(product, "drawable", getPackageName());
        Drawable drawable =res.getDrawable(id_img);
        iv_product.setBackground(drawable);

        int id_livingarea=res.getIdentifier("living_area"+tag, "string", getPackageName());
        String livingarea=res.getString(id_livingarea);
        tv_livingarea.setText(livingarea);

        int id_productname=res.getIdentifier("product_name"+tag, "string", getPackageName());
        String productname=res.getString(id_productname);
        tv_productname.setText(productname);

        int id_uploadtime=res.getIdentifier("time"+tag, "string", getPackageName());
        String uploadtime=res.getString(id_uploadtime);
        tv_uploadtime.setText(uploadtime);

        int id_productprice=res.getIdentifier("product_price", "string", getPackageName());
        String productprice=res.getString(id_productprice);
        tv_productprice.setText(productprice);
    }

    //public void closeproduct(View v){
        //finish(); }
}