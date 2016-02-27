package net.kwmt27.pushnotificationsample.dagger;

import android.content.Context;

import net.kwmt27.pushnotificationsample.model.GcmModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = AndroidModule.class)
public class GcmModelModule {
    @Provides
    @Singleton
    public GcmModel provideGcmModel(Context context) {
        return new GcmModel(context);
    }
}
