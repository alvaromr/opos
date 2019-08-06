package com.alvaro.opos.platform.di;

import android.app.Application;
import android.content.Context;

import com.alvaro.opos.domain.executor.AsyncExecutor;
import com.alvaro.opos.domain.executor.MainExecutor;
import com.alvaro.opos.domain.executor.UseCaseExecutor;
import com.alvaro.opos.platform.executor.ThreadExecutor;
import com.alvaro.opos.platform.executor.UIThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    AsyncExecutor provideJobScheduler() {
        return new ThreadExecutor();
    }

    @Provides
    @Singleton
    MainExecutor provideUIScheduler() {
        return new UIThreadExecutor();
    }

    @Provides
    @Singleton
    UseCaseExecutor providesUseCaseExecutor(AsyncExecutor asyncExecutor,
                                            MainExecutor mainExecutor) {
        return new UseCaseExecutor(asyncExecutor, mainExecutor);
    }
}
