package com.alvaro.opos.platform.di;

import com.alvaro.opos.domain.interactor.exercise.DeleteExercise;
import com.alvaro.opos.domain.interactor.exercise.GetExercise;
import com.alvaro.opos.domain.interactor.exercise.GetExercises;
import com.alvaro.opos.domain.interactor.exercise.SaveExercise;
import com.alvaro.opos.platform.view.ExerciseDetailActivity;
import com.alvaro.opos.platform.view.ExerciseListActivity;
import com.alvaro.opos.platform.view.ExerciseSaveActivity;
import com.alvaro.opos.presentation.presenter.ExerciseDetailPresenter;
import com.alvaro.opos.presentation.presenter.ExerciseListPresenter;
import com.alvaro.opos.presentation.presenter.ExerciseSavePresenter;

import dagger.Module;
import dagger.Provides;

@Module
class ActivityModule {
    @Provides
    ExerciseListPresenter provideExerciseListPresenter(ExerciseListActivity activity, GetExercises getExercises){
        ExerciseListPresenter presenter = new ExerciseListPresenter(getExercises);
        presenter.setView(activity);
        return presenter;
    }

    @Provides
    ExerciseDetailPresenter provideExerciseDetailPresenter(ExerciseDetailActivity activity, GetExercise getExercise, DeleteExercise deleteExercise){
        Long id = activity.getIntent().getLongExtra(ExerciseDetailActivity.EXTRA_ID, -1L);
        ExerciseDetailPresenter presenter = new ExerciseDetailPresenter(getExercise, deleteExercise, id);
        presenter.setView(activity);
        return presenter;
    }

    @Provides
    ExerciseSavePresenter provideExerciseSavePresenter(ExerciseSaveActivity activity, SaveExercise saveExercise, GetExercise getExercise){
        Long id = activity.getIntent().getLongExtra(ExerciseSaveActivity.EXTRA_ID, -1L);
        ExerciseSavePresenter presenter = new ExerciseSavePresenter(saveExercise, getExercise, id);
        presenter.setView(activity);
        return presenter;
    }
}
