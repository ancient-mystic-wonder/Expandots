package com.dtlim.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import com.dtlim.expandots.Expandots;

public class MainActivity extends AppCompatActivity {

    Expandots mExpandots;

    SeekBar mSeekbarCount;
    SeekBar mSeekbarMinWidth;
    SeekBar mSeekbarMaxWidth;
    SeekBar mSeekbarDuration;
    SeekBar mSeekbarNextStartDelay;

    TextView mTextViewCount;
    TextView mTextViewMinWidth;
    TextView mTextViewMaxWidth;
    TextView mTextViewDuration;
    TextView mTextViewNextStartDelay;

    CheckBox mCheckBoxWaitUntilFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mExpandots = (Expandots) findViewById(R.id.expandots);
        mSeekbarCount = (SeekBar) findViewById(R.id.seekbar_count);
        mSeekbarMinWidth = (SeekBar) findViewById(R.id.seekbar_min_width);
        mSeekbarMaxWidth = (SeekBar) findViewById(R.id.seekbar_max_width);
        mSeekbarDuration = (SeekBar) findViewById(R.id.seekbar_duration);
        mSeekbarNextStartDelay = (SeekBar) findViewById(R.id.seekbar_next_start_delay);

        mTextViewCount = (TextView) findViewById(R.id.textview_count);
        mTextViewMinWidth = (TextView) findViewById(R.id.textview_min_width);
        mTextViewMaxWidth = (TextView) findViewById(R.id.textview_max_width);
        mTextViewDuration = (TextView) findViewById(R.id.textview_duration);
        mTextViewNextStartDelay = (TextView) findViewById(R.id.textview_next_start_delay);

        mCheckBoxWaitUntilFinish = (CheckBox) findViewById(R.id.checkbox_wait_until_finish);

        initializeListeners();
        initializeValues();
    }

    private void initializeValues() {
        mSeekbarCount.setProgress(mExpandots.getCount());
        mSeekbarMinWidth.setProgress((int) mExpandots.getMinWidth());
        mSeekbarMaxWidth.setProgress((int) mExpandots.getMaxWidth());
        mSeekbarDuration.setProgress(mExpandots.getDuration());
        mSeekbarNextStartDelay.setProgress(mExpandots.getNextStartDelay());
        mCheckBoxWaitUntilFinish.setChecked(mExpandots.getWaitUntilFinish());
    }

    private void initializeListeners() {
        mSeekbarCount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mExpandots.setCount(i+1);
                mTextViewCount.setText("" + (i+1));
                mExpandots.restart();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSeekbarMinWidth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mExpandots.setMinWidth(i);
                mTextViewMinWidth.setText("" + i + " px");
                mExpandots.restart();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSeekbarMaxWidth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mExpandots.setMaxWidth(i);
                mTextViewMaxWidth.setText("" + i + " px");
                mExpandots.restart();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSeekbarDuration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mExpandots.setDuration(i);
                mExpandots.setNextStartDelay(i/2);
                if(mSeekbarNextStartDelay.getProgress() > i) {
                    mSeekbarNextStartDelay.setProgress(i);
                }
                mSeekbarNextStartDelay.setMax(i);
                mTextViewDuration.setText("" + i + " ms");
                mExpandots.restart();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSeekbarNextStartDelay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mExpandots.setNextStartDelay(i);
                mTextViewNextStartDelay.setText("" + i + " ms");
                mExpandots.restart();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mCheckBoxWaitUntilFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCheckBoxWaitUntilFinish.isChecked()) {
                    mExpandots.setWaitUntilFinish(true);
                }
                else {
                    mExpandots.setWaitUntilFinish(false);
                }
                mExpandots.restart();
            }
        });

    }

}
