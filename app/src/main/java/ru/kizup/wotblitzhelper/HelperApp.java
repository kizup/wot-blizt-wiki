package ru.kizup.wotblitzhelper;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.squareup.picasso.Picasso;

import ru.kizup.wotblitzhelper.di.application.AppComponent;
import ru.kizup.wotblitzhelper.di.application.AppModule;
import ru.kizup.wotblitzhelper.di.application.DaggerAppComponent;
import ru.kizup.wotblitzhelper.di.application.UtilsModule;

/**
 * Created by: dpuzikov on 25.12.17.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public class HelperApp extends Application {

    private AppComponent mAppComponent;

    @NonNull
    public static HelperApp get(@NonNull Context context) {
        return (HelperApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initComponents();
    }

    private void initComponents() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .utilsModule(new UtilsModule())
                .build();
    }

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
