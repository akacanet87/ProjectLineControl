package com.sds.study.projectlinecontrol;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Region;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by CANET on 2016-11-26.
 */

public class GuitarView extends View {

    Bitmap drum;
    Context context;
    Region drumRgn;

    HitTest hitTest;

    String TAG;

    int vWidth;
    int vHeight;
    int drumX;
    int drumY;

    int count;

    public GuitarView(Context context, Activity activity){

        super(context);
        this.context = context;
        TAG = this.getClass().getName() + "/Canet";

        this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        hitTest = new HitTest();

        /*winWidth = getScreenSize(activity).x;
        winHeight = getScreenSize(activity).y;

        vWidth = this.getWidth();
        vHeight = this.getHeight();

        Log.d( TAG, "높이 : "+winHeight+", 넓이 : "+winWidth); */
        //Log.d( TAG, "높이 : "+vHeight+", 넓이 : "+vWidth);

        drumX = 500;
        drumY = 300;

        drum = BitmapFactory.decodeResource(getResources(), R.drawable.drum);

        drumRgn = new Region();
        drumRgn = hitTest.getBitmapRegion(drum, drumX, drumY);

    }
/*
    public Point getScreenSize(Activity activity) {

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size;

    }*/

    @Override
    protected void onDraw(Canvas canvas){

        Log.d( TAG, "높이 : "+vHeight+", 넓이 : "+vWidth);

        Paint paint = new Paint();

        paint.setColor(Color.RED);

        canvas.drawBitmap(drum, drumX, drumY, null);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch(event.getAction()){

            case MotionEvent.ACTION_DOWN:

                Log.d( TAG, "x : "+x+", y : "+y);

                if( hitTest.isHit( x, y, drumRgn )){

                    count++;

                    Log.d(TAG, count+"번 닿았다");

                }

                break;

            case MotionEvent.ACTION_MOVE:

                Log.d( TAG, "x : "+x+", y : "+y);

                if( hitTest.isHit( x, y, drumRgn )){

                    count++;

                    Log.d(TAG, count+"번 닿았다");

                }

                break;

            case MotionEvent.ACTION_UP:

                break;

            case MotionEvent.ACTION_CANCEL:

                break;

        }

        return true;

    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus){

        vWidth = this.getWidth();
        vHeight = this.getHeight();

        super.onWindowFocusChanged(hasWindowFocus);

    }

}
