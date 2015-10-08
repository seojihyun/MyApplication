package com.example.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit1, edit2;
    Button btnAdd, btnSub, btnMul, btnDiv, btnRem;
    TextView textResult;
    String num1, num2;
    Double result;
    Button [] numButton;
    GridLayout gridLayout;
    CalcListener listener;

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

        gridLayout = (GridLayout) findViewById(R.id.Grid1);
        numButton = new Button [gridLayout.getChildCount()];
        listener = new CalcListener();

        for(int i=0; i<numButton.length; i++) {
            numButton[i] = (Button) gridLayout.getChildAt(i);
        }
        //버튼 리스너 부착
        for(int i=0; i<numButton.length; i++) {
            final int index;
            index = i;
            numButton[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (edit1.isFocused() == true) {
                        num1 = edit1.getText().toString() + numButton[index].getText().toString();
                        edit1.setText(num1);
                    } else if (edit2.isFocused() == true) {
                        num2 = edit2.getText().toString() + numButton[index].getText().toString();
                        edit2.setText(num2);
                    } else {
                        Toast.makeText(MainActivity.this, "EditText를 클릭해주세요", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        btnAdd.setOnClickListener(listener);
        btnSub.setOnClickListener(listener);
        btnMul.setOnClickListener(listener);
        btnDiv.setOnClickListener(listener);
        btnRem.setOnClickListener(listener);

    }
    class CalcListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                switch (v.getId()) {
                    case R.id.BtnAdd:
                        result = Double.parseDouble(num1) + Double.parseDouble(num2);
                        break;
                    case R.id.BtnSub:
                        result = Double.parseDouble(num1) - Double.parseDouble(num2);
                        break;
                    case R.id.BtnMul:
                        result = Double.parseDouble(num1) * Double.parseDouble(num2);
                        break;
                    case R.id.BtnDiv:
                        if (Double.parseDouble(num2) == 0) {
                            Toast.makeText(MainActivity.this, "'0'을 입력하지 마세요", Toast.LENGTH_LONG).show();
                            textResult.setText("계산 결과 : null");
                        }
                        else {
                            result = Double.parseDouble(num1) / Double.parseDouble(num2);
                        }
                        break;
                    case R.id.BtnRem:
                        result = Double.parseDouble(num1) % Double.parseDouble(num2);
                        break;
                }
                textResult.setText("계산 결과 : " + result.toString());

            }
            catch(java.lang.NumberFormatException e) {
                Toast.makeText(MainActivity.this, "숫자를 입력해주세요", Toast.LENGTH_LONG).show();
            }
        }
    }
}
