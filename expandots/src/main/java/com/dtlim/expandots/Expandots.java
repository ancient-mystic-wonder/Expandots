package com.dtlim.expandots;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dale on 9/13/16.
 */
public class Expandots extends View {

    List<Dot> mDots = new ArrayList<>();
    List<ValueAnimator> mValueAnimators = new ArrayList<>();

    private int mDotsCount;
    private float mMaxWidth;
    private float mMinWidth;
    private int mDuration;
    private int mNextStartDelay;
    private int mDotsColor;
    private boolean mWaitUntilFinish;

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
            addNewDot(i);
            addNewValueAnimator(i);
        }
        setViewParams();
        startAt(0);
    }

    private void addNewDot(int index) {
        Dot dot = new Dot((int) (mMaxWidth*index + mMaxWidth /2),
                (int) (mMaxWidth - mMaxWidth /2),
                mDotsColor);
        dot.setCurrentWidth(mMinWidth);
        dot.setCurrentHeight(mMinWidth);
        mDots.add(dot);
    }

    private void addNewValueAnimator(final int index) {
        ValueAnimator animator = ValueAnimator.ofFloat(mMinWidth, mMaxWidth, mMinWidth);
        animator.setDuration(mDuration);
        mValueAnimators.add(animator);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float scale = (float)(valueAnimator.getAnimatedValue());

                if(!mWaitUntilFinish) {
                    if(valueAnimator.getCurrentPlayTime() >= mNextStartDelay) {
                        doNextAnimation(index);
                    }
                }

                else {
                    if(index < mValueAnimators.size()-1 &&
                            valueAnimator.getCurrentPlayTime() >= mNextStartDelay) {
                        doNextAnimation(index);
                    }
                    else if(index >= mValueAnimators.size()-1 &&
                            valueAnimator.getCurrentPlayTime() >= valueAnimator.getDuration()) {
                        doNextAnimation(index);
                    }
                }

                Dot dot = mDots.get(index);
                dot.setCurrentWidth(scale);
                dot.setCurrentHeight(scale);
                invalidate();
            }
        });
    }

    private void setViewParams() {
        if(getLayoutParams() != null) {
            getLayoutParams().width = (int) (mDotsCount * mMaxWidth);
            getLayoutParams().height = (int) mMaxWidth;
            requestLayout();
            invalidate();
        }
    }

    private void setAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.Expandots, 0, 0);
        try {
            mMinWidth = a.getDimension(R.styleable.Expandots_minWidth, 0);
            mMaxWidth = a.getDimension(R.styleable.Expandots_maxWidth, 100);
            mDuration = a.getInt(R.styleable.Expandots_duration, 1400);
            mNextStartDelay = a.getInt(R.styleable.Expandots_nextStartDelay, mDuration/2);
            mDotsCount = a.getInt(R.styleable.Expandots_count, 2);
            mDotsColor = a.getColor(R.styleable.Expandots_color, 0xFFFF0000);
            mWaitUntilFinish = a.getBoolean(R.styleable.Expandots_waitUntilFinish, false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            a.recycle();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setViewParams();
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

    public void setCount(int count) {
        this.mDotsCount = count;
    }

    public void setMinWidth(float width) {
        this.mMinWidth = width;
    }

    public void setMaxWidth(float maxWidth) {
        this.mMaxWidth = maxWidth;
    }

    public void setDuration(int duration) {
        this.mDuration = duration;
    }

    public void setNextStartDelay(int nextStartDelay) {
        this.mNextStartDelay = nextStartDelay;
    }

    public void setDotsColor(int dotsColor) {
        this.mDotsColor = dotsColor;
    }

    public void setWaitUntilFinish(boolean waitUntilFinish) {
        this.mWaitUntilFinish = waitUntilFinish;
    }

    public int getCount() {
        return mDotsCount;
    }

    public float getMaxWidth() {
        return mMaxWidth;
    }

    public float getMinWidth() {
        return mMinWidth;
    }

    public int getDuration() {
        return mDuration;
    }

    public int getNextStartDelay() {
        return mNextStartDelay;
    }

    public int getDotsColor() {
        return mDotsColor;
    }

    public boolean getWaitUntilFinish() {
        return mWaitUntilFinish;
    }

    public void startAt(int index) {
        mValueAnimators.get(index).start();
    }

    public void restart() {
        for(ValueAnimator animator : mValueAnimators) {
            animator.removeAllUpdateListeners();
            animator.cancel();
            animator.end();
        }
        initialize();
    }
}
