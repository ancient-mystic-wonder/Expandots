package com.dtlim.expandots;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by dale on 9/13/16.
 */
public class Dot {

    public int mPosX;
    public int mPosY;

    public float mCurrentWidth;
    public float mCurrentHeight;

    public Paint mPaint = new Paint();

    public Dot(int x, int y) {
        mPosX = x;
        mPosY = y;
        mPaint.setColor(Color.RED);
    }

    public Dot(int x, int y, int color) {
        mPosX = x;
        mPosY = y;
        mPaint.setColor(color);
    }

    public void setCurrentWidth(float currentWidth) {
        mCurrentWidth = currentWidth;
    }

    public void setCurrentHeight(float currentHeight) {
        mCurrentHeight = currentHeight;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(mPosX, mPosY, mCurrentWidth /2, mPaint);
    }
}
