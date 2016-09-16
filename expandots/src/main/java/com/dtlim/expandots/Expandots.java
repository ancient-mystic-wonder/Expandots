package com.dtlim.expandots;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dale on 9/13/16.
 */
public class Expandots extends View {

    List<Dot> mDots = new ArrayList<>();
    List<ValueAnimator> mValueAnimators = new ArrayList<>();
    private int mDotsCount = 2;
    private float mMaxScale = 100;
    private float mMinScale = 0;
    private int mDuration = 1400;
    private int mNextStartDelay = mDuration/2;
    private int mDotsColor = 0;

    public Expandots(Context context) {
        super(context);
        initialize();
    }

    public Expandots(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributes(attrs);
        initialize();
    }

    public Expandots(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttributes(attrs);
        initialize();
    }

    public void initialize() {
        mDots = new ArrayList<>();
        mValueAnimators = new ArrayList<>();

        for(int i=0; i<mDotsCount; i++) {
            final Dot dot = new Dot((int) mMaxScale*(i+1), (int) mMaxScale, mDotsColor);

            mDots.add(dot);

            ValueAnimator animator = ValueAnimator.ofFloat(mMinScale, mMaxScale, mMinScale);
            animator.setDuration(mDuration);
            mValueAnimators.add(animator);

            final int index = i;
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float scale = (float)(valueAnimator.getAnimatedValue());

                    if(valueAnimator.getCurrentPlayTime() >= mNextStartDelay) {
                        doNextAnimation(index);
                    }

                    dot.setCurrentWidth(scale);
                    dot.setCurrentHeight(scale);
                    invalidate();
                }
            });
        }
        startAt(0);
    }

    public void startAt(int index) {
        mValueAnimators.get(index).start();
    }

    private void setAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.Expandots, 0, 0);
        try {
            mMinScale = a.getDimension(R.styleable.Expandots_minWidth, 0);
            mMaxScale = a.getDimension(R.styleable.Expandots_maxWidth, 100);
            mDuration = a.getInt(R.styleable.Expandots_duration, 1400);
            mNextStartDelay = a.getInt(R.styleable.Expandots_nextStartDelay, mDuration/2);
            mDotsCount = a.getInt(R.styleable.Expandots_count, 2);
            mDotsColor = a.getColor(R.styleable.Expandots_color, 0xFFFF0000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            a.recycle();
        }
    }

    private void doNextAnimation(int index) {
        ValueAnimator animator;
        if(index >= mValueAnimators.size()-1) {
            animator = mValueAnimators.get(0);
        }
        else {
            animator = mValueAnimators.get(index + 1);
        }
        if(!animator.isStarted()) {
            animator.start();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(Dot dot : mDots) {
            dot.draw(canvas);
        }
    }
}
