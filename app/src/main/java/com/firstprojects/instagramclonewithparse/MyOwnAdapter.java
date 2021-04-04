package com.firstprojects.instagramclonewithparse;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyOwnAdapter extends ArrayAdapter<String> {
    private final ArrayList<String> username;
    private final ArrayList<String> commentText;
    private final ArrayList<Bitmap> bitmaps;
    private final Activity context;

    public MyOwnAdapter(ArrayList<String> username, ArrayList<String> commentText, ArrayList<Bitmap> bitmaps,Activity context){
        super(context,R.layout.myownadapter_layout,username);
        this.username = username;
        this.commentText = commentText;
        this.bitmaps = bitmaps;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Layout'u sınıf ve activity'e baglama
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View myOwnLayoutView = layoutInflater.inflate(R.layout.myownadapter_layout,null,true);
        TextView usernameView = myOwnLayoutView.findViewById(R.id.myOwnLayout_username_view);
        TextView commentView = myOwnLayoutView.findViewById(R.id.myOwnLayout_CommentText_view);
        ImageView selectedImage = myOwnLayoutView.findViewById(R.id.myOwnLayout_image_view);

        //Es zamanlı goruntu cekme
        usernameView.setText(username.get(position));
        commentView.setText(commentText.get(position));
        selectedImage.setImageBitmap(bitmaps.get(position));


        return myOwnLayoutView;
    }
}
