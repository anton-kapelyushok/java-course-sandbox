package home.sandbox.exercises.variablegenerator;

import java.util.Map;

public interface VariableGenerator<T> {
    Map<String, Double> generateValues(T template);
}
