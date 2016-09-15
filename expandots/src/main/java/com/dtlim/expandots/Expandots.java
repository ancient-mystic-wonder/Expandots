package com.dtlim.expandots;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
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
    private int mMaxScale = 100;
    private int mMinScale = 0;
    private int mDuration = 1400;
    private int mWaitingDuration = mDuration/2;

    public Expandots(Context context) {
        super(context);
        initialize();
    }

    public Expandots(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public Expandots(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    public void initialize() {
        mDots = new ArrayList<>();
        mValueAnimators = new ArrayList<>();

        for(int i=0; i<mDotsCount; i++) {
            final Dot dot = new Dot(mMaxScale*(i+1), mMaxScale);
            mDots.add(dot);

            ValueAnimator animator = ValueAnimator.ofInt(mMinScale, mMaxScale, mMinScale);
            animator.setDuration(mDuration);
            mValueAnimators.add(animator);

            final int index = i;
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int scale = (int)(valueAnimator.getAnimatedValue());
                    if(index==0) {
                        Log.d("ANIMATION", "onAnimationUpdate: " + valueAnimator.getCurrentPlayTime());
                    }
                    if(valueAnimator.getCurrentPlayTime() >= mWaitingDuration) {
                        doNextAnimation(index);
                    }
                    dot.currentWidth = scale;
                    dot.currentHeight = scale;
                    invalidate();
                }
            });
        }
        mValueAnimators.get(0).start();
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
            Log.d("ANIMATION", "doNextAnimation: " + index + 1);
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
