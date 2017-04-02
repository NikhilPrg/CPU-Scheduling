package com.kurama.nikhil.cpuscheduling;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/*
    NOTES :
    Process View IDS start from 1000, 1001 and so on..

    DONE:
    Implemented Main Clock
    Implemented Compound View process_view_layout
    Implemented Class Process
    Implemented ArrayList to store Process
    Implemented Function to display different processes

    TO DO:
    Limit number of entries based on screen size
    Write functions to determine 'runningProcess' after each second to change its value
    Write various algorithms to detemine which process will run
    Cleanup UI
    Implement better design
    Create Database
    Store Default Values of some processes in DB
    Allow user to edit default values ( Allow multiple preset saves ?)

 */

public class MainActivity extends Activity {

    TextView timerTextView;
    long startTime = 0;
    ArrayList<Process> processesList;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            timerTextView.setText(String.format("%d:%02d", minutes, seconds));
            timerHandler.postDelayed(this, 1000);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = (TextView) findViewById(R.id.timer_tv);

        Button b = (Button) findViewById(R.id.button);
        b.setText("start");
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                if (b.getText().equals("stop")) {
                    timerHandler.removeCallbacks(timerRunnable);
                    b.setText("start");
                } else {
                    startTime = System.currentTimeMillis();
                    timerHandler.postDelayed(timerRunnable, 0);
                    b.setText("stop");
                }
            }
        });
        createTempProcesses();
        createProcessViews();
    }

    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
        Button b = (Button)findViewById(R.id.button);
        b.setText("start");
    }

    private void createTempProcesses(){
        processesList = new ArrayList<Process>();
        processesList.add(new Process("P0", 10 , 4));
        processesList.add(new Process("P1", 5 , 2));
        processesList.add(new Process("P2", 6 , 3));
        processesList.add(new Process("P3", 3 , 2));
        processesList.add(new Process("P4", 7 , 1));
        processesList.add(new Process("P5", 4 , 1));
        processesList.add(new Process("P6", 3 , 4));
        processesList.add(new Process("P7", 5 , 6));
        processesList.add(new Process("P8", 2 , 2));

    }

    public void createProcessViews(){
        //Calculating Number of Rows and Columns to fit in screen
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int processesPerRow = width/300;
        int total = processesList.size();
        int y = total/processesPerRow;
        int numberOfRows = (y > 0) ? y : 1;
        int processesInLastRow = total%processesPerRow;

        final LinearLayout outerLayout = (LinearLayout) findViewById(R.id.linerLayout1);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300,300);
        params.setMargins(5,5,5,5);
        //Deleting previous entries
        if(outerLayout.getChildCount() > 0) {
            outerLayout.removeAllViews();
        }
        int k = 0;
        for(int j = 0; j < numberOfRows; j++) {
            LinearLayout innerLayout = (LinearLayout)new LinearLayout(this);
            innerLayout.setOrientation(LinearLayout.HORIZONTAL);
            for(int i = 0; i < processesPerRow; i++) {
                Process p = processesList.get(k);
                k++;
                process_view_layout tempP = new process_view_layout(this);
                tempP.setMinimumHeight(100);
                tempP.setMinimumWidth(100);
                tempP.setLayoutParams(params);
                tempP.setProcessNameAndTime(p.getName(),p.getBurstTime());
                tempP.setId(1000+k);
                innerLayout.addView(tempP);
            }
            outerLayout.addView(innerLayout);
        }
        //Last Row
        LinearLayout innerLayout = (LinearLayout)new LinearLayout(this);
        innerLayout.setOrientation(LinearLayout.HORIZONTAL);
        for(int i = 0; i < processesInLastRow; i++) {
            Process p = processesList.get(k);
            k++;
            process_view_layout tempP = new process_view_layout(this);
            tempP.setMinimumHeight(100);
            tempP.setMinimumWidth(100);
            tempP.setLayoutParams(params);
            tempP.setProcessNameAndTime(p.getName(),p.getBurstTime());
            innerLayout.addView(tempP);
        }
        outerLayout.addView(innerLayout);
    }


    public void add(View view) {
        processesList.add(new Process("P9", 69, 10));
        createProcessViews();
    }
}