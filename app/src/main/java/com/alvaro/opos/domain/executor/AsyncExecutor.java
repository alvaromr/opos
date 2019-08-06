package com.alvaro.opos.domain.executor;

public interface AsyncExecutor {
    void run(final Runnable interactor);
}