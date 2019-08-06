package com.alvaro.opos.domain.executor;

public class UseCaseExecutor {
    private final AsyncExecutor asyncExecutor;
    private final MainExecutor mainExecutor;
    private Task task;

    public UseCaseExecutor(AsyncExecutor asyncExecutor,
                    MainExecutor mainExecutor) {
        this.asyncExecutor = asyncExecutor;
        this.mainExecutor = mainExecutor;
    }

    public final void execute(Task task) {
        this.task = task;
        this.asyncExecutor.run(new Runnable(){
            @Override
            public void run() {
                Exception exception = null;
                try {
                    task.runInBackground();
                } catch (Exception e) {
                    e.printStackTrace();
                    exception = e;
                }
                final Exception e = exception;
                mainExecutor.run(new Runnable() {
                    @Override
                    public void run() {
                        if (e != null) {
                            UseCaseExecutor.this.task.onFailure(e);
                        } else {
                            UseCaseExecutor.this.task.onSuccess();
                        }
                    }
                });
            }
        });
    }

    public interface Task {
        void runInBackground();

        void onSuccess();

        void onFailure(Exception exception);
    }

}