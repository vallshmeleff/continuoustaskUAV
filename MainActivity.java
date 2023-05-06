package com.example.timerbackgroundservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//--------------------------------------------------
//
// 
// The timer runs as a service in the Timer_Service class. Writes to the LOG every 10 seconds. 
// Sends a signal to a BroadcastReceiver that writes the time to a tv_timer (TextView). 
// Continues to work when the smartphone is blocked.
//
// http://oflameron.com
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_timer = (TextView) findViewById(R.id.tv_timer);

        Intent intent_service = new Intent(getApplicationContext(), Timer_Service.class);
        startService(intent_service.putExtra("time", 3).putExtra("task", "Example"));

       Log.e("== == startService == == ", "================ Start Service ================");

        registerReceiver(VGbroadcastReceiver,new IntentFilter("com.countdowntimerservice.receiver"));


    } // OnCreate



    private BroadcastReceiver VGbroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.e("== == onReceive == == ", "================ Start onReceive ================");

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

}


