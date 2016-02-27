package net.kwmt27.pushnotificationsample;

import android.app.IntentService;
import android.content.Intent;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import net.kwmt27.pushnotificationsample.model.ApiRequestCompletionCallback;
import net.kwmt27.pushnotificationsample.model.App;
import net.kwmt27.pushnotificationsample.model.GcmModel;
import net.kwmt27.pushnotificationsample.util.Logger;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Request;
import okhttp3.Response;

/**
 * トークン取得のためのサービス
 */
public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegistrationIntentService";

    @Inject
    GcmModel mGcmModel;

    public RegistrationIntentService(){
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ((App)getApplication()).component().inject(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Logger.d("onHandleIntent is called.");

        try {
            InstanceID instanceID = InstanceID.getInstance(this);
            // R.string.gcm_defaultSenderId (the Sender ID) is typically derived from google-services.json.
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            Logger.i("GCM Registration Token:" + token);

            sendRegistrationToServer(token);

        } catch (IOException e) {
            Logger.e(e);
        }
    }

    /**
     * AppサーバーにRegistrationTokenを登録
     * @param token
     */
    private void sendRegistrationToServer(String token) {
        mGcmModel.requestRegistrationToken(token, new ApiRequestCompletionCallback() {
            @Override
            public void onCompletion(boolean success, Request request, Response response, IOException e) {
                Logger.d("success:" + success + ", request:" + request + ", response:" + response + ", e:" + e);
                mGcmModel.setSentTokenToServer(RegistrationIntentService.this, success);
            }
        });
    }


}
