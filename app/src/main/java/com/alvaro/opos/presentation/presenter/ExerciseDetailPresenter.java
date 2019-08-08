package com.alvaro.opos.presentation.presenter;

import com.alvaro.opos.domain.interactor.Handler;
import com.alvaro.opos.domain.interactor.exercise.DeleteExercise;
import com.alvaro.opos.domain.interactor.exercise.GetExercise;
import com.alvaro.opos.domain.model.exercise.Exercise;

import java.lang.ref.WeakReference;
import java.util.List;

public class ExerciseDetailPresenter implements Presenter<ExerciseDetailPresenter.View> {

    private final GetExercise getExercise;
    private final DeleteExercise deleteExercise;
    private Long exerciseId;
    private WeakReference<View> view;

    public ExerciseDetailPresenter(GetExercise getExercise,
                                   DeleteExercise deleteExercise,
                                   Long exerciseId) {
        this.getExercise = getExercise;
        this.exerciseId = exerciseId;
        this.deleteExercise = deleteExercise;
    }

    @Override
    public void viewReady() {
        getExercise.execute(new Handler<Exercise>() {
            @Override
            public void handle(Exercise result) {
                View v = view.get();
                if (v != null) {
                    v.display(result);
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
        }, this.exerciseId);
    }

    @Override
    public void setView(View view) {
        this.view = new WeakReference<>(view);
    }

    public void onRemoveButtonClicked() {
        deleteExercise.execute(new Handler<Void>() {
            @Override
            public void handle(Void result) {
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
        }, this.exerciseId);
    }

    public void onEditButtonClicked() {
        View v = view.get();
        if (v != null) {
            v.navigateToSave(exerciseId);
        }
    }

    public void onPossibleAnswerSelected(Exercise exercise, int position) {
        List<String> possibleAnswers = exercise.getPossibleAnswers();
        int correctAnswer = exercise.getCorrectAnswer();
        String selectedAnswer = possibleAnswers.get(position);
        View v = view.get();
        if (v != null) {
            if (correctAnswer == position) {
                v.onCorrectAnswerSelected(selectedAnswer);
            } else {
                v.onWrongAnswerSelected(selectedAnswer);
            }
        }

    }

    public interface View extends Presenter.View {
        void display(Exercise exercise);
        void navigateToList();
        void navigateToSave(Long exerciseId);
        void onCorrectAnswerSelected(String selectedAnswer);
        void onWrongAnswerSelected(String selectedAnswer);
    }
}
