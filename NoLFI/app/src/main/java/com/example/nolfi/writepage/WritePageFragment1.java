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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.nolfi.MainActivity;
import com.example.nolfi.R;
import com.example.nolfi.UserAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.UUID;

public class WritePageFragment1 extends Fragment implements View.OnClickListener{
    //메인 액티비티 객체 선언
    MainActivity activity;
    ImageView img_sell;
    Uri selectedImageURi;

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;

    private EditText mEtProductName, mEtCategory, mEtPurchaseDate, mEtExpirationDate;
    private EditText mEtSellingPrice, mEtLocation, mEtContact;

    /*
    * // 로그인한 유저의 정보 가져오기
      FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
      // 로그인한 유저의 고유 uid 가져오기
      String uid = user != null ? user.getUid() : null;
    * */

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
        View v=inflater.inflate(R.layout.fragment_writepage1,container,false);
        ImageView imageView1=v.findViewById(R.id.down_arrow1);
        registerForContextMenu(imageView1);

        mFirebaseAuth = mFirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("NolFI");

        //imageview eventlistener 연결
        img_sell=v.findViewById(R.id.sell_product_photo);
        img_sell.setOnClickListener(this);

        mEtProductName=v.findViewById(R.id.sell_product_name);
        mEtCategory=v.findViewById(R.id.sell_product_category);
        mEtPurchaseDate=v.findViewById(R.id.sell_product_purchasedate);
        mEtExpirationDate=v.findViewById(R.id.sell_product_expirationdate);
        mEtSellingPrice=v.findViewById(R.id.sell_product_sellingprice);
        mEtLocation=v.findViewById(R.id.sell_product_location);
        mEtContact=v.findViewById(R.id.sell_product_contact);


        // enroll 버튼
        Button btn_sell_enroll=(Button)v.findViewById(R.id.enroll_button);
        btn_sell_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser firebaseUser=mFirebaseAuth.getCurrentUser();
                final String randomKey= UUID.randomUUID().toString();

                String strProductName=mEtProductName.getText().toString();
                String strCategory=mEtCategory.getText().toString();

                String strPurchaseDate=mEtPurchaseDate.getText().toString();
                String strExpirationDate=mEtExpirationDate.getText().toString();
                String strSellingPrice=mEtSellingPrice.getText().toString();
                String strLocation=mEtLocation.getText().toString();
                String strContact=mEtContact.getText().toString();

                /*
                SellData sellData=new SellData();
                sellData.setProductName(strProductName);
                sellData.setCategory(strCategory);
                sellData.setPurchaseDate(strPurchaseDate);
                sellData.setExpirationDate(strExpirationDate);
                sellData.setSellingPrice(strSellingPrice);
                sellData.setLocation(strLocation);
                sellData.setContact(strContact);
                */

                HashMap sellData = new HashMap<>();
                sellData.put("product name", strProductName);
                sellData.put("category", strCategory);
                sellData.put("purchase date", strPurchaseDate);
                sellData.put("expiration date", strExpirationDate);

                sellData.put("selling price", strSellingPrice);
                sellData.put("location", strLocation);
                sellData.put("contact", strContact);


                // 삭제는 removeValue()
                mDatabaseRef.child("UserAccount/Store").child(firebaseUser.getUid()).child(randomKey).updateChildren(sellData);
            }
        });

        return v;

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = activity.getMenuInflater();
        inflater.inflate(R.menu.write_menu, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //group purchase 클릭
            case R.id.write_menu2:
                activity.onFragmentChange(2);
                Toast.makeText(getContext(), "Group buying click", Toast.LENGTH_LONG).show();
                break;
            //donate 클릭
            case R.id.write_menu3:
                activity.onFragmentChange(3);
                Toast.makeText(getContext(), "Donate click", Toast.LENGTH_LONG).show();
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
            img_sell.setImageURI(selectedImageURi);
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
