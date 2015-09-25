package com.example.seojihyun.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit1, edit2;
    Button btnAdd, btnSub, btnMul, btnDiv, btnRem;
    TextView textResult;
    String num1, num2;
    Double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("초간단 계산기");

        edit1 = (EditText) findViewById(R.id.Edit1);
        edit2 = (EditText) findViewById(R.id.Edit2);
        btnAdd = (Button) findViewById(R.id.BtnAdd);
        btnSub = (Button) findViewById(R.id.BtnSub);
        btnMul = (Button) findViewById(R.id.BtnMul);
        btnDiv = (Button) findViewById(R.id.BtnDiv);
        btnRem = (Button) findViewById(R.id.BtnRem);
        textResult = (TextView) findViewById(R.id.TextResult);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    num1 = edit1.getText().toString();
                    num2 = edit2.getText().toString();
                    result = Double.parseDouble(num1) + Double.parseDouble(num2);
                    textResult.setText("계산 결과 : " + result.toString());
                }
                catch(java.lang.NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "숫자를 입력해주세요", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    num1 = edit1.getText().toString();
                    num2 = edit2.getText().toString();
                    result = Double.parseDouble(num1) - Double.parseDouble(num2);
                    textResult.setText("계산 결과 : " + result.toString());
                }
                catch(java.lang.NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "숫자를 입력해주세요", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    num1 = edit1.getText().toString();
                    num2 = edit2.getText().toString();
                    result = Double.parseDouble(num1) * Double.parseDouble(num2);
                    textResult.setText("계산 결과 : " + result.toString());
                }
                catch(java.lang.NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "숫자를 입력해주세요", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    num1 = edit1.getText().toString();
                    num2 = edit2.getText().toString();
                    if (Double.parseDouble(num2) == 0) {
                        Toast.makeText(MainActivity.this, "'0'을 입력하지 마세요", Toast.LENGTH_LONG).show();
                        textResult.setText("계산 결과 : null");
                    } else {
                        Double divResult = Double.parseDouble(num1) / Double.parseDouble(num2);
                        textResult.setText("계산 결과 : " + divResult.toString());
                    }
                }
                catch(java.lang.NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "숫자를 입력해주세요", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    num1 = edit1.getText().toString();
                    num2 = edit2.getText().toString();
                    if (Double.parseDouble(num2) == 0) {
                        Toast.makeText(MainActivity.this, "'0'을 입력하지 마세요", Toast.LENGTH_LONG).show();
                        textResult.setText("계산 결과 : null");
                    } else {
                        Double divResult = Double.parseDouble(num1) % Double.parseDouble(num2);
                        textResult.setText("계산 결과 : " + divResult.toString());
                    }
                }
                catch(java.lang.NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "숫자를 입력해주세요", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
