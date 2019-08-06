package com.alvaro.opos.domain.interactor;

import com.alvaro.opos.domain.model.Repository;
import com.alvaro.opos.domain.executor.UseCaseExecutor;

public abstract class GetOne<Model, Params> extends UseCase<Model, Params> {
    private final Repository<Model, Params, ?> repository;

    protected GetOne(Repository<Model, Params, ?> repository, UseCaseExecutor useCaseExecutor) {
        super(useCaseExecutor);
        this.repository = repository;
    }

    @Override
    protected Model process(Params params) {
        return repository.get(params);
    }
}