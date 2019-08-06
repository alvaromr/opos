package com.alvaro.opos.domain.interactor;

import com.alvaro.opos.domain.model.Repository;
import com.alvaro.opos.domain.executor.UseCaseExecutor;

public abstract class DeleteOne<Model, Params> extends UseCase<Void, Params> {
    private Repository<Model, Params, ?> repository;

    protected DeleteOne(Repository<Model, Params, ?> repository, UseCaseExecutor useCaseExecutor) {
        super(useCaseExecutor);
        this.repository = repository;
    }

    @Override
    protected Void process(Params params) {
        repository.delete(params);
        return null;
    }
}