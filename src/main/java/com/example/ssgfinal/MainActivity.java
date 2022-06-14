package com.example.ssgfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    ImageView RocketLeague_Button;
    ImageView Halo_Button;
    ImageView Rainbow6_Button;
    ImageView IRacer;
    ImageView Apex_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = MainActivity.this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.black));

        // Button Controls
        RocketLeague_Button = findViewById(R.id.rocketLeague);
        RocketLeague_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this, RocketLeague.class);
                startActivity(intentLoadNewActivity);
            }
        });

        Halo_Button = findViewById(R.id.Halo);
        Halo_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this, HaloInfinite.class);
                startActivity(intentLoadNewActivity);
            }
        });

        Rainbow6_Button = findViewById(R.id.rainbowSix);
        Rainbow6_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this, Rainbow6.class);
                startActivity(intentLoadNewActivity);
            }
        });

        IRacer = findViewById(R.id.IRacing);
        IRacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this, IRacing.class);
                startActivity(intentLoadNewActivity);
            }
        });

        Apex_Button = findViewById(R.id.Apex);
        Apex_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this, Apex.class);
                startActivity(intentLoadNewActivity);
            }
        });

    }
}
