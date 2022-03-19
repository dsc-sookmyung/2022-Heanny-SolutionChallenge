package com.example.nolfi;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;         // 파이어베이스 인증
    private DatabaseReference mDatabaseRef;     // 실시간 데이터베이스
    private FirebaseStorage storage;
    private StorageReference storageReference;

    private EditText mEtEmail, mEtPwd;          // 회원가입 입력필드
    private EditText mEtNickname, mEtStoreAddress, mEtStoreCategory;
    private Button mBtnRegister, mBtnImage;     // 회원가입 버튼

    ImageView imageViewProfile;
    Uri selectedImageURi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = mFirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("NolFI");
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();

        mEtEmail=findViewById(R.id.registerEmail);
        mEtPwd=findViewById(R.id.registerPassword);
        mBtnRegister=findViewById(R.id.registerSignUpBtn);

        //mBtnImage=findViewById(R.id.register_image_btn);
        mEtNickname=findViewById(R.id.registerNickname);
        mEtStoreAddress=findViewById(R.id.registerStoreAddress);
        mEtStoreCategory=findViewById(R.id.registerStoreCategory);

        imageViewProfile=findViewById(R.id.iv_register_profile);


        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEmail=mEtEmail.getText().toString();
                String strPwd=mEtPwd.getText().toString();

                String strNickname=mEtNickname.getText().toString();
                String strAddress=mEtStoreAddress.getText().toString();
                String strStoreCategory=mEtStoreCategory.getText().toString();
                //String strProfile="profile "+strPwd+".jpg";

                // Firebase Auth 진행
                mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser=mFirebaseAuth.getCurrentUser();
                            UserAccount account=new UserAccount();
                            account.setIdToken(firebaseUser.getUid());  // 로그인하면 부여되는 고유값
                            account.setEmailId(firebaseUser.getEmail());
                            account.setPassword(strPwd);

                            account.setNickname(strNickname);
                            account.setAddress(strAddress);
                            account.setCategory(strStoreCategory);
                            // account.setAddress(strStoreCategory);

                            // setValue: database에 삽입하는 행위
                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);
                            Toast.makeText(RegisterActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //image 클릭시
        imageViewProfile=(ImageView) findViewById(R.id.iv_register_profile);
        imageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //갤러리에서 이미지 클릭해서 이미지 뷰에 보여주기
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/");
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null & data.getData() != null) {
            selectedImageURi = data.getData();
            imageViewProfile.setImageURI(selectedImageURi);
            uploadPicture();
        }
    }

    private void uploadPicture() {

        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();

        final String randomKey= UUID.randomUUID().toString();
        StorageReference riversRef=storageReference.child("images/profile/"+randomKey);

        // Create a reference to "mountains.jpg"
        // StorageReference mountainsRef = storageReference.child("mountains.jpg");

        // Create a reference to 'images/mountains.jpg'
       //  StorageReference mountainImagesRef = storageReference.child("images/mountains.jpg");

        // While the file names are the same, the references point to different files
        //mountainsRef.getName().equals(mountainImagesRef.getName());    // true
        //mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false

        riversRef.putFile(selectedImageURi).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pd.dismiss();
                Snackbar.make(findViewById(android.R.id.content), "Image Uploaded", Snackbar.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "Failed to Upload.", Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progressPercent=(100.00*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                pd.setMessage("Percentage: "+(int)progressPercent+" %");
            }
        });
    }

    /*
    public void makeConfirmDialog(){
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(RegisterActivity.this, R.style.MyAlertDialogStyle);
        alt_bld.setTitle("작성 완료").setIcon(R.drawable.check_dialog_64).setMessage("글을 게시하시겠습니까?").setCancelable(false).setPositiveButton("네", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //DB에 등록하기
                final String cu = mAuth.getUid();
                //1. 사진을 storage에 저장하고 그 url을 알아내야함..
                String filename = cu + "_" + System.currentTimeMillis();
                StorageReference storageRef = storage.getReferenceFromUrl("본인의 Firebase 저장소").child("WriteClassImage/" + filename);
                UploadTask uploadTask;

                Uri file = null;
                if(flag ==0){
                    //사진촬영
                    file = Uri.fromFile(new File(mCurrentPhotoPath));
                }else if(flag==1){
                    //앨범선택
                    file = photoURI;
                }
                uploadTask = storageRef.putFile(file);

                final ProgressDialog progressDialog = new ProgressDialog(WriteClassActivity.this,R.style.MyAlertDialogStyle);
                progressDialog.setMessage("업로드중...");
                progressDialog.show();

                // Register observers to listen for when the download is done or if it fails
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Log.v("알림", "사진 업로드 실패");
                        exception.printStackTrace();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                        downloadUrl = taskSnapshot.getDownloadUrl();
                        Log.v("알림", "사진 업로드 성공 " + downloadUrl);
                    }
                });
            }
        }).setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // 아니오 클릭. dialog 닫기.
                dialog.cancel();
            }
        });
        AlertDialog alert = alt_bld.create();
        alert.show();
    }
    */


}
