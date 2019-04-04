package com.asuprojects.testescomponentes;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class NotificacoesActivity extends AppCompatActivity {

    private String channelId = "channel_id_notificacao";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacoes);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Notificações");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        findViewById(R.id.btn_notificacao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(NotificacoesActivity.this, channelId)
                                .setSmallIcon(R.drawable.ic_notification_icon)
                                .setContentTitle("My notification")
                                .setContentText("Hello World!");

                /** Para Android Oreo ou Maior **/
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    String channelName = "channel1";

                    NotificationChannel channel =
                            new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
                    channel.enableLights(true);
                    channel.setLightColor(Color.RED);
                    channel.setShowBadge(true);
                    channel.enableVibration(true);

                    mBuilder.setChannelId(channelId);

                    if(mNotificationManager != null){
                        mNotificationManager.createNotificationChannel(channel);
                    }
                } else { /** Para Android abaixo do Oreo **/
                    mBuilder.setDefaults(Notification.DEFAULT_SOUND |
                            Notification.DEFAULT_LIGHTS |
                            Notification.DEFAULT_VIBRATE);
                }

                Intent resultIntent = new Intent(NotificacoesActivity.this, MainActivity.class);

                TaskStackBuilder stackBuilder = TaskStackBuilder.create(NotificacoesActivity.this);
                stackBuilder.addParentStack(MainActivity.class);
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);


                int mId = 200;
                mNotificationManager.notify(mId, mBuilder.build());
            }
        });
    }
}
