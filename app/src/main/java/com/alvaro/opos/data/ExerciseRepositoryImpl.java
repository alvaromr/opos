package com.alvaro.opos.data;

import com.alvaro.opos.data.entity.ExerciseEntity;
import com.alvaro.opos.data.entity.mapper.Mapper;
import com.alvaro.opos.data.source.local.LocalDataSource;
import com.alvaro.opos.domain.model.exercise.Exercise;
import com.alvaro.opos.domain.model.exercise.ExerciseRepository;

import java.util.List;

public class ExerciseRepositoryImpl implements ExerciseRepository {

    private Mapper<Exercise, ExerciseEntity> exerciseMapper;
    private LocalDataSource<ExerciseEntity> localDataSource;

    public ExerciseRepositoryImpl(Mapper<Exercise, ExerciseEntity> exerciseMapper,
                                  LocalDataSource<ExerciseEntity> localDataSource) {
        this.exerciseMapper = exerciseMapper;
        this.localDataSource = localDataSource;
    }

    @Override
    public List<Exercise> list(Void v) {
        List<ExerciseEntity> list = localDataSource.getAll();
        return exerciseMapper.from(list);
    }

    @Override
    public Exercise get(Long id) {
        ExerciseEntity entity = localDataSource.get(id);
        return exerciseMapper.from(entity);
    }

    @Override
    public Exercise save(Exercise exercise) {
        ExerciseEntity exerciseEntity = exerciseMapper.transform(exercise);
        ExerciseEntity saved = localDataSource.save(exerciseEntity);
        return exerciseMapper.from(saved);
    }

    @Override
    public void delete(Long id) {
        ExerciseEntity entity = localDataSource.get(id);
        localDataSource.delete(entity);
    }
}
