package com.example.ssgfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Rainbow6 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ParseAdapter adapter;
    private ArrayList<ParseItem> parseItems = new ArrayList<>();
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rainbow6);

        Window window = Rainbow6.this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(Rainbow6.this, R.color.black));

        progressBar = findViewById(R.id.progressBarR);
        recyclerView = findViewById(R.id.recyclerViewR);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ParseAdapter(parseItems, this);
        recyclerView.setAdapter(adapter);

        Rainbow6.Content content = new Rainbow6.Content();
        content.execute();

    }

    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(Rainbow6.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(Rainbow6.this, android.R.anim.fade_out));
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "https://www.spacestationgaming.com/rainbow-six-siege";
                Document doc = Jsoup.connect(url).get();

                Elements data = doc.select("div.collectionteam-item.w-dyn-item");

                int size = data.size();
                for (int i = 0; i < size; i++) {
                    String imgUrl = data.select("div.playerimage")
                            .select("img")
                            .eq(i)
                            .attr("src");
                    String title = data.select("div.w-layout-grid.playergrid")
                            .select("h3")
                            .eq(i)
                            .text();
                    String subTitle = data.select("div.w-layout-grid.playergrid")
                            .select("p.fullname")
                            .eq(i)
                            .text();

                    parseItems.add(new ParseItem(imgUrl, title, subTitle, ""));
                    Log.d("items", "img: " + imgUrl + " . title: " + title + " . sub: " + subTitle);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}