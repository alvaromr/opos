package com.alvaro.opos.presentation.presenter;

import com.alvaro.opos.domain.model.exercise.Exercise;
import com.alvaro.opos.domain.interactor.Handler;
import com.alvaro.opos.domain.interactor.exercise.GetExercise;
import com.alvaro.opos.domain.interactor.exercise.SaveExercise;

import java.lang.ref.WeakReference;
import java.util.List;

public class ExerciseSavePresenter implements Presenter<ExerciseSavePresenter.View> {

    private final SaveExercise saveExercise;
    private final GetExercise getExercise;
    private WeakReference<ExerciseSavePresenter.View> view;
    private Long exerciseId;

    public ExerciseSavePresenter(SaveExercise saveExercise, GetExercise getExercise, Long id) {
        this.saveExercise = saveExercise;
        this.getExercise = getExercise;
        this.exerciseId = id;
    }

    @Override
    public void viewReady() {
        if (exerciseId == -1L) {
            return;
        }
        getExercise.execute(new Handler<Exercise>() {
            @Override
            public void handle(Exercise result) {
                ExerciseSavePresenter.View v = view.get();
                if (v != null) {
                    v.display(result);
                }
            }

            @Override
            public void error(Exception exception) {
                ExerciseSavePresenter.View v = view.get();
                if (v != null) {
                    v.showErrorMessage(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        }, this.exerciseId);
    }

    public void onSave(String imagePath, String question, List<String> realAnswers, List<String> possibleAnswers) {
        Exercise exercise = new Exercise(exerciseId, imagePath, question, realAnswers, possibleAnswers);
        saveExercise.execute(new Handler<Exercise>() {
            @Override
            public void handle(Exercise saved) {
                View v = view.get();
                if (v != null) {
                    v.navigateToList();
                }
            }

            @Override
            public void error(Exception exception) {
                View v = view.get();
                if (v != null) {
                    v.showErrorMessage(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        }, exercise);
    }

    @Override
    public void setView(View view) {
        this.view = new WeakReference<>(view);
    }

    public interface View extends Presenter.View {
        void navigateToList();
        void display(Exercise exercise);
    }
}
