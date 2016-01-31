package com.cs.mju.hanmadang.Function.push;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.cs.mju.hanmadang.Constants;
import com.cs.mju.hanmadang.MainActivity;
import com.cs.mju.hanmadang.R;
import com.cs.mju.hanmadang.Tab.Club.ClubReadActivity;
import com.cs.mju.hanmadang.Tab.Club.JSONParser;
import com.cs.mju.hanmadang.Tab.DateActivity;
import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by 하옹 on 2016-01-28.
 */
public class MyGcmListenerService extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";
    private JSONParser jsonParser = new JSONParser();
    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);

        if (from.startsWith("/topics/")) {
            // message received from some topic.
        } else {
            // normal downstream message.
        }

        // [START_EXCLUDE]
        /**
         * Production applications would usually process the message here.
         * Eg: - Syncing with server.
         *     - Store message in local database.
         *     - Update UI.
         */

        /**
         * In some cases it may be useful to show a notification indicating to the user
         * that a message was received.
         */
        sendNotification(message);
        // [END_EXCLUDE]
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received GCM message.
     *
     * @param message GCM message received.
     */
    private void sendNotification(String message) {
        Intent intent = new Intent();
        String tab = "";
        Bundle args = new Bundle();
        int position;
        switch (Constants.num) {
            case 0:
                intent = new Intent(this, MainActivity.class);
                break;
            case 1:
                intent = new Intent(this, ClubReadActivity.class);
                jsonParser.parseJSONFromURL(Constants.CLUB_URL);
                position = (jsonParser.object.size() - 1);
                args.putInt("pos", position);
                tab = " : " + getString(R.string.club) + " " + getString(R.string.alarm);
                intent.putExtras(args);
                break;
            case 2:
                intent = new Intent(this, DateActivity.class);
                break;
            default:
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.hanmadang_noti_icon)
                .setTicker(message)
                .setContentTitle(Constants.HANMADANG + tab)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }


}
