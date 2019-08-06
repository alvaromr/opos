package com.alvaro.opos.platform.executor;

import android.os.Handler;
import android.os.Looper;

import com.alvaro.opos.domain.executor.MainExecutor;

public class UIThreadExecutor implements MainExecutor {

    private Handler handler;

    public UIThreadExecutor() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    public void run(Runnable runnable) {
        handler.post(runnable);
    }
}