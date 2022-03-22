package com.example.nolfi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.nolfi.mainpage.MainPageFragment1;
import com.example.nolfi.mypage.MyPageFragment1;
import com.example.nolfi.writepage.WritePageFragment1;
import com.example.nolfi.writepage.WritePageFragment2;
import com.example.nolfi.writepage.WritePageFragment3;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView=findViewById(R.id.bottom_navigation);

        //첫 화면 띄우기
        getSupportFragmentManager().beginTransaction().add(R.id.frame_container,new MainPageFragment1()).commit();

        //case 함수를 통해 클릭 받을 때마다 화면 변경하기 (bottom navi)
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    //mainpage
                    case R.id.menu_1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new MainPageFragment1()).commit();
                        break;
                    //write page
                    case R.id.menu_2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new WritePageFragment1()).commit();
                        break;
                    //mypage
                    case R.id.menu_3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new MyPageFragment1()).commit();
                        break;

                }
                return true;
            }
        });
    }
    //프래그먼트가 바뀔때 작동하게끔 작성한 메서드
    public void onFragmentChange(int fragmentNum) {
        //sell
        if (fragmentNum == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new WritePageFragment1()).commit();
        } else if (fragmentNum == 2) { //group purchase
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new WritePageFragment2()).commit();
        } else if (fragmentNum == 3) { //donate
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new WritePageFragment3()).commit();
        }else if (fragmentNum == 4) { //main page -> mainpage2로 이동
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new WritePageFragment3()).commit();
        }
    }
}