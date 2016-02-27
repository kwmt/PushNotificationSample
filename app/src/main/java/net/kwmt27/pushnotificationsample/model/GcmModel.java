package net.kwmt27.pushnotificationsample.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import net.kwmt27.pushnotificationsample.util.Logger;

import javax.inject.Inject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * GCM(Push通知)関連モデル
 */
public class GcmModel {


    private static final String SENT_TOKEN_TO_SERVER = "sent_token_to_server";

    @Inject
    ApiRequest mApiRequest;

    public GcmModel(Context context) {
        mApiRequest = ((App)context).component().apiRequest();

    }

    /**
     * RegistrationTokenをAppサーバーに送ったかどうかフラグをプレファレンスにセットする
     * @param context
     * @param sent
     */
    public void setSentTokenToServer(final Context context, final boolean sent) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean(SENT_TOKEN_TO_SERVER, sent).apply();
    }

    /**
     * RegistrationTokenをAppサーバーに送ったかどうかをプレファレンスから取得する。true:送った
     * @param context
     * @return
     */
    public boolean isSentTokenToServer(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(SENT_TOKEN_TO_SERVER, false);
    }


    /**
     * RegistrationTokenをApiサーバーに登録する
     */
    public void requestRegistrationToken(String token, ApiRequestCompletionCallback callback) {
        Logger.d("requestRegistrationToken is called.");

        // TODO:適切なuuidにする
        String uuid = "uuid";
        RequestBody body = new FormBody.Builder()
                .add("udid", uuid)
                .add("registration_token", token)
                .build();


        // TODO: パスも適切に
        mApiRequest.request("/sample", body, callback);
    }
}

