package org.techtown.scheduledetail2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddSchedule extends AppCompatActivity {
    final int START=1;
    final int END=2;

    String title;
    String contents;
    String start_location;
    String end_location;
    String startDate;
    String startTime;
    String endDate;
    String endTime;

    EditText titleText;
    EditText editTextContents;
    EditText editTextStartLocation;
    EditText editTextEndLocation;
    TextView textViewStartDate;
    TextView textViewStartTime;
    TextView textViewEndDate;
    TextView textViewEndTime;

    private ScheduleDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_schedule);

        // 현재 날짜, 시간 보여주기
        textViewStartDate = (TextView) findViewById(R.id.tv_start_date);
        textViewStartTime=(TextView)findViewById(R.id.tv_start_time);
        textViewEndDate = (TextView) findViewById(R.id.tv_end_date);
        textViewEndTime=(TextView)findViewById(R.id.tv_end_time);
        Date currentTime = Calendar.getInstance().getTime();
        String today = new SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault()).format(currentTime);
        String time=new SimpleDateFormat("HH:mm").format(currentTime);
        textViewStartDate.setText(today);
        textViewStartTime.setText(time);
        textViewEndDate.setText(today);
        textViewEndTime.setText(time);

        // 시작 날짜와 끝 날짜 초기화
        // to-do: today 형식 맞춰서 초기화하기
        startDate=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(currentTime);
        startTime=new SimpleDateFormat("HH:mm").format(currentTime);
        endDate=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(currentTime);
        endTime=new SimpleDateFormat("HH:mm").format(currentTime);

        textViewStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate(START);
            }
        });
        textViewEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate(END);
            }
        });
        textViewStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTime(START);
            }
        });
        textViewEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTime(END);
            }
        });

    }

//    // 액션바
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return true;
//    }

    // 달력 창 띄우기 -날짜선택
    void showDate(final int state) {
        final Calendar cal=Calendar.getInstance();

        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                switch(state){
                    case START:
                        textViewStartDate.setText(year + "년 " + (month+1) + "월 " + dayOfMonth + "일");
                        startDate=year+"-"+(month+1)+"-"+dayOfMonth;
                        Log.e("startDate: ", startDate);
                        break;
                    case END:
                        textViewEndDate.setText(year + "년 " + (month+1) + "월 " + dayOfMonth + "일");
                        endDate=year+"-"+(month+1)+"-"+dayOfMonth;
                        break;
                }


            }
        },cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

        datePickerDialog.setMessage("메시지");
        datePickerDialog.show();

    }

    // 시계 창 띄우기-시각 선택
    public void showTime(final int state){

        final TimePickerDialog timePickerDialog= new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        switch(state){
                            case START:
                                startTime=hourOfDay+":"+minute;
                                textViewStartTime.setText(hourOfDay+"시 "+minute+"분");
                                break;
                            case END:
                                endTime=hourOfDay+":"+minute;
                                textViewEndTime.setText(hourOfDay+"시 "+minute+"분");
                                break;
                        }
                    }
                }, 0,0,true);

        timePickerDialog.show();
    }

    // 일정 추가 버튼 눌렀을 때
    public void addSchedule(View view){
        Toast.makeText(this, "일정 추가 버튼 눌림", Toast.LENGTH_SHORT).show();
        Log.e("confirm", "일정 추가 버튼 눌림");

        titleText=(EditText)findViewById(R.id.et_title);
        editTextContents=(EditText)findViewById(R.id.et_contents);
        editTextStartLocation=(EditText)findViewById(R.id.start_location);
        editTextEndLocation=(EditText)findViewById(R.id.end_location);

        // 제목, 내용, 장소 중 한 가지가 비어있을 때
        if((titleText==null)||(editTextStartLocation==null) || (editTextEndLocation==null)){
            Toast.makeText(this, "항목을 모두 작성해주세요", Toast.LENGTH_SHORT).show();

        } else {

            title=titleText.getText().toString();
            contents=editTextContents.getText().toString();
            start_location=editTextStartLocation.getText().toString();
            end_location=editTextEndLocation.getText().toString();

            Log.e("title", title);
            Log.e("contents", contents);
            Log.e("location", start_location);

            dbHelper=new ScheduleDBHelper(AddSchedule.this, "Scheduler.db", null, 1);
            dbHelper.insertRecord(title, contents, start_location, end_location, startDate, startTime, endDate, endTime);

            //printTable();            // 들어간 데이터 확인용
        }
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}

class ScheduleDBHelper extends SQLiteOpenHelper {

    public ScheduleDBHelper(Context context){
        super(context, "Scheduler.db", null,1);
    }

    public ScheduleDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScheduleForm.ScheduleEntry.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ScheduleForm.ScheduleEntry.SQL_DELETE_TABLE);
        onCreate(db);
    }

    public void insertRecord(String title, String content, String start_location, String end_location,String startDate, String startTime, String endDate, String endTime){

        Log.e("insertRecord confirm", "1");

        // 읽기 전용 DB를 가져온다
        SQLiteDatabase db = getReadableDatabase();

        Log.e("insertRecord confirm", "읽기전용 DB 가져옴");

        // column name을 key로 테이블에 삽입할 값의 집합을 생성한다.
        ContentValues values = new ContentValues();

        Log.e("insertRecord confirm", "값의 집합 생성");

        values.put(ScheduleForm.ScheduleEntry.COLUMN_TITLE, title);
        values.put(ScheduleForm.ScheduleEntry.COLUMN_CONTENTS, content);
        values.put(ScheduleForm.ScheduleEntry.COLUMN_START_LOCATION, start_location);
        values.put(ScheduleForm.ScheduleEntry.COLUMN_END_LOCATION, end_location);
        values.put(ScheduleForm.ScheduleEntry.COLUMN_START_DATE, startDate);
        values.put(ScheduleForm.ScheduleEntry.COLUMN_START_TIME, startTime);
        values.put(ScheduleForm.ScheduleEntry.COLUMN_END_DATE, endDate);
        values.put(ScheduleForm.ScheduleEntry.COLUMN_END_TIME, endTime);

        Log.e("insertRecord confirm", "value 값 초기화 ");

        db.insert(ScheduleForm.ScheduleEntry.TABLE_NAME, null, values); //에러

        Log.e("insertRecord confirm", "insert 완료");
    }

    public Cursor readRecordOrderByAge() {

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                ScheduleForm.ScheduleEntry.COLUMN_TITLE,
                ScheduleForm.ScheduleEntry.COLUMN_CONTENTS,
                ScheduleForm.ScheduleEntry.COLUMN_START_LOCATION,
                ScheduleForm.ScheduleEntry.COLUMN_END_LOCATION,
                ScheduleForm.ScheduleEntry.COLUMN_START_DATE,
                ScheduleForm.ScheduleEntry.COLUMN_START_TIME,
                ScheduleForm.ScheduleEntry.COLUMN_END_DATE,
                ScheduleForm.ScheduleEntry.COLUMN_END_TIME
        };

        String sortOrder = ScheduleForm.ScheduleEntry.COLUMN_START_DATE;

        Cursor cursor = db.query(
                ScheduleForm.ScheduleEntry.TABLE_NAME,
                projection,   // 값을 가져올 column name의 배열
                null,   // where 문에 필요한 column
                null,   // where 문에 필요한 value
                null,   // group by를 적용할 column
                null,   // having 절
                sortOrder   // 정렬 방식
        );

        return cursor;
    }
}
