package net.kwmt27.pushnotificationsample.dagger;


import net.kwmt27.pushnotificationsample.MainActivity;
import net.kwmt27.pushnotificationsample.RegistrationIntentService;
import net.kwmt27.pushnotificationsample.model.ApiRequest;

public interface PushNotificationSampleComponent {
    ApiRequest apiRequest();
    void inject(MainActivity activity);
    void inject(RegistrationIntentService service);
}
