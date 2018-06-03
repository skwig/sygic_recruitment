package sk.brecka.sygicrecruitment;

import android.app.Application;
import android.content.Context;

import sk.brecka.sygicrecruitment.di.AndroidModule;
import sk.brecka.sygicrecruitment.di.AppComponent;
import sk.brecka.sygicrecruitment.di.DaggerAppComponent;

public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

//        Realm.init(this);

        mAppComponent = DaggerAppComponent.builder()
                .androidModule(new AndroidModule(this))
                .build();
    }

    public AppComponent getComponent() {
        return mAppComponent;
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }
}
