package home.sandbox.exercises.variablegenerator;

import home.sandbox.exercises.evaluator.Evaluator;
import home.sandbox.exercises.ExerciseTemplate;
import home.sandbox.exercises.util.DataFactoryWrapper;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class VariableGeneratorImpl implements VariableGenerator<ExerciseTemplate> {
    private Evaluator<String> evaluator;
    @Override
    public Map<String, Double> generateValues(ExerciseTemplate template) {
        Map<String, Double> variables = new HashMap<>();
        template.getVariables()
                .forEach(variable ->
                        variables.put(variable, (double) DataFactoryWrapper.get().getNumberBetween(0, 50))
                );
        double answer = evaluator.evaluateExpression(template.getQuestion(), variables);
        variables.put(template.getAnswerVariable(), answer);
        return variables;
    }
}
