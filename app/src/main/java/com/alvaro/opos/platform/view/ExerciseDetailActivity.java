package com.alvaro.opos.platform.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alvaro.opos.R;
import com.alvaro.opos.domain.model.exercise.Exercise;
import com.alvaro.opos.presentation.presenter.ExerciseDetailPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class ExerciseDetailActivity extends DaggerAppCompatActivity implements ExerciseDetailPresenter.View {

    public static final String EXTRA_ID = "id";

    @Inject ExerciseDetailPresenter presenter;


    @BindView(R.id.textViewQuestion) TextView textView;

    public static void launch(Activity activity, Long id) {
        Intent intent = new Intent(activity, ExerciseDetailActivity.class);
        intent.putExtra(ExerciseDetailActivity.EXTRA_ID, id);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);
        ButterKnife.bind(this);

        informPresenterViewIsReady();
    }

    @Override
    public void display(Exercise exercise) {
        textView.setText(exercise.getQuestion());
    }

    @Override
    public void navigateToList() {
        ExerciseListActivity.launch(this);
    }

    @Override
    public void navigateToSave(Long exerciseId) {
        ExerciseSaveActivity.launch(this, exerciseId);
    }

    @Override
    public void informPresenterViewIsReady() {
        presenter.viewReady();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, getString(R.string.exercise_detail_error), Toast.LENGTH_LONG).show();
    }

    public void onRemoveButtonClick(View v){
        presenter.onRemoveButtonClicked();
    }

    public void onEditButtonClick(View v){
        presenter.onEditButtonClicked();
    }
}
