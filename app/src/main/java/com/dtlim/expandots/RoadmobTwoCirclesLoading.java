package com.dtlim.expandots;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by dale on 9/13/16.
 */
public class RoadmobTwoCirclesLoading extends LinearLayout {

    ImageView circleLeft;
    ImageView circleRight;

    AccelerateDecelerateInterpolator accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
    public static final long DURATION_IN_MILLIS = 1500;

    float width = 50;
    float height = 50;
    float minScaleX = 0;
    float minScaleY = 0;
    float maxScaleX = 1;
    float maxScaleY = 1;

    public RoadmobTwoCirclesLoading(Context context) {
        super(context);
        initialize();
        initializeAnimations();
    }

    public RoadmobTwoCirclesLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
        setAttributes(attrs);
        initializeAnimations();
    }

    public RoadmobTwoCirclesLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
        setAttributes(attrs);
        initializeAnimations();
    }

    private void initialize() {
        inflate(getContext(), R.layout.roadmob_two_circles_loading, this);
        circleLeft = (ImageView) findViewById(R.id.roadmob_customview_loading_circle_left);
        circleRight = (ImageView) findViewById(R.id.roadmob_customview_loading_circle_right);
    }

    private void setAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RoadmobTwoCirclesLoading, 0, 0);
        try {
            width = a.getDimension(R.styleable.RoadmobTwoCirclesLoading_circleWidth, 50);
            height = a.getDimension(R.styleable.RoadmobTwoCirclesLoading_circleHeight, 50);
            minScaleX = a.getFloat(R.styleable.RoadmobTwoCirclesLoading_minScaleX, 0);
            maxScaleX = a.getFloat(R.styleable.RoadmobTwoCirclesLoading_maxScaleX, 1);
            minScaleY = a.getFloat(R.styleable.RoadmobTwoCirclesLoading_minScaleY, 0);
            maxScaleY = a.getFloat(R.styleable.RoadmobTwoCirclesLoading_maxScaleY, 1);

            Log.d("CUSTIMVIEW", "CUSTOMVIEW " + minScaleX + " " + minScaleY + " " + maxScaleX + " " + maxScaleY);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            a.recycle();
            circleLeft.setLayoutParams(new LayoutParams((int) width, (int) height));
            circleRight.setLayoutParams(new LayoutParams((int) width, (int) height));
        }
    }

    private void initializeAnimations() {
        circleRight.setScaleX(minScaleX);
        circleRight.setScaleY(minScaleY);

        animateLeftCircle();
        animateRightCircle();
    }

    private void animateLeftCircle() {
        ObjectAnimator animatorLeftX = ObjectAnimator.ofFloat(circleLeft, "scaleX", minScaleX, maxScaleX, minScaleX);
        animatorLeftX.setDuration(DURATION_IN_MILLIS);
        animatorLeftX.setRepeatCount(ObjectAnimator.INFINITE);
        animatorLeftX.setInterpolator(accelerateDecelerateInterpolator);
        animatorLeftX.start();

        ObjectAnimator animatorLeftY = ObjectAnimator.ofFloat(circleLeft, "scaleY", minScaleY, maxScaleY, minScaleY);
        animatorLeftY.setDuration(DURATION_IN_MILLIS);
        animatorLeftY.setRepeatCount(ObjectAnimator.INFINITE);
        animatorLeftY.setInterpolator(accelerateDecelerateInterpolator);
        animatorLeftY.start();
    }

    private void animateRightCircle() {
        ObjectAnimator animatorRightX = ObjectAnimator.ofFloat(circleRight, "scaleX", maxScaleX, minScaleX, maxScaleX);
        animatorRightX.setDuration(DURATION_IN_MILLIS);
        animatorRightX.setRepeatCount(ObjectAnimator.INFINITE);
        animatorRightX.setInterpolator(accelerateDecelerateInterpolator);
        animatorRightX.start();

        ObjectAnimator animatorRightY = ObjectAnimator.ofFloat(circleRight, "scaleY", maxScaleY, minScaleY, maxScaleY);
        animatorRightY.setDuration(DURATION_IN_MILLIS);
        animatorRightY.setRepeatCount(ObjectAnimator.INFINITE);
        animatorRightY.setInterpolator(accelerateDecelerateInterpolator);
        animatorRightY.start();
    }
}