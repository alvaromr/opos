package com.alvaro.opos.domain.interactor;

import com.alvaro.opos.domain.model.Repository;
import com.alvaro.opos.domain.executor.UseCaseExecutor;

public abstract class SaveOne<Model> extends UseCase<Model, Model> {
    private Repository<Model, ?, ?> repository;

    protected SaveOne(Repository<Model, ?, ?> repository, UseCaseExecutor useCaseExecutor) {
        super(useCaseExecutor);
        this.repository = repository;
    }

    @Override
    protected Model process(Model params) {
        return repository.save(params);
    }
}