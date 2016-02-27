package net.kwmt27.pushnotificationsample.dagger;

import net.kwmt27.pushnotificationsample.model.ApiRequest;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiRequestModule {
    @Provides
    @Singleton
    public ApiRequest provideApiRequestModule() {
        return new ApiRequest();
    }
}
