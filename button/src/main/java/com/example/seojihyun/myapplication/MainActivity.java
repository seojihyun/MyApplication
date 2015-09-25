package com.example.seojihyun.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button [];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = new Button[4];

        button[0] = (Button)findViewById(R.id.button1); //네이트 홈페이지
        button[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.nate.com")));
            }
        });

        button[1] = (Button)findViewById(R.id.button2); //911 응급전화
        button[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:911")));
            }
        });

        button[2] = (Button) findViewById(R.id.button3);
        button[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //갤러리열기
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));
                startActivity(mIntent);
            }
        });

        button[3] = (Button) findViewById(R.id.button4);
        button[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //끝내기
                finish();
            }
        });
    }

}