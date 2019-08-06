package com.alvaro.opos.platform.di;

import android.app.Application;

import com.alvaro.opos.platform.App;

import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class,
        DataModule.class,
        DomainModule.class
})
public interface AppComponent extends AndroidInjector<App> {

    @Override
    void inject(App app);

    @Component.Builder
    interface Builder {
        /* This method is necessary for DaggerMock when running instrumentations tests */
        Builder appModule(AppModule appModule);

        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

}
