package com.alvaro.opos.domain.interactor;

import com.alvaro.opos.domain.model.Repository;
import com.alvaro.opos.domain.executor.UseCaseExecutor;

import java.util.List;

public abstract class GetList<Model, Params> extends UseCase<List<Model>, Params> {
    private Repository<Model, ?, Params> repository;

    protected GetList(Repository<Model, ?, Params> repository, UseCaseExecutor useCaseExecutor) {
        super(useCaseExecutor);
        this.repository = repository;
    }

    @Override
    protected List<Model> process(Params params) {
        return repository.list(params);
    }
}
