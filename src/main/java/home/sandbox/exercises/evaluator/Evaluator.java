package home.sandbox.exercises.evaluator;

import java.util.Map;

public interface Evaluator<T> {
    double evaluateExpression(T expression, Map<String, Double> variables);
    boolean evaluateRestriction(T restriction, Map<String, Double> variables);
}
