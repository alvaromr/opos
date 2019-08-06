package com.alvaro.opos.data.source.local;

import com.alvaro.opos.data.entity.ExerciseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class InMemoryExerciseLocalDatasource implements LocalDataSource<ExerciseEntity> {
    private static final HashMap<Long, ExerciseEntity> exercises = new HashMap<>();

    @Override
    public List<ExerciseEntity> getAll() {
        return new ArrayList<>(exercises.values());
    }

    @Override
    public ExerciseEntity get(Long id) {
        return exercises.get(id);
    }

    @Override
    public ExerciseEntity save(ExerciseEntity exerciseEntity) {
        if(exerciseEntity.id == null) {
            exerciseEntity.id = new Random().nextLong();
        }
        exercises.put(exerciseEntity.id, exerciseEntity);
        return get(exerciseEntity.id);
    }

    @Override
    public void deleteAll() {
        exercises.clear();
    }

    @Override
    public void delete(ExerciseEntity exerciseEntity) {
        exercises.remove(exerciseEntity.id);
    }
}
