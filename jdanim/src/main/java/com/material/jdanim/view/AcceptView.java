package com.material.jdanim.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.material.jdanim.R;

/**
 * Created by Anyone on 2016/5/5.
 */
public class AcceptView  extends View{

    private Bitmap goods,people,peopleWithGoods;
    private Paint mPaint;
    private int mCurrentAlpha;
    private float mCurrentProgress;
    private Bitmap scaledPeople;
    private Bitmap scaledGoods;
    private int measuredWidth;
    private int measuredHeight;

    public AcceptView(Context context) {
        super(context);
        initView();
    }

    public AcceptView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AcceptView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView(){
        goods = BitmapFactory.decodeResource(getResources(), R.mipmap.app_refresh_goods_0);
        people = BitmapFactory.decodeResource(getResources(), R.mipmap.app_refresh_people_0);
        peopleWithGoods = BitmapFactory.decodeResource(getResources(), R.mipmap.app_refresh_people_3);
        mPaint = new Paint();
        mPaint.setAlpha(0);
    }

    /**
     * 测量方法
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }
    //测量宽度
    private int measureWidth(int widthMeasureSpec){
        int result = 0;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (MeasureSpec.EXACTLY == mode) {
            result = size;
        }else {
            result = peopleWithGoods.getWidth();
            if (MeasureSpec.AT_MOST == mode) {
                result = Math.min(result, size);
            }
        }
        return result;
    }
    //测量高度
    private int measureHeight(int heightMeasureSpec){
        int result = 0;
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        if (MeasureSpec.EXACTLY == mode) {
            result = size;
        }else {
            result = peopleWithGoods.getHeight();
            if (MeasureSpec.AT_MOST == mode) {
                result = Math.min(result, size);
            }
        }
        return result;
    }
    //在这里面拿到测量后的宽和高，w就是测量后的宽，h是测量后的高
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        measuredWidth = w;
        measuredHeight = h;
        //根据测量后的宽高来对快递小哥做一个缩放
        scaledPeople = Bitmap.createScaledBitmap(people,measuredWidth,measuredHeight,true);
        //根据测量后的宽高来对快递包裹做一个缩放
        scaledGoods = Bitmap.createScaledBitmap(goods, scaledPeople.getWidth()*10/27, scaledPeople.getHeight()/5, true);
    }


    public void setCurrentProgress(float currentProgress){
        this.mCurrentProgress = currentProgress;
        this.mCurrentAlpha = (int)(currentProgress * 255);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(mCurrentProgress, mCurrentProgress ,  measuredWidth-scaledGoods.getWidth()/2 , measuredHeight/2);
        mPaint.setAlpha(mCurrentAlpha);
        canvas.drawBitmap(scaledGoods, measuredWidth-scaledGoods.getWidth(), measuredHeight/2-scaledGoods.getHeight()/2, mPaint);
        canvas.restore();
        canvas.save();
        canvas.scale(mCurrentProgress, mCurrentProgress , 0 , measuredHeight/2);
        mPaint.setAlpha(mCurrentAlpha);
        canvas.drawBitmap(scaledPeople, 0,0,mPaint);
        canvas.restore();

    }
}
