package com.alvaro.opos.platform.di;

import com.alvaro.opos.platform.view.ExerciseDetailActivity;
import com.alvaro.opos.platform.view.ExerciseListActivity;
import com.alvaro.opos.platform.view.ExerciseSaveActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = ActivityModule.class)
    abstract ExerciseListActivity bindExerciseListActivity();

    @ContributesAndroidInjector(modules = ActivityModule.class)
    abstract ExerciseDetailActivity bindExerciseDetailActivity();

    @ContributesAndroidInjector(modules = ActivityModule.class)
    abstract ExerciseSaveActivity bindExerciseSaveActivity();

}