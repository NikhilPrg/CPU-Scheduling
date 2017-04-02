package com.kurama.nikhil.cpuscheduling;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Nikhil on 02/04/17.
 */
public class process_view_layout extends RelativeLayout{

    String process = "P1";
    int time = 0;

    public process_view_layout(Context context) {
        super(context);
        initializeViews(context);
    }

    public process_view_layout(Context context, String p, int t) {
        super(context);
        initializeViews(context);
        process = p;
        time = t;
    }

    public process_view_layout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public process_view_layout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeViews(context);
    }

    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.process_view_layout, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setProcessNameAndTime();
        super.setBackgroundResource(R.drawable.square);
    }

    public void setProcessNameAndTime() {
        TextView pro = (TextView) findViewById(R.id.temp_textViewProcess);
        TextView tim = (TextView) findViewById(R.id.temp_textViewTime);

        pro.setText(process);

        tim.setText(Integer.toString(time));

        super.setBackgroundResource(R.drawable.square);
    }

    public void setProcessNameAndTime(String n, int t) {
        process = n;
        time = t;
        setProcessNameAndTime();
    }

    public void subTime() {
        if(time > 1) {
            time--;
            TextView tim = (TextView) findViewById(R.id.temp_textViewTime);
            tim.setText(Integer.toString(time));
        } else {
            TextView tim = (TextView) findViewById(R.id.temp_textViewTime);
            tim.setText("Done");
        }
    }
}
