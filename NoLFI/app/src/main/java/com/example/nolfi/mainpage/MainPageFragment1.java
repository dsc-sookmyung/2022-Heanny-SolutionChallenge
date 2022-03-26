package com.example.nolfi.mainpage;


import android.content.Intent;

import android.content.Context;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nolfi.MainActivity;
import com.example.nolfi.R;
import com.example.nolfi.model.MainItem;
import com.example.nolfi.model.MainItemAdapter;

import java.util.ArrayList;


public class MainPageFragment1  extends Fragment implements View.OnClickListener{
    //메인 액티비티 객체 선언
    MainActivity activity;

    private RecyclerView mRecyclerView;
    private MainItemAdapter mRecyclerAdapter;
    private ArrayList<MainItem> mMainItem;
    boolean i=true;

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

        View v=inflater.inflate(R.layout.fragment_mainpage1,container,false);
        mRecyclerView = v.findViewById(R.id.recycler_view);

        /* initiate adapter */
        mRecyclerAdapter = new MainItemAdapter();

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        /* adapt data */
        mMainItem = new ArrayList<>();
        for(int i=1;i<=10;i++){
            mMainItem.add(new MainItem(Uri.EMPTY,"물건이름1","파는곳1","시간1","가격1","카테고리1"));
        }
        mRecyclerAdapter.setFriendList(mMainItem);
        return v;
    }

    //click event
    @Override
    public void onClick(View view) {

    }
}