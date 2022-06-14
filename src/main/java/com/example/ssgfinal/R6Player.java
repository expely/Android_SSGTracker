package com.example.ssgfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class R6Player extends AppCompatActivity {

    private final Handler uiHandler = new Handler();
    private ProgressDialog progressDialog;

    private ParsePlayer adapter;
    private ArrayList<ParsePlayerItems> parseItems = new ArrayList<>();

    private TextView titleText;
    private ImageView imageView;

    private Boolean check = true;

    private class JSHtmlInterfaceH {
        @android.webkit.JavascriptInterface
        public void showHTML(String html) {
            final String htmlContent = html;

            uiHandler.post(
                    new Runnable() {
                        @Override
                        public void run() {
                            Document doc = Jsoup.parse(htmlContent);
                            System.out.println(doc);
                            Elements elements = doc.select("div.trn-card");
                            parseItems.clear();
                            int size = elements.size();
                            for (int i = 0; i < size + 7; i++) {
                                String title = elements.select("div.trn-defstats.trn-defstats--width4 > div")
                                        .select("div.trn-defstat__name")
                                        .eq(i).text();
                                String subTitle = elements.select("div.trn-defstats.trn-defstats--width4 > div")
                                        .select("div.trn-defstat__value")
                                        .eq(i).text();
                                String subSubTitle = elements.select("div.trn-defstats.trn-defstats--width4 > div")
                                        .select("div.trn-defstat__rank")
                                        .eq(i).text();

                                if (!title.equals("")) {
                                    parseItems.add(new ParsePlayerItems(title, subTitle, subSubTitle));
                                    check = true;
                                } else if (title.equals(""))  {
                                    System.out.println(title.toString());
                                    check = false;
                                }

                            }
/*                            for (Element elementss : elements) {
                                System.out.println("brh");
                                String title = elementss.select("div.numbers")
                                        .select("span.name")
                                        .text();
                                parseItems.add(new ParsePlayerItems(title, "", ""));
                            }*/
                            adapter.notifyDataSetChanged();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    // yourMethod();
                                    progressDialog.dismiss();
                                }
                            }, 3000);   //4 seconds
                        }
                    }
            );
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r6_player);

        Window window = R6Player.this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(R6Player.this, R.color.black));

        titleText = findViewById(R.id.textViewTitleR);
        imageView = findViewById(R.id.imageViewR);

        titleText.setText(getIntent().getStringExtra("title"));
        Picasso.get().load(getIntent().getStringExtra("image")).into(imageView);

        RecyclerView listView = findViewById(R.id.recyclerViewR);

        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ParsePlayer(parseItems, this);
        listView.setAdapter(adapter);

        progressDialog = ProgressDialog.show(this, "Loading","Please wait...", true);
        progressDialog.setCancelable(false);

        try {
            final WebView browser = new WebView(this);
            browser.setVisibility(View.INVISIBLE);
            browser.setLayerType(View.LAYER_TYPE_NONE,null);
            browser.getSettings().setJavaScriptEnabled(true);
            browser.getSettings().setBlockNetworkImage(true);
            browser.getSettings().setDomStorageEnabled(false);
            browser.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            browser.getSettings().setDomStorageEnabled(true);
            browser.getSettings().setLoadsImagesAutomatically(false);
            browser.getSettings().setGeolocationEnabled(false);
            browser.getSettings().setSupportZoom(false);
            browser.getSettings().setUserAgentString("Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");

            browser.addJavascriptInterface(new JSHtmlInterfaceH(), "JSBridge");

            browser.setWebViewClient(
                    new WebViewClient() {
                        private int _progress = 0;
                        @Override
                        public void onPageStarted(WebView view, String url, Bitmap favicon) {
                            progressDialog.show();
                            super.onPageStarted(view, url, favicon);
                        }


                        @Override
                        public void onPageFinished(WebView view, String url) {
                            super.onPageFinished(view, url);
                            browser.loadUrl("javascript:window.JSBridge.showHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    if (!check) {
                                        browser.loadUrl(url);
                                    }
                                }
                            }, 4000);   //4 seconds

                        }

                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            view.loadUrl(url);
                            return false;
                        }

                    }
            );


            String url = "";

            String player = getIntent().getStringExtra("title");
            if (player.equals("Bosco")) {
                browser.loadUrl("https://r6.tracker.network/profile/pc/Bosco.SSG");
                browser.loadUrl(browser.getUrl());
            } else if (player.equals("Rampy")) {
                browser.loadUrl("https://r6.tracker.network/profile/pc/Rampy.SSG");
                browser.loadUrl(browser.getUrl());
            } else if (player.equals("Fultz")) {
                browser.loadUrl("https://r6.tracker.network/profile/pc/Fultz.SSG");
                browser.loadUrl(browser.getUrl());
            }     else if (player.equals("Skys")) {
                browser.loadUrl("https://r6.tracker.network/profile/pc/Skys.SSG");
                browser.loadUrl(browser.getUrl());
            } else if (player.equals("Hotancold")) {
                browser.loadUrl("https://r6.tracker.network/profile/pc/Hotancold.SSG");
                browser.loadUrl(browser.getUrl());
            } else if (player.equals("Lycan")) {
                browser.loadUrl("https://r6.tracker.network/profile/pc/Lycan.SSG");
                browser.loadUrl(browser.getUrl());
            } else if (player.equals("Sov")) {
                browser.loadUrl("https://r6.tracker.network/profile/pc/Sov.SSG");
                browser.loadUrl(browser.getUrl());
            } else if (player.equals("Luke")) {
                browser.loadUrl("https://r6.tracker.network/profile/pc/Luke.SSG");
                browser.loadUrl(browser.getUrl());
            }
            if(getSupportActionBar()!=null) getSupportActionBar().setTitle(browser.getUrl());
            browser.loadUrl(browser.getUrl());



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}