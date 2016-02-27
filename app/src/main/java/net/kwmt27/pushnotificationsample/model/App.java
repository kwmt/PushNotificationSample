package net.kwmt27.pushnotificationsample.model;

import android.app.Application;

import net.kwmt27.pushnotificationsample.dagger.AndroidModule;
import net.kwmt27.pushnotificationsample.dagger.ApiRequestModule;
import net.kwmt27.pushnotificationsample.dagger.AppComponent;
import net.kwmt27.pushnotificationsample.dagger.DaggerAppComponent;
import net.kwmt27.pushnotificationsample.dagger.GcmModelModule;
import net.kwmt27.pushnotificationsample.dagger.PushNotificationSampleComponent;

public class App extends Application {

    private PushNotificationSampleComponent appComponent = null;


    @Override
    public void onCreate() {
        super.onCreate();

        if (appComponent == null){
            appComponent = createComponent();
        }

    }

    protected AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .androidModule(new AndroidModule(this))
                .apiRequestModule(new ApiRequestModule())
                .gcmModelModule(new GcmModelModule())
                .build();
    }

    public PushNotificationSampleComponent component() {
        return appComponent;
    }

    public void setComponent(PushNotificationSampleComponent component) {
        appComponent = component;
    }


}
