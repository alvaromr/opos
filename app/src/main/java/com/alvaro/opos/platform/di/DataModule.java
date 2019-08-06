package com.alvaro.opos.platform.di;

import android.content.Context;

import com.alvaro.opos.data.ExerciseRepositoryImpl;
import com.alvaro.opos.data.entity.ExerciseEntity;
import com.alvaro.opos.data.entity.mapper.ExerciseMapper;
import com.alvaro.opos.data.entity.mapper.Mapper;
import com.alvaro.opos.data.source.local.LocalDataSource;
import com.alvaro.opos.data.source.local.db.DatabaseHelper;
import com.alvaro.opos.data.source.local.db.DbExerciseLocalDatasource;
import com.alvaro.opos.domain.model.exercise.Exercise;
import com.alvaro.opos.domain.model.exercise.ExerciseRepository;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module()
class DataModule {
    @Provides
    @Singleton
    ExerciseRepository provideExerciseRepository(Mapper<Exercise, ExerciseEntity> exerciseMapper,
                                                 LocalDataSource<ExerciseEntity> localDataSource) {
        return new ExerciseRepositoryImpl(exerciseMapper, localDataSource);
    }

//    @Provides
//    @Singleton
//    LocalDataSource<ExerciseEntity> provideExerciseLocalDataSource() {
//        LocalDataSource<ExerciseEntity> datasource = new InMemoryExerciseLocalDatasource();
//        generateFixtures(datasource);
//        return datasource;
//    }

    @Provides
    @Singleton
    LocalDataSource<ExerciseEntity> provideExerciseLocalDataSource(DatabaseHelper sqLiteOpenHelper) {
        LocalDataSource<ExerciseEntity> datasource = new DbExerciseLocalDatasource(sqLiteOpenHelper);
        generateFixtures(datasource); //TODO temp method
        return datasource;
    }

    @Provides
    @Singleton
    DatabaseHelper provideSQLiteOpenHelper(Context context) {
        return new DatabaseHelper(context);
    }


    @Provides
    @Singleton
    Mapper<Exercise, ExerciseEntity> provideExerciseMapper() {
        return new ExerciseMapper();
    }

    private void generateFixtures(LocalDataSource<ExerciseEntity> datasource) {
        for (int i = 0; i < 4; i++) {
            ExerciseEntity e = new ExerciseEntity();
            e.id = (long) i;
            e.question = "Question " + i;
            e.realAnswers = new ArrayList<>();

            datasource.save(e);
        }
    }
}
