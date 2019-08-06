package com.alvaro.opos.platform;

import android.app.Application;
import android.widget.Toast;

import com.alvaro.opos.platform.di.AppComponent;
import com.alvaro.opos.platform.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class App extends DaggerApplication {
    @Override
    protected AndroidInjector<App> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}
