package net.kwmt27.pushnotificationsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.kwmt27.pushnotificationsample.util.PlayServicesUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // GooglePlayServicesのバージョンチェック
        if (PlayServicesUtils.checkGooglePlayServices(this)) {
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        // GooglePlayServicesのバージョンチェック
        PlayServicesUtils.checkGooglePlayServices(this);
    }
}
