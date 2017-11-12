package jp.techacademy.aso.muneki.aisatsuapp;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mTextView;
    EditText mEditText;

    final Calendar calendar = Calendar.getInstance();
    // カレンダーから現在の '時' を取得
    int mHour = calendar.get(Calendar.HOUR_OF_DAY);
    // カレンダーから現在の '分' を取得
    int mMinute = calendar.get(Calendar.MINUTE);
    TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);

        mTextView = (TextView) findViewById(R.id.textView);
        mEditText = (EditText) findViewById(R.id.editText);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button1) {
            mTextView.setText(mEditText.getText().toString());
        } else if (v.getId() == R.id.button2) {
            showTimePickerDialog();
        }
    }

    private void showTimePickerDialog() {
//        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
//                new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        Log.d("UI-PARTS", String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
//                    }
//                },
//                13, // 初期値（時間）
//                0,  // 初期値（分）
//                true);

        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mHour = hourOfDay; // '時' を取得
                mMinute = minute;  // '分' を取得
            }
        };

        timePickerDialog = new TimePickerDialog(
                this,     // 第1引数 : Context
                listener, // 第2引数 : TimePickerDialog.OnTimeSetListener
                mHour,    // 第3引数 : 時
                mMinute,  // 第4引数 : 分
                true      // 第5引数 : 24時間表示(true)かAM/PM表示(false)か
        );

        timePickerDialog.show();

        timePickerDialog.getContext();
        

        if (timePickerDialog>= "2:0" && timePickerDialog < "9:59") {
            mTextView.setText("おはよう");
        } else if (timePickerDialog >= "10:0" && timePickerDialog < "17:59") {
            mTextView.setText("こんにちは");
        } else if (timePickerDialog >= "18:0" || timePickerDialog < "1:59") {
            mTextView.setText("こんばんは");
        }
    }
}