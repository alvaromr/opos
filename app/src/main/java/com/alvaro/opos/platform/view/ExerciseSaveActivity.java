package com.alvaro.opos.platform.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alvaro.opos.R;
import com.alvaro.opos.domain.model.exercise.Exercise;
import com.alvaro.opos.presentation.presenter.ExerciseSavePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class ExerciseSaveActivity extends DaggerAppCompatActivity implements ExerciseSavePresenter.View {
    public static final String EXTRA_ID = "id";

    @Inject ExerciseSavePresenter presenter;

    @BindView(R.id.editTextQuestion) EditText editTextQuestion;

    public static void launch(Activity activity) {
        launch(activity, -1L);
    }

    public static void launch(Activity activity, Long id) {
        Intent intent = new Intent(activity, ExerciseSaveActivity.class);
        intent.putExtra(ExerciseDetailActivity.EXTRA_ID, id);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_new);
        ButterKnife.bind(this);

        informPresenterViewIsReady();
    }


    @Override
    public void informPresenterViewIsReady() {
        presenter.viewReady();
    }

    @Override
    public void display(Exercise exercise) {
        editTextQuestion.setText(exercise.getQuestion());
    }

    @Override
    public void navigateToList() {
        ExerciseListActivity.launch(this);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, getString(R.string.exercise_save_error), Toast.LENGTH_LONG).show();
    }

    public void onSaveButtonClick(View v) {
        String question = editTextQuestion.getText().toString();
        presenter.onSave("", question, null, null);
    }
}
