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
    public int mMaxWidth = 10;
    public int mMaxHeight = 10;
    public int mMinWidth = 5;
    public int mMinHeight = 5;

    public int currentWidth = mMinWidth;
    public int currentHeight = mMinHeight;

    public Paint mPaint = new Paint();

    public Dot(int x, int y) {
        mPosX = x;
        mPosY = y;

        mPaint.setColor(Color.RED);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(mPosX, mPosY, currentWidth/2, mPaint);
    }
}
