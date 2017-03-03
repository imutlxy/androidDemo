package com.lxy.custom.viewgroup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by gaohuang on 17-2-22.
 */
//实现自定义横向的LinearLayout,可以自动换行
    /*
    * 1. 父控件本身支持自动换行(done)
    * 2. 子控件支持 gravity
    * 3. 子控件支持 margin
    * 4. 子控件支持 padding
    * 5. 子控件支持 visibility (done)
    * 6. 父控件本身支持 wrap_content (done)
    * 7. 父控件本身支持ViewGroup的所有属性,如background,visibility,layout_gravity,margin,padding等等。
    *    (done)
    * */

    /*
    * 类似功能的博客和开源项目:
    *
    * 1. https://github.com/blazsolar/FlowLayout
    * 2. http://blog.csdn.net/lmj623565791/article/details/38352503
    * 3. http://www.ctolib.com/topics/73091.html
    *
    * */

    /*
    * 待解决问题:
    *
    * 1. onLayout()方法为什么执行两次
    *
    * */
public class MyHorizontalLinearLayout extends ViewGroup {

    private final static String TAG = "HorizontalLinearLayout";

    public MyHorizontalLinearLayout(Context context) {
        this(context, null, 0);
    }

    public MyHorizontalLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyHorizontalLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @SuppressLint("NewApi")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //如果不需要支持wrap_content,用注释掉的代码即可。
        /*-----------------------------------------------------*/

     /*   // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //测量并保存layout的宽高(使用getDefaultSize时，wrap_content和match_parent都是填充屏幕)
        //稍后会重新写这个方法，能达到wrap_content的效果
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));

     */

        /*-----------------------------------------------------*/


        //下面的代码用来支持 wrap_content 属性可以注释。

        //ViewGroup自身的测量宽度
        int layoutWidth = 0;
        //ViewGroup自身的测量高度
        int layoutHeight = 0;

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        int lastLineHeight = 0;

        int count = getChildCount();

        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);

            if (childView.getVisibility() != View.GONE) {

                //依次测量各个子View
                measureChild(childView, widthMeasureSpec, heightMeasureSpec);

                // 得到child的lp
                MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
                // 当前子控件实际占据的宽度
                int childWidth = childView.getMeasuredWidth() + lp.leftMargin
                        + lp.rightMargin;
                // 当前子控件实际占据的高度
                int childHeight = childView.getMeasuredHeight() + lp.topMargin
                        + lp.bottomMargin;

                //超出一行,自动换行
                if (layoutWidth + childWidth > widthSpecSize) {
                    //前面几行最大的宽度和当前行的宽度哪个大哪个就是新的layoutWidth
                    layoutWidth = childWidth;
                    layoutHeight += lastLineHeight;

                    lastLineHeight = childHeight;
                    Log.d(TAG, "i=" + i + ",lastLineHeight=" + lastLineHeight + ",layoutHeight=" + layoutHeight);
                } else {
                    layoutWidth += childWidth;

                    Log.d(TAG, "i=" + i + ",lastLineHeight=" + lastLineHeight + ",childMeasuredHeight=" + childHeight);

                    lastLineHeight = Math.max(childHeight, lastLineHeight);
                }

                if (i == count - 1) {
                    layoutHeight += lastLineHeight;
                    Log.d(TAG, "最后一行:layoutHeight=" + layoutHeight);
                }

                Log.d(TAG, "i=" + i + ",onMeasure()--layoutWidth=" + layoutWidth + ",layoutHeight=" + layoutHeight + ",childView.measuredWidth=" + childView.getMeasuredWidth() + ",childView.measuredHeight=" + childView.getMeasuredHeight());
            }
        }

        setMeasuredDimension(widthSpecMode == MeasureSpec.EXACTLY ? widthSpecSize : layoutWidth, heightSpecMode == MeasureSpec.EXACTLY ? heightSpecSize : layoutHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        Log.d(TAG, "left=" + left + ",top=" + top + ",right=" + right + ",bottom=" + bottom);

        int count = getChildCount();

        int layoutWidth = 0;//目前layout已经占据的宽度
        int layoutHeight = 0;//目前layout已经占据的高度

        int parentWidth = getWidth();

//        Log.d(TAG, "onLayout()::parentWidth=" + parentWidth + ",parentHeight=" + parentHeight + ",getMeasuredWidth=" + getMeasuredWidth() + ",getMeasuredHeight=" + getMeasuredHeight());

        int lastLayoutHeight = 0;//上一行子View占据的高度,用于超出一行时重新计算View的高度

        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            int measuredWidth = childView.getMeasuredWidth();
            int measuredHeight = childView.getMeasuredHeight();

            //超出一行,自动换行
            if (layoutWidth + measuredWidth > parentWidth) {
//                Log.d(TAG, "i=" + i + ",parentWidth=" + parentWidth + ",layoutWidth + measuredWidth=" + (layoutWidth + measuredWidth));
                //如果超出一行,当前layout的高度=已经占据的高度+上一行View的高度
                layoutHeight += lastLayoutHeight;

                left = 0;
                right = left + measuredWidth;
                top = layoutHeight;
                bottom = top + measuredHeight;

                layoutWidth = measuredWidth;
                lastLayoutHeight = measuredHeight;

//                Log.d(TAG, "i=" + i + ",lastLayoutHeight=" + lastLayoutHeight + ",layoutHeight=" + layoutHeight);
//                Log.d(TAG, "i=" + i + ",measuredHeight=" + measuredHeight + ",超出一行--left=" + left + ",top=" + top + ",right=" + right + ",bottom=" + bottom);
            } else {
                //没有超出一行
                left = layoutWidth;
                right = left + measuredWidth;
                top = layoutHeight;
                bottom = top + measuredHeight;

                layoutWidth += measuredWidth;

                lastLayoutHeight = Math.max(lastLayoutHeight, measuredHeight);

//                Log.d(TAG, "i=" + i + ",lastLayoutHeight=" + lastLayoutHeight + ",layoutHeight=" + layoutHeight);
//                Log.d(TAG, "i=" + i + ",measuredHeight=" + measuredHeight + ",没有超出一行--left=" + left + ",top=" + top + ",right=" + right + ",bottom=" + bottom);
            }
            childView.layout(left, top, right, bottom);
        }
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
