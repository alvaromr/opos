package com.alvaro.opos.data.entity.mapper;

import com.alvaro.opos.data.entity.ExerciseEntity;
import com.alvaro.opos.domain.model.exercise.Exercise;

import java.util.ArrayList;
import java.util.List;

import static com.alvaro.opos.data.entity.mapper.MapperUtils.getListFromString;
import static com.alvaro.opos.data.entity.mapper.MapperUtils.getStringFromList;

public class ExerciseMapper implements Mapper<Exercise, ExerciseEntity> {
    @Override
    public List<Exercise> from(List<ExerciseEntity> list) {
        List<Exercise> result = new ArrayList<>();
        for (ExerciseEntity entity : list) {
            result.add(from(entity));
        }
        return result;
    }

    @Override
    public Exercise from(ExerciseEntity entity) {
        if (entity == null) {
            return null;
        }
        try {
            return new Exercise(
                    entity.id,
                    entity.image,
                    entity.question,
                    entity.correctAnswer,
                    getListFromString(entity.possibleAnswers)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ExerciseEntity transform(Exercise exercise) {
        ExerciseEntity entity = new ExerciseEntity();
        entity.id = exercise.getId();
        entity.question = exercise.getQuestion();
        entity.image = exercise.getImage();
        entity.correctAnswer = exercise.getCorrectAnswer();
        entity.possibleAnswers = getStringFromList(exercise.getPossibleAnswers());
        return entity;
    }
}
