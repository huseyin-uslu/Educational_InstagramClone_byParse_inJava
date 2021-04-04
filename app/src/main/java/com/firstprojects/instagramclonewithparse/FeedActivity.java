package com.firstprojects.instagramclonewithparse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.LogOutCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {
ListView listView;
ArrayList<String> username;
ArrayList<String> commenText;
ArrayList<Bitmap> bitmaps;
MyOwnAdapter myOwnAdapter;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = this.getMenuInflater();
        menuInflater.inflate(R.menu.feed_activity_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add_post_menu_feed) {
            //Add post Activity
            Intent intentToUploadActivity = new Intent(this,UploadActivity.class);
            startActivity(intentToUploadActivity);

        }else if(item.getItemId() == R.id.log_aut_menu_feed) {
            ParseUser user = new ParseUser();
            user.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {
                    if(e!=null){
                        Toast.makeText(FeedActivity.this,e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }else{
                        Intent intent = new Intent(FeedActivity.this,SignUpActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(FeedActivity.this, "Logout is Succesful", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        listView = findViewById(R.id.listView);
        username = new ArrayList<>();
        commenText = new ArrayList<>();
        bitmaps = new ArrayList<>();
        myOwnAdapter = new MyOwnAdapter(username,commenText,bitmaps,this);
        listView.setAdapter(myOwnAdapter);

        download();
    }

    public void download(){
        ParseQuery parseQuery = new ParseQuery("Posts");
       parseQuery.getQuery("Posts").findInBackground(new FindCallback<ParseObject>() {
           @Override
           public void done(List<ParseObject> objects, ParseException e) {
               if(e!=null){
                   Toast.makeText(FeedActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
               }else {
                   if(objects.size() > 0) {
                   for (final ParseObject parseObject : objects) {
                      ParseFile parseFile = parseObject.getParseFile("image");
                      parseFile.getDataInBackground(new GetDataCallback() {
                          @Override
                          public void done(byte[] data, ParseException e) {
                              if(e == null && data != null){

                                      Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
                                      username.add(parseObject.getString("username"));
                                      commenText.add(parseObject.getString("comment"));
                                      bitmaps.add(bitmap);
                                      myOwnAdapter.notifyDataSetChanged();



                              }
                          }
                      });

                   }
                   }
               }
           }
       });
    }
}