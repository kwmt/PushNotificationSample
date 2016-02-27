package net.kwmt27.pushnotificationsample.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * ユーザーモデル
 */
public class UserModel {

    /** UUIDのプレファレンスキー */
    private static final String PREF_UUID = "pref_uuid";

    /**
     * UUIDをプレファレンスにセットする。
     * @param context
     * @param uuid
     */
    public static void setUuid(final Context context, final String uuid) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(PREF_UUID, uuid).apply();
    }

    /**
     * UUIDをプレファレンスから取得する。
     * @param context
     * @return
     */
    public static String getUuid(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(PREF_UUID, null);
    }

}
