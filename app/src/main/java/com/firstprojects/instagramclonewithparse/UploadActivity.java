package com.firstprojects.instagramclonewithparse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class UploadActivity extends AppCompatActivity {
ImageView imageView;
EditText editPostText;
Bitmap bitmapImageSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        imageView = findViewById(R.id.imageView);
        editPostText = findViewById(R.id.editPastText);


    }
    public void uploadImage(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intentToGallery, 2);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && resultCode == RESULT_OK && data != null ) {
            Uri dataUrl = data.getData();
            if(Build.VERSION.SDK_INT >= 28) {
                ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(),dataUrl);
                try {
               bitmapImageSelected = ImageDecoder.decodeBitmap(source);
                    imageView.setImageBitmap(bitmapImageSelected);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    bitmapImageSelected = MediaStore.Images.Media.getBitmap(getContentResolver(),dataUrl);
                    imageView.setImageBitmap(bitmapImageSelected);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //permissions degiskeni icerisinde istedigimiz degiskenler bulunuyor bu dogru kullanım
        if(requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intentToGallery, 2);
        }
    }

    public void shareButton(View view) {
        //UUID
        UUID uuid = UUID.randomUUID();



        ///Covert & Create Process ///Covert & Create Process ///Covert & Create Process ///Covert & Create Process
        String comment = editPostText.getText().toString();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmapImageSelected.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        byte[] bytes = outputStream.toByteArray();


//save //save //save //save
       //Parsefile for uploading bitmap process
       ParseFile parseFile = new ParseFile(uuid.toString(),bytes);

        ParseObject parseObject = new ParseObject("Posts");
        parseObject.put("comment",comment);
        parseObject.put("image",parseFile);
        parseObject.put("username", ParseUser.getCurrentUser().getUsername());
        parseObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e!=null){
                    Toast.makeText(UploadActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(UploadActivity.this, "Succesfull..!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UploadActivity.this,FeedActivity.class);
                    finish();
                    startActivity(intent);

                }
            }
        });


    }
}
//PERMISSION VE RESULT_OK 