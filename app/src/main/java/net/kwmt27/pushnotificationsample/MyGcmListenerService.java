package net.kwmt27.pushnotificationsample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GcmListenerService;

import net.kwmt27.pushnotificationsample.util.Logger;


/**
 * Push通知を受け取るクラス
 */
public class MyGcmListenerService extends GcmListenerService {

    // プッシュ通知確認コマンド
    // curl --header "Authorization: key=AIzaSyCVW5QWTWgHQzfWCU0Y0_UzntlLMrXKGuE" --header Content-Type:"application/json" https://android.googleapis.com/gcm/send -d "{\"registration_ids\":[\"<Registration id>\"],\"data\":{\"message\":\"Hello\"}}"


    @Override
    public void onMessageReceived(String from, Bundle data) {
        // TODO: keyは適当なので変更すること。
        String message = data.getString("message");
        String tag = data.getString("tag");
        Logger.d("From:" + from);
        Logger.d("message:" + message);
        Logger.d("tag:" + tag);

        sendNotification(message, tag);
    }

    /**
     * 通知領域にメッセージを通知する
     */
    private void sendNotification(String message, String tag) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // TODO
        String appName = getResources().getString(R.string.app_name);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle(appName)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(message)
                .setTicker(appName + ":" + message)
                .setSound(defaultSoundUri)
                .setContentIntent(pi)
                .setAutoCancel(true); // タップするとキャンセル(消える);

        // Android Tips #23 Android4.1 で追加された Notification のスタイルを使いこなす
        // http://dev.classmethod.jp/smartphone/android/android-tips-23-android4-1-notification-style/
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle(builder);
        bigTextStyle.bigText(message);
        bigTextStyle.setBigContentTitle(appName);
        bigTextStyle.setSummaryText("要約");

        NotificationManager manager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
        manager.notify(tag, 0, bigTextStyle.build());
    }
}
