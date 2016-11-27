package com.sds.study.projectlinecontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LineControlActivity extends AppCompatActivity{

    GuitarView guitarView;

    String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        TAG = this.getClass().getName() + "/Canet";

        guitarView = new GuitarView(this.getApplicationContext(), this);

        setContentView(guitarView);

    }

}
