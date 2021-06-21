package com.example.a2021_androidproject;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import java.io.FileInputStream;
import java.io.FileOutputStream;


public class CalendarActivity extends AppCompatActivity {


    public String fname=null;
    public String str1,str2,str3,str4,str_total=null;
    public CalendarView calendarView;
    public Button cha_Btn,del_Btn,save_Btn;
    public TextView diaryTextView, tv_rec_str, tv_header;
    public EditText contextEditText_Breakfast,contextEditText_Lunch,contextEditText_Dinner,contextEditText_Memo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendarView=findViewById(R.id.calendarView);
        diaryTextView=findViewById(R.id.diaryTextView);
        save_Btn=findViewById(R.id.save_Btn);
        del_Btn=findViewById(R.id.del_Btn);
        cha_Btn=findViewById(R.id.cha_Btn);
        tv_rec_str =findViewById(R.id.tv_rec_file);
        tv_header =findViewById(R.id.tv_cal_records);
        contextEditText_Breakfast=findViewById(R.id.contextEditText_Breakfast);
        contextEditText_Lunch=findViewById(R.id.contextEditText_Lunch);
        contextEditText_Dinner=findViewById(R.id.contextEditText_Dinner);
        contextEditText_Memo=findViewById(R.id.contextEditText_Memo);
        tv_header.setText("식단 기록표");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                diaryTextView.setVisibility(View.VISIBLE);
                save_Btn.setVisibility(View.VISIBLE);
                contextEditText_Breakfast.setVisibility(View.VISIBLE);
                contextEditText_Lunch.setVisibility(View.VISIBLE);
                contextEditText_Dinner.setVisibility(View.VISIBLE);
                contextEditText_Memo.setVisibility(View.VISIBLE);
                tv_rec_str.setVisibility(View.INVISIBLE);
                cha_Btn.setVisibility(View.INVISIBLE);
                del_Btn.setVisibility(View.INVISIBLE);
                diaryTextView.setText(String.format("%d / %d / %d",year,month+1,dayOfMonth));
                contextEditText_Breakfast.setText("");
                contextEditText_Lunch.setText("");
                contextEditText_Dinner.setText("");
                contextEditText_Memo.setText("");
                checkDay(year,month,dayOfMonth);
            }
        });
        save_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDiary(fname);
                str1=contextEditText_Breakfast.getText().toString();
                str2=contextEditText_Lunch.getText().toString();
                str3=contextEditText_Dinner.getText().toString();
                str4=contextEditText_Memo.getText().toString();
                str_total="아침 : "+str1+"\n"
                        +"점심 : "+str2+"\n"
                        +"저녁 : "+str3+"\n"+"\n"
                        +"메모 : "+str4+"\n";     //여기
                tv_rec_str.setText(str_total);
                save_Btn.setVisibility(View.INVISIBLE);
                cha_Btn.setVisibility(View.VISIBLE);
                del_Btn.setVisibility(View.VISIBLE);
                contextEditText_Breakfast.setVisibility(View.INVISIBLE);
                contextEditText_Lunch.setVisibility(View.INVISIBLE);
                contextEditText_Dinner.setVisibility(View.INVISIBLE);
                contextEditText_Memo.setVisibility(View.INVISIBLE); //여기
                tv_rec_str.setVisibility(View.VISIBLE);

            }
        });
    }

    public void  checkDay(int cYear,int cMonth,int cDay){
        fname=""+cYear+"-"+(cMonth+1)+""+"-"+cDay+".txt";//저장할 파일 이름설정
        FileInputStream fis=null;//FileStream fis 변수

        try{
            fis=openFileInput(fname);

            byte[] fileData=new byte[fis.available()];
            fis.read(fileData);
            fis.close();

            str_total=new String(fileData);

            contextEditText_Breakfast.setVisibility(View.INVISIBLE);
            contextEditText_Lunch.setVisibility(View.INVISIBLE);
            contextEditText_Dinner.setVisibility(View.INVISIBLE);
            contextEditText_Memo.setVisibility(View.INVISIBLE);        //여기
            tv_rec_str.setVisibility(View.VISIBLE);
            tv_rec_str.setText(str_total);

            save_Btn.setVisibility(View.INVISIBLE);
            cha_Btn.setVisibility(View.VISIBLE);
            del_Btn.setVisibility(View.VISIBLE);

            cha_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    contextEditText_Breakfast.setVisibility(View.VISIBLE);
                    contextEditText_Lunch.setVisibility(View.VISIBLE);
                    contextEditText_Dinner.setVisibility(View.VISIBLE);
                    contextEditText_Memo.setVisibility(View.VISIBLE);      //여기
                    tv_rec_str.setVisibility(View.INVISIBLE);
                    contextEditText_Breakfast.setText(str1);
                    contextEditText_Lunch.setText(str2);
                    contextEditText_Dinner.setText(str3);
                    contextEditText_Memo.setText(str4);                 //여기

                    save_Btn.setVisibility(View.VISIBLE);
                    cha_Btn.setVisibility(View.INVISIBLE);
                    del_Btn.setVisibility(View.INVISIBLE);
                    tv_rec_str.setText(contextEditText_Breakfast.getText());
                    str1=contextEditText_Breakfast.getText().toString();
                    str2=contextEditText_Lunch.getText().toString();
                    str3=contextEditText_Dinner.getText().toString();
                    str4=contextEditText_Memo.getText().toString();
                    str_total="아침 : "+str1+"\n"
                            +"점심 : "+str2+"\n"
                            +"저녁 : "+str3+"\n"+"\n"
                            +"메모 : "+str4+"\n";
                    tv_rec_str.setText(str_total);     //여기
                }

            });
            del_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv_rec_str.setVisibility(View.INVISIBLE);
                    contextEditText_Breakfast.setText("");
                    contextEditText_Lunch.setText("");
                    contextEditText_Dinner.setText("");
                    contextEditText_Memo.setText("");              //여기

                    contextEditText_Breakfast.setVisibility(View.VISIBLE);
                    contextEditText_Lunch.setVisibility(View.VISIBLE);
                    contextEditText_Dinner.setVisibility(View.VISIBLE);
                    contextEditText_Memo.setVisibility(View.VISIBLE);       //여기

                    save_Btn.setVisibility(View.VISIBLE);
                    cha_Btn.setVisibility(View.INVISIBLE);
                    del_Btn.setVisibility(View.INVISIBLE);
                    removeDiary(fname);
                }
            });
            if(tv_rec_str.getText()==null){
                tv_rec_str.setVisibility(View.INVISIBLE);
                diaryTextView.setVisibility(View.VISIBLE);
                save_Btn.setVisibility(View.VISIBLE);
                cha_Btn.setVisibility(View.INVISIBLE);
                del_Btn.setVisibility(View.INVISIBLE);
                contextEditText_Breakfast.setVisibility(View.VISIBLE);
                contextEditText_Lunch.setVisibility(View.VISIBLE);
                contextEditText_Dinner.setVisibility(View.VISIBLE);
                contextEditText_Memo.setVisibility(View.VISIBLE);       //여기
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @SuppressLint("WrongConstant")
    public void removeDiary(String readDay){
        FileOutputStream fos=null;

        try{
            fos=openFileOutput(readDay,MODE_NO_LOCALIZED_COLLATORS);
            String content="";
            fos.write((content).getBytes());
            fos.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @SuppressLint("WrongConstant")
    public void saveDiary(String readDay){
        FileOutputStream fos=null;

        try{
            fos=openFileOutput(readDay,MODE_NO_LOCALIZED_COLLATORS);

            str1=contextEditText_Breakfast.getText().toString();
            str2=contextEditText_Lunch.getText().toString();
            str3=contextEditText_Dinner.getText().toString();
            str4=contextEditText_Memo.getText().toString();
            str_total="아침 : "+str1+"\n"
                    +"점심 : "+str2+"\n"
                    +"저녁 : "+str3+"\n"+"\n"
                    +"메모 : "+str4+"\n";
            String content=str_total;          //여기
            fos.write((content).getBytes());
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}