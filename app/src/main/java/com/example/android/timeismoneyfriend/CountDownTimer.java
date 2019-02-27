package com.example.android.timeismoneyfriend;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import maes.tech.intentanim.CustomIntent;

public class CountDownTimer extends AppCompatActivity {

    TextView DisplayMinutes, timeLeft;
    android.os.CountDownTimer countDownTimer;
    Button cancel, resume;
    boolean timerRunning; //int initialSeconds; for the reset function
    long millisLeft;
    ProgressBar progressBar;
    private MediaPlayer mp;

    //might need to put it in onCreate

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        mp = MediaPlayer.create(this, R.raw.tuturu);
        sortingVariables();
        changeColorAndFont();
        startTimer();



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTimer();
                Intent intent = new Intent(CountDownTimer.this, CountDownTimerEntryy.class);
                startActivity(intent);
                CustomIntent.customType(CountDownTimer.this,"right-to-left");
            }
        });
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) {
                    resume.setText("resume");
                    pauseTimer();

                } else {
                    resume.setText("pause");
                    resumeTimer();
                }
            }
        });

    }

    public void startTimer() {
        final int milli = getIntent().getExtras().getInt("Seconds");
        progressBar.setProgress(0);
        progressBar.setMax((milli / 1000));
        countDownTimer = new android.os.CountDownTimer(milli, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                millisLeft = millisUntilFinished;


                DisplayMinutes.setText(millisLeft / 60000 + ":" + (millisLeft / 1000) % 60);

                 // we set the max of the progress bar to be our initial seconds



                    //after that the progress starts decreasing
                    progressBar.setProgress((int) (millisLeft / 1000), true);



            }

            @Override
            public void onFinish() {
                //example seconds = 70 display 10 as seconds seconds = 30 display 30 as seconds.
                DisplayMinutes.setText("Done!");
                timerRunning = false;
                timeLeft.setText("");
                progressBar.setProgress(0);
                mp.start();

            }
        }.start();
        timerRunning = true;
    }

    public void pauseTimer() {

        countDownTimer.cancel();


        timerRunning = false;


    }

    /**
     * public void resetTimer() {
     * <p>
     * DisplaySeconds.setText((initialSeconds / 1000) % 60 + ""); //example seconds = 70 display 10 as seconds seconds = 30 display 30 as seconds.
     * DisplayMinutes.setText(initialSeconds / 60000 + "");
     * countDownTimer.cancel();
     * resume.setText("Resume");
     * }
     **/
    public void resumeTimer() {
        /* so we save the milliseconds left from the start timer and use them on the resume to start at the time we left at */
        //and we make the milliseconds left = to the milliseconds left from this resumeTimer method so if we keep pausing and resuming it does not mess it up
        countDownTimer = new android.os.CountDownTimer(millisLeft, 1000) {
            @Override
            public void onTick(long timeLeft) {
                millisLeft = timeLeft;
                //example seconds = 70 display 10 as seconds seconds = 30 display 30 as seconds.
                DisplayMinutes.setText(timeLeft / 60000 + ":" + (timeLeft / 1000) % 60 + "");
                int progress = (int) (timeLeft / 1000); //after that the progress starts decreasing
                progressBar.setProgress(progress);
            }

            @Override
            public void onFinish() {
                //example seconds = 70 display 10 as seconds seconds = 30 display 30 as seconds.
                DisplayMinutes.setText("Done!");
                timeLeft.setVisibility(View.GONE);
                progressBar.setProgress(0);
                mp.start();
                //Both resumeTimer and startTimer should have the same onFinish since the user might have reset his timer or he may not have done it.
            }
        }.start();
        timerRunning = true;
    }

    public void changeColorAndFont(){

        Typeface myCustomBoldSans = Typeface.createFromAsset(getAssets(),"fonts/SourceSansPro-Bold.ttf");

        DisplayMinutes.setTextColor(Color.parseColor("#FFDA7E9A"));
        timeLeft.setTextColor(Color.parseColor("#FFDA7E9A"));

        DisplayMinutes.setTypeface(myCustomBoldSans);
        cancel.setTypeface(myCustomBoldSans);
        resume.setTypeface(myCustomBoldSans);
        timeLeft.setTypeface(myCustomBoldSans);
    }

    public void sortingVariables(){
        timeLeft = findViewById(R.id.timeLeft);
        resume = findViewById(R.id.resume);
        cancel = findViewById(R.id.cancel);

        DisplayMinutes = findViewById(R.id.DisplayMinutes);

        progressBar = findViewById(R.id.progressBar);
    }

}
