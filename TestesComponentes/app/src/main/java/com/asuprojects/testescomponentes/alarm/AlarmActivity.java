package com.asuprojects.testescomponentes.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.asuprojects.testescomponentes.MainActivity;
import com.asuprojects.testescomponentes.R;

public class AlarmActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Alarm Manager");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        findViewById(R.id.btn_set_alarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // BEGIN_INCLUDE (intent_fired_by_alarm)
                // First create an intent for the alarm to activate.
                // This code simply starts an Activity, or brings it to the front if it has already
                // been created.
                Intent intent = new Intent(AlarmActivity.this, ResultAlarmActivity.class);
                intent.setAction(Intent.ACTION_MAIN);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                // END_INCLUDE (intent_fired_by_alarm)

                // BEGIN_INCLUDE (pending_intent_for_alarm)
                // Because the intent must be fired by a system service from outside the application,
                // it's necessary to wrap it in a PendingIntent.  Providing a different process with
                // a PendingIntent gives that other process permission to fire the intent that this
                // application has created.
                // Also, this code creates a PendingIntent to start an Activity.  To create a
                // BroadcastIntent instead, simply call getBroadcast instead of getIntent.
                PendingIntent pendingIntent = PendingIntent.getActivity(AlarmActivity.this, REQUEST_CODE,
                        intent, 0);

                // END_INCLUDE (pending_intent_for_alarm)

                // BEGIN_INCLUDE (configure_alarm_manager)
                // There are two clock types for alarms, ELAPSED_REALTIME and RTC.
                // ELAPSED_REALTIME uses time since system boot as a reference, and RTC uses UTC (wall
                // clock) time.  This means ELAPSED_REALTIME is suited to setting an alarm according to
                // passage of time (every 15 seconds, 15 minutes, etc), since it isn't affected by
                // timezone/locale.  RTC is better suited for alarms that should be dependant on current
                // locale.

                // Both types have a WAKEUP version, which says to wake up the device if the screen is
                // off.  This is useful for situations such as alarm clocks.  Abuse of this flag is an
                // efficient way to skyrocket the uninstall rate of an application, so use with care.
                // For most situations, ELAPSED_REALTIME will suffice.
                int alarmType = AlarmManager.ELAPSED_REALTIME;
                final int CINCO_SEGUNDOS = 5000;

                // The AlarmManager, like most system services, isn't created by application code, but
                // requested from the system.
                AlarmManager alarmManager = (AlarmManager)
                        getSystemService(Context.ALARM_SERVICE);

                // setRepeating takes a start delay and period between alarms as arguments.
                // The below code fires after 15 seconds, and repeats every 15 seconds.  This is very
                // useful for demonstration purposes, but horrendous for production.  Don't be that dev.
                if(alarmManager != null){
                    alarmManager.setRepeating(alarmType, SystemClock.elapsedRealtime() + CINCO_SEGUNDOS,
                            CINCO_SEGUNDOS, pendingIntent);
                } else {
                    Log.i("FALHA", "onClick: Falha ao setar alarme");
                }
                // END_INCLUDE (configure_alarm_manager);
                Log.i("RepeatingAlarmFragment", "Alarm set.");

            }
        });
    }
}
