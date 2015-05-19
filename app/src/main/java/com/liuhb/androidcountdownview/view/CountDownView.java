package com.liuhb.androidcountdownview.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.liuhb.androidcountdownview.R;

import java.util.Timer;
import java.util.TimerTask;


/**
 * android倒计时的TextView
 * Created by liuhb on 2015/5/18.
 */
public class CountDownView extends TextView {

    private long mMills ;
    private OnFinishedListener mFinishedListener ;
    private String mStrTime = "";

    private TimerTask mTask ;
    private Timer mTimer = new Timer();


    public interface OnFinishedListener {
        public void onFinished()  ;
    }

    public void setOnFinishedListener(OnFinishedListener listener) {
        this.mFinishedListener = listener ;
    }

    public CountDownView(Context context) {
        this(context, null);
    }

    public CountDownView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownView(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CountDownView) ;
        try {
            mMills = a.getInt(R.styleable.CountDownView_mills,0) ;
        } finally {
            a.recycle();
        }



        mTask = new TimerTask() {
            @Override
            public void run() {

                Activity activity = (Activity)context ;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        if (mMills >= 0) {
                            mStrTime = formatTime(mMills);
                            setText(mStrTime);
                            mMills -= 1000;
                        } else {
                            if (mFinishedListener!=null) {
                                mFinishedListener.onFinished();
                            }
                            mTimer.cancel();
                            mStrTime=null ;

                        }

                    }
                });

            }
        } ;


        mTimer.schedule(mTask, 1000, 1000);


    }


    /**
     * 设置倒计时的时长(毫秒)
     * @param mills
     */
    public void setMills(long mills) {
        mMills = mills ;
        postInvalidate();
    }





    /**
     * convert mills left to 00:00:00:
     * @param mills
     * @return
     */
    private String formatTime(long mills) {

        long dayMills = 24*60*60*1000 ;
        long hourMills = 60*60*1000 ;
        long minuteMills = 60*1000 ;
        long secondMills = 1000 ;

        long days = mills / dayMills ;
        mills %= dayMills ;
        long hours = mills / hourMills ;
        mills %= hourMills ;
        long minutes = mills / minuteMills ;
        mills %= minuteMills ;
        long seconds = mills / secondMills ;

        if (days>0) {
            return "活动尚未开始" ;
        }

        return /*((days<10)?"0"+days:""+days) + ":"
                + */((hours<10)?"0"+hours:""+hours )+ ":"
                + ((minutes<10)?"0"+minutes:""+minutes ) + ":"
                + ((seconds<10)?"0"+seconds:""+seconds );
    }
}
