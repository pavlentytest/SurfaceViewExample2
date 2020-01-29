package com.samsung.myitschool.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class TestSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    private float x, y, r;
    private boolean flag;

    public TestSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        new Thread() {
            @Override
            public void run() {
                //super.run();
                Paint p = new Paint();
                p.setColor(Color.YELLOW);
                while(true) {
                    Canvas canvas = getHolder().lockCanvas();
                    if(canvas != null) {
                        canvas.drawColor(Color.BLUE);
                        canvas.drawCircle(x,y,r,p);
                        // r += 5;
                        r += flag  ? 5 : 0;
                        getHolder().unlockCanvasAndPost(canvas);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        this.x = event.getX();
        this.y = event.getY();
        r = 0;
        this.flag = true;
        return true;
    }
}