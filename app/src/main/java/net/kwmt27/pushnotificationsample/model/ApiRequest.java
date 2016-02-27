package net.kwmt27.pushnotificationsample.model;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiRequest {

    // TODO
    private static final String BASE_API_URL = "http://192.168.86.103:8080/api"; //BuildConfig.BASE_API_URL;
    private OkHttpClient mClient;

    public ApiRequest() {
        mClient = new OkHttpClient();
    }

    /**
     * リクエスト共通処理
     * @param path エンドポイント
     * @param body POST内容
     * @param callback コールバック
     */
    public void request(String path, RequestBody body, final ApiRequestCompletionCallback callback) {
        Request.Builder builder = new Request.Builder()
                .url(BASE_API_URL + path);

        if (body != null) {
            builder = builder.post(body);
        }

        final Request request = builder.build();

        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callback != null) {
                    callback.onCompletion(false, request, null, e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (callback != null) {
                    callback.onCompletion(true, request, response, null);
                }
            }

        });
    }}
