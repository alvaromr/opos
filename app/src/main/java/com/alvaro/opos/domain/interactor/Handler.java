package com.alvaro.opos.domain.interactor;

public interface Handler<Result> {

    void handle(Result result);

    void error(Exception exception);

}
