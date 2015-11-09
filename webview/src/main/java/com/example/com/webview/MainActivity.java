package com.example.com.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.*;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    EditText editUrl;
    Button btnMove, btnBack;
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUrl = (EditText) findViewById(R.id.EditUrl1);
        btnMove = (Button) findViewById(R.id.BtnMove);
        btnBack = (Button) findViewById(R.id.BtnBack);
        web = (WebView) findViewById(R.id.WebView1);
        WebViewClient client = new WebViewClient();
        ButtonListener btnlistener = new ButtonListener();
        web.setWebViewClient(client);
        btnMove.setOnClickListener(btnlistener);
        btnBack.setOnClickListener(btnlistener);
        WebSettings webset = web.getSettings();
        webset.setBuiltInZoomControls(true);
    }

    class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.BtnMove :
                    String str = editUrl.getText().toString();
                    boolean value = str.matches("http://");
                    if(value == true) {
                        web.loadUrl(str);
                        break;
                    }
                    else {
                        str = "http://" + str;
                        web.loadUrl(str);
                        editUrl.setText(str);
                        break;
                    }

                case R.id.BtnBack :
                    web.goBack();
                    break;
            }
        }
    }

    class WebViewClient extends android.webkit.WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            editUrl.setText(url);
            return false;
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
