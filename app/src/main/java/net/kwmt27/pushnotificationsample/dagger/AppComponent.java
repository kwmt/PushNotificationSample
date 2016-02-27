package net.kwmt27.pushnotificationsample.dagger;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AndroidModule.class,
        ApiRequestModule.class,
        GcmModelModule.class
})
public interface AppComponent extends PushNotificationSampleComponent {
}
