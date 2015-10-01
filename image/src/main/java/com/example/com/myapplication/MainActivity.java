package com.example.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Switch switch1;
    TextView textView1;
    RadioGroup radioGroup1;
    RadioButton radioButton1, radioButton2, radioButton3;
    ImageView imageView1;
    Button button1, button2;
    RadioButtonListner radioButtonListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch1 = (Switch)findViewById(R.id.Switch1);
        textView1 = (TextView)findViewById(R.id.Text2);
        radioGroup1 = (RadioGroup)findViewById(R.id.RadioGroup1);
        radioButton1 = (RadioButton)findViewById(R.id.Option1);
        radioButton2 = (RadioButton)findViewById(R.id.Option2);
        radioButton3 = (RadioButton)findViewById(R.id.Option3);
        imageView1 = (ImageView)findViewById(R.id.Image);
        button1 = (Button)findViewById(R.id.Button1);
        button2 = (Button)findViewById(R.id.Button2);
        radioButtonListner = new RadioButtonListner();

        switch1.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textView1.setVisibility(View.VISIBLE);
                    radioGroup1.setVisibility(View.VISIBLE);
                    radioButton1.setVisibility(View.VISIBLE);
                    radioButton2.setVisibility(View.VISIBLE);
                    radioButton3.setVisibility(View.VISIBLE);
                    imageView1.setVisibility(View.VISIBLE);
                    button1.setVisibility(View.VISIBLE);
                    button2.setVisibility(View.VISIBLE);
                } else {
                    textView1.setVisibility(View.INVISIBLE);
                    radioGroup1.setVisibility(View.INVISIBLE);
                    radioButton1.setVisibility(View.INVISIBLE);
                    radioButton2.setVisibility(View.INVISIBLE);
                    radioButton3.setVisibility(View.INVISIBLE);
                    imageView1.setVisibility(View.INVISIBLE);
                    button1.setVisibility(View.INVISIBLE);
                    button2.setVisibility(View.INVISIBLE);
                }
            }
        });

        radioButton1.setOnClickListener(radioButtonListner);
        radioButton2.setOnClickListener(radioButtonListner);
        radioButton3.setOnClickListener(radioButtonListner);
        button1.setOnClickListener(radioButtonListner);
        button2.setOnClickListener(radioButtonListner);


    }

    class RadioButtonListner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.Option1: //젤리빈
                    imageView1.setImageResource(R.drawable.jellybean);
                    break;
                case R.id.Option2: //킷캣
                    imageView1.setImageResource(R.drawable.kitkat);
                    break;
                case R.id.Option3: //롤리팝
                    imageView1.setImageResource(R.drawable.lollipop);
                    break;
                case R.id.Button1: //종료
                    System.exit(0);
                    break;
                case R.id.Button2: //처음으로
                    switch1.setChecked(false);
                    break;
            }
        }
    }


}
