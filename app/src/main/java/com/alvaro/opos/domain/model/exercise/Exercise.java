package com.alvaro.opos.domain.model.exercise;

import java.util.List;

public class Exercise {
    private final Long id;
    private final String image;
    private final String question;
    private final int correctAnswer;
    private final List<String> possibleAnswers;

    public Exercise(Long id, String imagePath, String question, int correctAnswer, List<String> possibleAnswers) {
        this.id = id;
        this.image = imagePath;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.possibleAnswers = possibleAnswers;
    }

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getQuestion() {
        return question;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exercise exercise = (Exercise) o;

        return id.equals(exercise.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
