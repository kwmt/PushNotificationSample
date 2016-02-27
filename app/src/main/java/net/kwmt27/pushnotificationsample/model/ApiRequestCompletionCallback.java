package net.kwmt27.pushnotificationsample.model;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * ApiRequest用完了コールバック
 */
public interface ApiRequestCompletionCallback {
    void onCompletion(boolean success, Request request, Response response, IOException e);
}
