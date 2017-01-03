package com.tringapps.animations;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener{

    ImageView imageView;
    TextView box;
    CustomView customView;
    private int targetY;
    private float initialX,initialY,X,Y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.image);
        box = (TextView) findViewById(R.id.box);
        customView = (CustomView) findViewById(R.id.custom);
        box = (TextView) findViewById(R.id.box);
        imageView.setOnClickListener(this);
        customView.setOnTouchListener(this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        targetY = (int) ((displayMetrics.heightPixels)/1.2);
        X = box.getLeft();
        Y = box.getRight();
    }


    @Override
    public void onClick(final View view) {

        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, targetY);


        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) valueAnimator.getAnimatedValue();
                view.setTranslationY(value);
                view.setTranslationX((float) -(value / 2.5));
                Log.e("TAG", "value  ............ " + value);
                valueAnimator.setDuration(1000);
            }
        });

        valueAnimator.addListener(new ValueAnimator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {


            }

            @Override
            public void onAnimationEnd(Animator animation) {

                view.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }


        });


        valueAnimator.start();
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialX = v.getX() - event.getRawX();
                initialY = v.getY() - event.getRawY();
                return true;
            case MotionEvent.ACTION_MOVE:
                event.getX();
                event.getY();

                v.animate().x(event.getRawX() + initialX).y(event.getRawY() +
                        initialY).setDuration(0).start();

                return true;
            case MotionEvent.ACTION_UP:

                if(v.getLeft() == X && v.getTop() == Y)
                {
                    v.setVisibility(v.INVISIBLE);
                }

                return true;

        }
        return false;
    }
}
