package home.sandbox.exercises.generator;

import home.sandbox.exercises.ExerciseTemplate;
import home.sandbox.exercises.Exercise;
import home.sandbox.exercises.variablegenerator.VariableGenerator;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class FromTemplateGenerator implements ExerciseGenerator {
    private ExerciseTemplate template;
    private VariableGenerator<ExerciseTemplate> variableGenerator;

    @Override
    public Exercise generate() {
        Map<String, Double> variables = variableGenerator.generateValues(template);
        final String[] question = {template.getQuestion()};
        variables.forEach((name, value) -> question[0] = question[0].replaceAll(name, Double.toString(value)));
        String answer = Double.toString(variables.get(template.getAnswerVariable()));
        return new Exercise(question[0], answer);
    }
}
