package com.example.gamedetailapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ShowDataActivity extends AppCompatActivity {



    String genre;
    String link;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);


        title = getIntent().getParcelableExtra("title");
        genre = getIntent().getParcelableExtra("genre");
        link = getIntent().getParcelableExtra("link");

        TextView text1 = findViewById(R.id.text1_activityItem);
        text1.setText(title);

        TextView text2 = findViewById(R.id.text2_activityItem);
        text2.setText(genre);


    }


}
