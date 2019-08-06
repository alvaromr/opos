package com.alvaro.opos.platform.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alvaro.opos.R;
import com.alvaro.opos.presentation.presenter.ExerciseListPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class ExerciseListActivity extends DaggerAppCompatActivity implements ExerciseListPresenter.View {

    @Inject ExerciseListPresenter presenter;

    @BindView(R.id.exercises_list_view) RecyclerView listView;


    ExerciseAdapter adapter;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, ExerciseListActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercise);
        ButterKnife.bind(this);

        setUpAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();

        informPresenterViewIsReady();
    }

    private void setUpAdapter() {
        adapter = new ExerciseAdapter(presenter);
        listView.setLayoutManager(new GridLayoutManager(this, 1));
        listView.setAdapter(adapter);
    }

    @Override
    public void informPresenterViewIsReady() {
        presenter.viewReady();
    }

    @Override
    public void refreshView() {
        adapter.refreshData();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, getString(R.string.exercise_list_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToDetail(Long id) {
        ExerciseDetailActivity.launch(this, id);
    }

    @Override
    public void navigateToSave() {
        ExerciseSaveActivity.launch(this);
    }

    public void onFloatingButtonClick(View v){
        presenter.onFloatingButtonClick();
    }
}
