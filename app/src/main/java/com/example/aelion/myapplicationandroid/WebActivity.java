package com.example.aelion.myapplicationandroid;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WebActivity extends AppCompatActivity implements View.OnClickListener {

    Button ok;
    EditText editText;
    TextView textView;
    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        editText = findViewById(R.id.edtext);
        ok = findViewById(R.id.btok);
        ok.setOnClickListener(this);
        editText.setText("http://www.amonteiro.fr");
        textView = findViewById(R.id.textView2);

        myWebView = findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient());

        //Activer le Javascript(Attention aux performances)
        WebSettings webviewSettings = myWebView.getSettings();
        webviewSettings.setJavaScriptEnabled(true);

    }

    @Override
    public void onClick(View v) {
        if (v == ok) {
            myWebView.loadUrl(editText.getText().toString());
            MyAT myAt = new MyAT();
            myAt.execute();
            Toast.makeText(this, "url : " + editText.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public class MyAT extends AsyncTask {

        String response;
        String url;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            url = editText.getText().toString();
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                response = WSUtils.sendGetOkHttpRequest(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            textView.setText(response);
            Toast.makeText(WebActivity.this, "Chargement terminé", Toast.LENGTH_SHORT).show();

        }
    }



}