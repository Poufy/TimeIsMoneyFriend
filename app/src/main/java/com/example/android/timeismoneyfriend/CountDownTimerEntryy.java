package com.example.android.timeismoneyfriend;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import maes.tech.intentanim.CustomIntent;

public class CountDownTimerEntryy extends AppCompatActivity {
    Button switchActivity;
    EditText EditSeconds;
    EditText EditMinutes;
    int milliseconds;
    int minutesToMilli;
    TextView countDown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditSeconds = findViewById(R.id.EditTextSeconds);
        EditMinutes = findViewById(R.id.EditTextMinutes);
        countDown = findViewById(R.id.countDown);
        changeColorsAndFonts();
        switchActivity = findViewById(R.id.switchActivity);
        switchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CountDownTimerEntryy.this, CountDownTimer.class);
                intent.putExtra("Seconds", getTime());

                startActivity(intent);
                CustomIntent.customType(CountDownTimerEntryy.this,"left-to-right");
            }
        });
    }

    public int getTime() {
        if (EditMinutes.getText().toString().isEmpty()) {
            minutesToMilli = 0;

        }else{
            minutesToMilli = Integer.parseInt(EditMinutes.getText().toString());
        }
        if(EditSeconds.getText().toString().isEmpty()){
            milliseconds = 0;
        }else{
            milliseconds = Integer.parseInt(EditSeconds.getText().toString());
        }



        return (milliseconds * 1000) + (minutesToMilli * 60000);

    }

    public void changeColorsAndFonts(){
        Typeface myKnevFont = Typeface.createFromAsset(getAssets(),"fonts/Knewave-Regular.ttf");
        countDown.setTypeface(myKnevFont);


    }
}
