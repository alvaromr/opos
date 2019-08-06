package com.alvaro.opos.domain.interactor;

import com.alvaro.opos.domain.executor.UseCaseExecutor;

abstract class UseCase<Result, ExecuteParam> {
    private UseCaseExecutor useCaseExecutor;

    UseCase(UseCaseExecutor useCaseExecutor) {
        this.useCaseExecutor = useCaseExecutor;
    }

    public final void execute(Handler<Result> handler, ExecuteParam params) {
        useCaseExecutor.execute(new Task(handler, params));
    }

    private class Task implements UseCaseExecutor.Task {
        private final Handler<Result> handler;
        private final ExecuteParam params;
        private Result result;

        Task(Handler<Result> handler, ExecuteParam params) {
            this.handler = handler;
            this.params = params;
        }

        @Override
        public void runInBackground() {
            result = process(params);
        }

        @Override
        public void onSuccess() {
            handler.handle(result);
        }

        @Override
        public void onFailure(Exception exception) {
            handler.error(exception);
        }
    }

    protected abstract Result process(ExecuteParam params);
}