package com.example.nolfi.mypage;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.nolfi.R;

public class MyPageFragment1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_mypage1,container,false);

        Button bookButton = (Button)v.findViewById(R.id.btn_bookmark);
        Button productButton = (Button)v.findViewById(R.id.btn_productlist);
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyPage_Bookmark.class);
                startActivity(intent);
            }
        });

        productButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Mypage_Product.class);
                startActivity(intent);
            }
        });



        /*Button btn1 = (Button)v.findViewById(R.id.btn_bookmark);
        Button btn2 = (Button)v.findViewById(R.id.btn_productlist);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);*/


        return v;
        }

        /*public void onClick(View view){

            switch (view.getId()){
                case R.id.btn_bookmark:
                    View(frag);
                case R.id.btn_productlist:
                    btnNum = 1;
                    Log.d("onclick","btnNum : "+ btnNum);
            }
        }*/




}