package home.sandbox.exercises;

import lombok.*;

import java.util.List;

@Builder @Value
public class ExerciseTemplate {
    @NonNull private String question;
    @NonNull private String answerVariable;
    @NonNull @Singular private List<String> variables;
    @NonNull @Singular private List<String> restrictions;
}
