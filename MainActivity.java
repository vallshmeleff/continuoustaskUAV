package org.o7planning.bgservicesms;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.telephony.SmsManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//--------------------------------------------------
//
// Uses Timer_Service as background service with tayier,
// which sends broadcasts every 10 seconds.
// MainActivity has a BroadcastReceiver that receives them
// and performs an action - writes the current time to tv_timer (TextView)
//
// After launch, the application "disappears" super.finish() - moves to the background
// At the same time, SMS continue to be sent on 05/18/2023
// Sends SMS from BroadcastReceiver
//
// Everything is working.
//
//--------------------------------------------------

public class MainActivity extends AppCompatActivity {

    private Button btn_start, btn_cancel;
    private TextView tv_timer;
    String date_time;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    EditText et_hours;

    SharedPreferences mpref;
    SharedPreferences.Editor mEditor;
    private final static int SEND_SMS_PERMISSION_REQ=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQ); // SMS Permissions

        tv_timer = (TextView) findViewById(R.id.tv_timer);

        // Запускаем свой IntentService
        Intent intent_service = new Intent(getApplicationContext(), Timer_Service.class);
        startService(intent_service.putExtra("time", 3).putExtra("task", "App Example"));
        // startService(intent_service); // The application sends data to the service via the startService() method
        Log.e("== == startService == == ", "================ Start Service ================");


        // регистрируем BroadcastReceiver
        registerReceiver(VGbroadcastReceiver,new IntentFilter("com.countdowntimerservice.receiver"));

        super.finish(); // Hide application
    } // OnCreate



    // Receiver. Registration
    private BroadcastReceiver VGbroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //= String str_time = intent.getStringExtra("time");
            //= String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
            //= tv_timer.setText(currentTime); // tv_timer = (TextView) findViewById
            String ttime = java.text.DateFormat.getDateTimeInstance().format(new Date());
            tv_timer.setText(ttime); // Write the date and time on the timer

            MainActivity MActivity = new MainActivity();
            MActivity.dial(); // SMS Send

            Log.e("== == BroadcastReceiver == == ", "================ Start onReceive ================");

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(VGbroadcastReceiver,new IntentFilter(Timer_Service.str_receiver));

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(VGbroadcastReceiver);
    }



    //------------------- SMS ---------------------------------
    public void dial() {
        MainActivity MActivity = new MainActivity();

        String SMSMess = "== Oflameron Java Programmer =";java.text.DateFormat.getDateTimeInstance().format(new Date());
        SmsManager.getDefault().sendTextMessage("420251001411", null, SMSMess, null, null); // Phone number
        Log.d("= Dial =", "==== SMS Send ====: " + String.valueOf("ehh")); // SMS Count

    }




} // class MainActivity



