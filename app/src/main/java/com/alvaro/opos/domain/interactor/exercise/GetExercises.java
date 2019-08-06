package com.alvaro.opos.domain.interactor.exercise;

import com.alvaro.opos.domain.model.exercise.Exercise;
import com.alvaro.opos.domain.model.exercise.ExerciseRepository;
import com.alvaro.opos.domain.interactor.GetList;
import com.alvaro.opos.domain.executor.UseCaseExecutor;

public class GetExercises extends GetList<Exercise, Void> {
    public GetExercises(ExerciseRepository repository,
                        UseCaseExecutor useCaseExecutor) {
        super(repository, useCaseExecutor);
    }
}
