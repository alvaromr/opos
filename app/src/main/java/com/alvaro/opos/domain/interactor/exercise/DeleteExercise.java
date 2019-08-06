package com.alvaro.opos.domain.interactor.exercise;

import com.alvaro.opos.domain.model.exercise.Exercise;
import com.alvaro.opos.domain.model.Repository;
import com.alvaro.opos.domain.interactor.DeleteOne;
import com.alvaro.opos.domain.executor.UseCaseExecutor;

public class DeleteExercise extends DeleteOne<Exercise, Long> {
    public DeleteExercise(Repository<Exercise, Long, ?> repository,
                          UseCaseExecutor useCaseExecutor) {
        super(repository, useCaseExecutor);
    }
}
