package com.example.nolfi.mainpage;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nolfi.MainActivity;
import com.example.nolfi.R;

public class MainPageFragment2 extends Fragment implements View.OnClickListener{
    //메인 액티비티 객체 선언
    MainActivity activity;

    boolean i=true;
    ImageView imageview;
    LinearLayout layout_store;

    @Override public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //현재 소속된 액티비티를 메인 액티비티로 한다.
        activity = (MainActivity) getActivity();
    }
    @Override public void onDetach() {
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_mainpage2,container,false);
        layout_store=v.findViewById(R.id.layout_store);
        layout_store.setOnClickListener(this);
        imageview=v.findViewById(R.id.icon_phone);
        imageview.setOnClickListener(this);
        return v;
    }

    public void onClick(View v){
        switch (v.getId()){
            //fragment 변환 (layout_store 클릭시)
            case R.id.layout_store:{
                activity.onFragmentChange(5);
            }
            //전화모양 클릭시 -> 다이얼로그창 띄움
            case R.id.icon_phone:{
                // 데이터를 다이얼로그로 보내는 코드
                Bundle args = new Bundle();
                args.putString("key", "value");
                FragmentDialog dialog = new FragmentDialog();
                dialog.setArguments(args); // 데이터 전달
                dialog.show(getActivity().getSupportFragmentManager(),"tag");
                break;
            }
        }
    }
}
