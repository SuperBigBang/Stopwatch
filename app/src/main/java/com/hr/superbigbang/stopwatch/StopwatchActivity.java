package com.hr.superbigbang.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class StopwatchActivity extends AppCompatActivity {
    private boolean running = false;
    private int seconds = 0;
    private boolean wasRunning = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if (savedInstanceState != null) {
            running = savedInstanceState.getBoolean("running");
            seconds = savedInstanceState.getInt("seconds");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (wasRunning) {
            running = true;
        }
        //  System.out.println("Стала видимой для пользователя");
    }

    @Override
    protected void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;
        //   System.out.println("Скрылся");
    }

    public void onClickStartb(View view) {
        running = true;
        CharSequence startclicktoast = "Собщение-уведомление (Таймер запущен)";
        int toastsequencestart = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, startclicktoast, toastsequencestart);
        toast.show();
    }

    public void onClickStopb(View view) {
        running = false;
    }

    public void onClickResetb(View view) {
        running = false;
        seconds = 0;
    }

    private void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                    //    System.out.println("still running");
                } //else System.out.println("waiting for start timer");
                handler.postDelayed(this, 1000);
            }
        });
    }
}
