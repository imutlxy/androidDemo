package com.lxy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.lxy.R;

/**
 * Created by gaohuang on 17-2-21.
 */
public class TestOnMeasureView extends View {

    private final static String TAG = "TestOnMeasureView";

    private Paint paint;


//    public TestOnMeasureView(Context context) {
//        super(context);
//        init();
//    }

    public TestOnMeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

//    public TestOnMeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init();
//    }

    private void init() {
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.black));
        paint.setTextSize(59);
    }

    //todo:为什么onMeasure()方法执行了4次
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.d(TAG, "--------onMeasure----------");

        int measuredWidth = 0;
        int measuredHeight = 0;

        int widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpaceSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);

        Log.d(TAG, "widthSpaceSize=" + widthSpaceSize + ",heightSpaceSize=" + heightSpaceSize);

        Log.d(TAG, "widthSpecMode=" + widthSpecMode + ",heightSpecMode=" + heightSpecMode);


        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            Log.d(TAG, "widthMeasureSpec == MeasureSpec.AT_MOST && heightMeasureSpec == MeasureSpec.AT_MOST");

            measuredWidth = getMeasuredWidth();
            measuredHeight = getMeasuredHeight();

            measuredWidth = 200;

            setMeasuredDimension(measuredWidth, measuredHeight);

            Log.d(TAG, "measuredWidth=" + measuredWidth + ",measuredHeight=" + measuredHeight);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            Log.d(TAG, "widthMeasureSpec == MeasureSpec.AT_MOST");

        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            Log.d(TAG, "heightMeasureSpec == MeasureSpec.AT_MOST");

        } else if (widthSpecMode == MeasureSpec.EXACTLY && heightSpecMode == MeasureSpec.EXACTLY) {
            Log.d(TAG, "widthSpecMode == MeasureSpec.EXACTLY && heightSpecMode == MeasureSpec.EXACTLY");
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText("Hello,Liangxinyu!", getWidth() / 2, getHeight() / 2, paint);
    }
}
