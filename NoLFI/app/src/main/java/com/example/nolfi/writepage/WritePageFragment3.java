package com.example.nolfi.writepage;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.nolfi.MainActivity;
import com.example.nolfi.R;

public class WritePageFragment3 extends Fragment implements View.OnClickListener{
    //메인 액티비티 객체 선언
    MainActivity activity;
    ImageView img_donate;
    Uri selectedImageURi;

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
        View v=inflater.inflate(R.layout.fragment_writepage3,container,false);
        ImageView imageView1=v.findViewById(R.id.down_arrow3);
        registerForContextMenu(imageView1);

        //imageview eventlistener 연결
        img_donate=v.findViewById(R.id.donate_product_photo);
        img_donate.setOnClickListener(this);
        return v;

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = activity.getMenuInflater();
        inflater.inflate(R.menu.write_menu, menu);
    }

    public boolean onContextItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            //sell 클릭
            case R.id.write_menu1:
                activity.onFragmentChange(1);
                Toast.makeText(getContext(), "Sell click", Toast.LENGTH_LONG).show();
                break;

            //group purchase 클릭
            case R.id.write_menu2:
                activity.onFragmentChange(2);
                Toast.makeText(getContext(), "Group Purchase click", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onContextItemSelected(item);
    }

    //imageview에 image 보여주기
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode ==  RESULT_OK && data != null & data.getData() != null) {
            selectedImageURi = data.getData();
            img_donate.setImageURI(selectedImageURi);
        }
    }

    @Override
    public void onClick(View view) {
            //갤러리에서 이미지 클릭해서 이미지 뷰에 보여주기
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/");
            startActivityForResult(intent, 1);
    }
}
