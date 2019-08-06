package com.alvaro.opos.domain.interactor.exercise;

import com.alvaro.opos.domain.model.exercise.Exercise;
import com.alvaro.opos.domain.model.Repository;
import com.alvaro.opos.domain.interactor.SaveOne;
import com.alvaro.opos.domain.executor.UseCaseExecutor;

public class SaveExercise extends SaveOne<Exercise> {
    public SaveExercise(Repository<Exercise, ?, ?> repository,
                        UseCaseExecutor useCaseExecutor) {
        super(repository, useCaseExecutor);
    }
}
