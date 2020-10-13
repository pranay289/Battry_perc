package com.pranay.battry_perc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int view = R.layout.activity_main;
    TextView text;


    private BroadcastReceiver mBatteryLevelReciver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            //int rawLevel = intent.getIntExtra("level", -1);
            int rawLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            //int scale = intent.getIntExtra("scale", -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int level = -1;
            if (rawLevel >= 0 && scale > 0) {
                level = (rawLevel * 100) / scale;
            }
            text.setText("Battery Level Remaining  :" + level + "%");
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);
        BatteryManager bm = (BatteryManager)getSystemService(BATTERY_SERVICE);
        int percentage = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);


        batteryLevel();
    }

    private void batteryLevel() {
        IntentFilter batteryLevelFliter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mBatteryLevelReciver, batteryLevelFliter);
    }
}
