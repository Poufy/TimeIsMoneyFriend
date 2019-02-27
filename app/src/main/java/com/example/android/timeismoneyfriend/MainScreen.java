package com.example.android.timeismoneyfriend;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainScreen extends AppCompatActivity {
Button timerButton;
Button CountDownTimerButton;
TextView mainTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        timerButton = findViewById(R.id.TimerButton);
        mainTitle = findViewById(R.id.textView2);
        setFont();
        CountDownTimerButton = findViewById(R.id.CountDownButton);
        CountDownTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, CountDownTimerEntryy.class);
                startActivity(intent);
            }
        });
    }
    private void setFont(){
       Typeface myKnevFont = Typeface.createFromAsset(getAssets(),"fonts/Knewave-Regular.ttf");
        mainTitle.setTypeface(myKnevFont);
    }
}
