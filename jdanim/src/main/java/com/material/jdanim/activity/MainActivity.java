package com.material.jdanim.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

import com.material.jdanim.R;
import com.material.jdanim.view.AcceptView;

public class MainActivity extends Activity {

    private SeekBar sb;
    private AcceptView av;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public  void initView(){
        sb = (SeekBar) findViewById(R.id.seekBar);
        av = (AcceptView) findViewById(R.id.accept_view);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i("seekBar.getProgress()",seekBar.getProgress()+"  "+ seekBar.getMax());
                float  currentProgress = (float) seekBar.getProgress() / (float) seekBar.getMax();
                av.setCurrentProgress(currentProgress);
                av.invalidate();
            }
        });
    }

}
