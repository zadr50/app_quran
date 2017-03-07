package com.talagasoft.appqurannew;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;

public class SuratWebView extends AppCompatActivity {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surat_web_view_activity);
        Bundle bundle = getIntent().getExtras();
        int surat_ke=0;
        String mTitle="Al-Fatikhah";

        if(bundle!= null)
        {
            surat_ke=bundle.getInt("surat_ke",0);
            mTitle=bundle.getString("nama_surat","Al-fatikhah");
        }
        if(surat_ke==0)surat_ke=1;

        WebView wv = (WebView)this.findViewById(R.id.webView);
        Surat surat=new Surat(this.getBaseContext());

        String data=surat.load_as_html(surat_ke);
        // Log.e("AppQuran", "Data: " + data);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading WebPage...");

        wv.setWebViewClient(new CustomWebViewClient());
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setBuiltInZoomControls(true);
        wv.getSettings().setSupportZoom(true);
        wv.getSettings().setUseWideViewPort(true);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.loadDataWithBaseURL("file:///android_asset/", data, "text/html", "utf-8", null );





    }

    class CustomWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressDialog.dismiss();

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressDialog.show();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

    }


    public void onSound(View v) throws IOException {
        MediaPlayer mp = new MediaPlayer();

        mp.setDataSource("001/001.mp3");

        mp.prepare();
        mp.start();
    }
    private int doWork() {
        return 1;
    }

}
