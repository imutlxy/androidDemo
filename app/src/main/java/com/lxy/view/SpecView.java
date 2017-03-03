package com.lxy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.lxy.R;


/**
 * Created by gaohuang on 17-2-16.
 */
public class SpecView extends View {

    private Paint paint;

    public SpecView(Context context) {
        super(context);

        init();
    }

    public SpecView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init(){
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.black));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int widthRadius = (width-paddingLeft-paddingRight)/2;
        int heightRadius = (height-paddingTop-paddingBottom)/2;

        int radius,centerX,centerY;

        if (widthRadius <= heightRadius){
            radius = widthRadius;
            //这种情况下paddingBottom和paddingTop不能兼顾,因为widthRadius比heightRadius小,
            // 这里我们遵循paddingTop,paddingBottom相当于无效
            centerX = paddingLeft + radius;
            centerY = paddingTop + radius;
        }else{
            radius = heightRadius;
            //这种情况下paddingLeft和paddingRight不能兼顾,同样我们遵循paddingLeft,
            // paddingRight相当于无效
            centerX = paddingLeft + radius;
            centerY = paddingTop + radius;
        }

        canvas.drawCircle(centerX,centerY,radius,paint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);





    }
}
