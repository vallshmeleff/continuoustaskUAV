package org.o7planning.continuetimerworkuav;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

//--------------------------------------------------------------------
// 
// Timer_Service Class as Service
// 
// Add as File -> New -> Service
// 
// Working in backplane
// 
// (c) Valery Shmelev http://oflameron.com
// 
//--------------------------------------------------------------------

public class Timer_Service extends Service {
    public static int ed =0;
    public static String str_receiver = "com.countdowntimerservice.receiver";

    private Handler mHandler = new Handler();
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String strDate;
    Date date_current, date_diff;
    SharedPreferences mpref;
    SharedPreferences.Editor mEditor;

    private Timer mTimer = null;
    public static final long NOTIFY_INTERVAL = 1000;
    Intent intent;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mTimer = new Timer();
        mTimer.schedule(new TimeDisplayTimerTask(), 0,10000); // 10 sec
        // intent = new Intent(str_receiver);
        intent = new Intent(str_receiver);
        sendBroadcast(intent);



    } // onCreate




    class TimeDisplayTimerTask extends TimerTask {
        //int tm = intent.getIntExtra("time", 10);

        @Override
        public void run() {
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    ed = ed +1;
                    Log.e("== == mTimer == == ", "================ mTimer ============== " + String.valueOf(ed) + "oflameron.com");
                    sendBroadcast(intent);

                }

            });
        }

    } // class TimeDisplayTimerTask

    @Override
    public void onDestroy() {
        super.onDestroy();
        // = mTimer.cancel();
        Log.e("== == Service finish == == ", "Finish");
    }







}
