package com.alvaro.opos.presentation.presenter;

import com.alvaro.opos.domain.model.exercise.Exercise;
import com.alvaro.opos.domain.interactor.Handler;
import com.alvaro.opos.domain.interactor.exercise.GetExercises;

import java.lang.ref.WeakReference;
import java.util.List;

public class ExerciseListPresenter implements Presenter<ExerciseListPresenter.View> {

    private final GetExercises useCase;
    private WeakReference<View> view;
    private List<Exercise> exercises;

    public ExerciseListPresenter(GetExercises useCase) {
        this.useCase = useCase;
    }

    @Override
    public void viewReady() {
        useCase.execute(new Handler<List<Exercise>>() {
            @Override
            public void handle(List<Exercise> result) {
                View v = view.get();
                if (v != null) {
                    exercises = result;
                    v.refreshView();
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
        }, null);
    }

    public void onItemClick(int position) {
        Exercise exercise = exercises.get(position);
        View v = view.get();
        if (v != null) {
            v.navigateToDetail(exercise.getId());
        }
    }

    public void onFloatingButtonClick(){
        View v = view.get();
        if (v != null) {
            v.navigateToSave();
        }
    }

    public void configureCell(CellView cellView, int position) {
        Exercise exercise = exercises.get(position);
        cellView.display(exercise);
    }

    @Override
    public void setView(View view) {
        this.view = new WeakReference<>(view);
    }

    public int getItemsCount() {
        return exercises != null ? exercises.size() : 0;
    }

    public interface View extends Presenter.View {
        void refreshView();
        void navigateToDetail(Long id);
        void navigateToSave();
    }

    public interface CellView {
        void display(Exercise exercise);
    }
}
