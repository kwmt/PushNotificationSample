package net.kwmt27.pushnotificationsample;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

import net.kwmt27.pushnotificationsample.util.Logger;


/**
 * registration tokenの更新を検知
 */
public class MyInstanceIDListenerService extends InstanceIDListenerService {

    /**
     * Instance ID tokenが更新されたときに呼ばれる。
     */
    @Override
    public void onTokenRefresh() {
        Logger.d("onTokenRefresh is called.");
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}
