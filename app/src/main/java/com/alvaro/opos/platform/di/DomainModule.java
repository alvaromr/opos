package com.alvaro.opos.platform.di;

import com.alvaro.opos.domain.model.exercise.ExerciseRepository;
import com.alvaro.opos.domain.executor.UseCaseExecutor;
import com.alvaro.opos.domain.interactor.exercise.DeleteExercise;
import com.alvaro.opos.domain.interactor.exercise.GetExercise;
import com.alvaro.opos.domain.interactor.exercise.GetExercises;
import com.alvaro.opos.domain.interactor.exercise.SaveExercise;

import dagger.Module;
import dagger.Provides;

@Module
class DomainModule {
    @Provides
    GetExercise providesGetExercise(ExerciseRepository repository,
                                    UseCaseExecutor useCaseExecutor) {
        return new GetExercise(repository, useCaseExecutor);
    }

    @Provides
    GetExercises providesGetExercises(ExerciseRepository repository,
                                      UseCaseExecutor useCaseExecutor) {
        return new GetExercises(repository, useCaseExecutor);
    }

    @Provides
    SaveExercise providesSaveExercise(ExerciseRepository repository,
                                      UseCaseExecutor useCaseExecutor) {
        return new SaveExercise(repository, useCaseExecutor);
    }

    @Provides
    DeleteExercise providesDeleteExercise(ExerciseRepository repository,
                                          UseCaseExecutor useCaseExecutor) {
        return new DeleteExercise(repository, useCaseExecutor);
    }
}
