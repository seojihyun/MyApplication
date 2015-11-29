package com.example.seojihyun.myapplication;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends Activity {
    private Button saveBtn;
    private TextView dateTextView;
    private EditText editText1;
    private int yearPicked, monthPicked, dayPicked; /*선택한 날짜 - 오늘날짜로 초기화*/
    private DatePickerDialog datePicker;
    private DatePickerDialog.OnDateSetListener dateListener;
    private File diaryFile;
    private final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
    private final File myDir = new File( strSDpath , "/mydiary");
    private final CharSequence[] items = {"크게", "중간", "작게"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveBtn = (Button) findViewById(R.id.saveBtn);
        dateTextView = (TextView) findViewById(R.id.dateTextView);
        editText1 = (EditText) findViewById(R.id.editText1);

        /*오늘 날짜로 초기화*/
        Calendar calendar = Calendar.getInstance();
        yearPicked = calendar.get(Calendar.YEAR);
        monthPicked= calendar.get(Calendar.MONTH) + 1;
        dayPicked = calendar.get(Calendar.DAY_OF_MONTH);
        dateTextView.setText(String.format("%d년 %d월 %d일", yearPicked, monthPicked, dayPicked));

        /*myDiary 디렉토리 생성*/
        makeDirectory(myDir);

        /*오늘날짜에 해당하는 파일 존재시 read diary*/
        diaryFile = new File(myDir.getAbsolutePath(), getFileName(yearPicked, monthPicked, dayPicked));
        readDiary(diaryFile);

        /*DatePickerDialog 에 리스너 부착*/
        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                yearPicked = year;
                monthPicked = monthOfYear+1;
                dayPicked = dayOfMonth;
                dateTextView.setText(String.format("%d년 %d월 %d일", yearPicked, monthPicked, dayPicked));

                 /*파일이 이미 존재하는 경우 : 파일 내용 불러오기*/
                diaryFile = new File(myDir.getAbsolutePath(), getFileName(yearPicked, monthPicked, dayPicked));
                readDiary(diaryFile);
                //openDiary();
            }
        };

        /*TextView Listener 부착 - DatePicker 위젯열기 */
        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(MainActivity.this, dateListener, yearPicked, monthPicked-1, dayPicked);
                datePicker.show();
            }
        });
        /*저장 버튼에 리스너 부착 - 저장or수정*/
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*다이어리 파일 생성*/
                diaryFile = new File(myDir.getAbsolutePath(), getFileName(yearPicked, monthPicked, dayPicked));
                /*다이어리에 쓰기*/
                writeDiary(diaryFile);
            }//end onClick()
        });//end OnClickListener

    }
    /*메인화면 초기화*/
    public void init() {
        editText1.setText(null);
        editText1.setHint("일기 없음");
        saveBtn.setText("저장");
    }
    /*diaryFile 이름 설정*/
    public String getFileName(int year, int month, int day) {
        return String.format("%d년%d월%d일.txt", year, month, day);
    }
    /*일기 읽기*/
    public void readDiary(File diaryFile) {
        String diaryStr = null;
        FileInputStream inFs;
        if(diaryFile.exists()) {
            Toast.makeText(getApplicationContext(), diaryFile.getName()+ "파일 존재", Toast.LENGTH_SHORT).show();
            try {
                inFs = new FileInputStream(diaryFile);
                byte[] txt = new byte[500];
                inFs.read(txt);
                inFs.close();
                diaryStr = (new String(txt)).trim();
                saveBtn.setText("수정");
                /*edit Text 에 보여지기 */
                editText1.setText(diaryStr);
                saveBtn.setText("수정");

            } catch (IOException e) {
                editText1.setHint("일기 없음");
                saveBtn.setText("저장");
            }
        }
        else {
            init();
        }
    }
    /*일기 쓰기*/
    public void writeDiary(File diaryFile) {
        /*내용이 입력되지 않은경우 - 파일 생성 안함*/
        if(editText1.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "내용을 입력해주세요", Toast.LENGTH_SHORT).show();
        }
        else {
        /*내용 입력이 입력 된경우 - 파일 생성 & 파일 내용 입력*/
            try {
                makeFile(diaryFile);

                FileOutputStream outFs = new FileOutputStream(diaryFile);
                String str = editText1.getText().toString();
                outFs.write(str.getBytes());
                Toast.makeText(getApplicationContext(), diaryFile.getName() + "에 내용이 저장됨", Toast.LENGTH_SHORT).show();
                saveBtn.setText("수정");
                outFs.flush();
                outFs.close();

            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "writeDiary() 에러!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    /*myDiary 디렉토리 존재 여부 확인*/
    public boolean isDirExist(File dir) {
        if(dir.isDirectory() && dir.exists())
            return true;
        return false;
    }
    /* 디렉토리 생성*/
    public void makeDirectory(File dir) {
        //폴더가 존재하지 않을경우에만 폴더 생성
        if( !isDirExist(dir) ) {
            if(dir.mkdir()) { //폴더 생성 성공시
                Toast.makeText(getApplicationContext(), dir.getName() + "폴더 생성 성공", Toast.LENGTH_SHORT).show();
            }
            else { //폴더 생성 실패
                Toast.makeText(getApplicationContext(), dir.getName() + "폴더 생성 실패", Toast.LENGTH_SHORT).show();
            }
        }
    }
    /* 파일 생성*/
    public void makeFile(File file) {
        try {
            if (!file.exists()) {
                file.createNewFile();
                Toast.makeText(getApplicationContext(), file.getName() + "파일 생성 성공", Toast.LENGTH_SHORT).show();
            }
        }catch(IOException e) {
            Toast.makeText(getApplicationContext(), file.getName() + "파일 생성 실패", Toast.LENGTH_SHORT).show();
        }
    }
    /*파일 삭제 (설정된 날짜에 대한 파일)*/
    public void removeFile() {
        if(diaryFile.exists()) { //파일이 존재할경우 파일 삭제
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage(diaryFile.getName() + "파일을 지우시겠습니까?");
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (diaryFile.delete()) {
                        Toast.makeText(getApplicationContext(), "파일 삭제 성공", Toast.LENGTH_SHORT).show();
                        init();
                    }
                }
            });
            alertDialog.setNegativeButton("No", null);
            alertDialog.show();
        }
        else { //파일이 존재 하지 않는 경우
            Toast.makeText(getApplicationContext(),"지울 파일이 없습니다", Toast.LENGTH_SHORT).show();
        }
    }
    /*글자 크기 설정*/
    public void setTextSize() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("글자 크기 설정");
        alertDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        editText1.setTextSize(30);
                        break;
                    case 1:
                        editText1.setTextSize(20);
                        break;
                    case 2:
                        editText1.setTextSize(10);
                        break;
                }
            }
        });
        alertDialog.show();
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

        switch (id) {
             /* (1) 옵션 : 다시 읽기 */
            case R.id.action_readDiary:
                readDiary(diaryFile);
                break;
            /* (2) 옵션 : 일기 삭제 */
            case R.id.action_removeDiary:
                removeFile();
                break;
            /* (3) 옵션 : 글자 크기 설정 */
            case R.id.action_textSize:
                setTextSize();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}