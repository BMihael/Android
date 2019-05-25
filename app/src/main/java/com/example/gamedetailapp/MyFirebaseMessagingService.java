package com.example.gamedetailapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private NotificationManager notificationManager;
    private static String CHANNEL_ID = "01";
    private  long[] notificationVibrate = {0,500,20,500,200};

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        ///super.onMessageReceived(remoteMessage);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            setupChannel();
        }

        int notificationID = new Random().nextInt(10);
        Uri notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(notificationSound)
                .setVibrate(notificationVibrate)
                .setAutoCancel(true);

        notificationManager.notify(notificationID,notificationBuilder.build());

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupChannel() {
        NotificationChannel mainChannel = new NotificationChannel(CHANNEL_ID,"Main Channel",NotificationManager.IMPORTANCE_HIGH);
        mainChannel.enableLights(true);
        mainChannel.setVibrationPattern(notificationVibrate);
        mainChannel.setLightColor(Color.BLUE);
        mainChannel.enableVibration(true);
        if(notificationManager != null){
            notificationManager.createNotificationChannel(mainChannel);
        }
    }
}
