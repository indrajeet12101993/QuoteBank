package com.atechnos.quotebank.timerNotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.atechnos.quotebank.FinalActivity;

/**
 * Created by Jaison on 17/06/17.
 */

public class AlarmReceiverSleep extends BroadcastReceiver {

    String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                LocalDataSleep localData = new LocalDataSleep(context);
                NotificationSchedulerSleep.setReminder(context, AlarmReceiverSleep.class, localData.get_hour(), localData.get_min());
                return;
            }
        }

        Log.d(TAG, "onReceive: ");

        //Trigger the notification
        NotificationSchedulerSleep.showNotification(context, FinalActivity.class,
                "You have 5 unwatched videos", "Watch them now?");

    }
}


