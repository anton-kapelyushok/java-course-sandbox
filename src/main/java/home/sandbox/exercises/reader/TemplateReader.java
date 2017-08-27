package home.sandbox.exercises.reader;

import home.sandbox.exercises.ExerciseTemplate;

public interface TemplateReader<T> {
    ExerciseTemplate readTemplate(T template);
}
