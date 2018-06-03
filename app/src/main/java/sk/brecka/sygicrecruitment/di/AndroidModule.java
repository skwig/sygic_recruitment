package sk.brecka.sygicrecruitment.di;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidModule {

    private Application mApp;

    public AndroidModule(Application app) {
        mApp = app;
    }

    @Provides
    Context provideApplicationContext() {
        return mApp;
    }
}
