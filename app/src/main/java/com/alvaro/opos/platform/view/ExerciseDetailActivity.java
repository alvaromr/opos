package com.alvaro.opos.platform.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alvaro.opos.R;
import com.alvaro.opos.domain.model.exercise.Exercise;
import com.alvaro.opos.presentation.presenter.ExerciseDetailPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class ExerciseDetailActivity extends DaggerAppCompatActivity implements ExerciseDetailPresenter.View {

    public static final String EXTRA_ID = "id";

    @Inject ExerciseDetailPresenter presenter;


    @BindView(R.id.textViewQuestion) TextView textView;
    @BindView(R.id.radioGroup) RadioGroup radioGroup;

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
        List<String> possibleAnswers = exercise.getPossibleAnswers();

        for (int i = 0; i < possibleAnswers.size(); i++) {
            String possibleAnswer = possibleAnswers.get(i);
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(possibleAnswer);
            radioButton.setId(i);
            radioGroup.addView(radioButton);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                presenter.onPossibleAnswerSelected(exercise, checkedId);
            }
        });

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
    public void onCorrectAnswerSelected(String selectedAnswer) {
        Toast.makeText(this, selectedAnswer, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWrongAnswerSelected(String selectedAnswer) {
        Toast.makeText(this, selectedAnswer + " (X)", Toast.LENGTH_SHORT).show();
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
