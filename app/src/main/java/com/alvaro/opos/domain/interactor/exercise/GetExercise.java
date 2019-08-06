package com.alvaro.opos.domain.interactor.exercise;


import com.alvaro.opos.domain.model.exercise.Exercise;
import com.alvaro.opos.domain.model.exercise.ExerciseRepository;
import com.alvaro.opos.domain.interactor.GetOne;
import com.alvaro.opos.domain.executor.UseCaseExecutor;

public class GetExercise extends GetOne<Exercise, Long> {
    public GetExercise(ExerciseRepository repository,
                       UseCaseExecutor useCaseExecutor) {
        super(repository, useCaseExecutor);
    }
}
