package com.dtlim.expandots;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dale on 9/13/16.
 */
public class Expandots extends View {

    List<Dot> mDots = new ArrayList<Dot>();
    private int mDotsCount = 5;
    private int mMaxScale = 100;
    private int mMinScale = 10;
    private int mDuration = 600;
    private int mWaitForAll = mDuration*(mDotsCount-1);


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

        for(int i=0; i<mDotsCount; i++) {
            final Dot dot = new Dot(mMaxScale*i+1, mMaxScale);
            mDots.add(dot);
            ValueAnimator animator = ValueAnimator.ofInt(mMinScale, mMaxScale, mMinScale);
            animator.setDuration(mDuration);
            animator.setStartDelay(i*mDuration);
//            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int scale = (int)(valueAnimator.getAnimatedValue());
                    Log.d("ANIM", "onAnimationUpdate: " + scale);
                    dot.currentWidth = scale;
                    dot.currentHeight = scale;
                    invalidate();
                }
            });
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    animator.setStartDelay(mWaitForAll);
                    animator.start();
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
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
