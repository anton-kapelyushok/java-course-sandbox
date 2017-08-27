package home.sandbox.exercises.evaluator;

import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Map;

public class Exp4jEvaluator implements Evaluator<String> {

    @Override
    public double evaluateExpression(String expression, Map<String, Double> variables) {
          return new ExpressionBuilder(expression)
                  .variables(variables.keySet())
                  .build()
                  .setVariables(variables)
                  .evaluate();
    }

    @Override
    public boolean evaluateRestriction(String restriction, Map<String, Double> variables) {
        return true;
    }
}
