package home.sandbox.exercises;

import home.sandbox.exercises.evaluator.Exp4jEvaluator;
import home.sandbox.exercises.generator.ExerciseGenerator;
import home.sandbox.exercises.generator.FromTemplateGenerator;
import home.sandbox.exercises.reader.StringTemplateReader;
import home.sandbox.exercises.variablegenerator.VariableGeneratorImpl;

public class Main {
    public static void main(String[] args) {
        VariableGeneratorImpl variableGenerator = new VariableGeneratorImpl(new Exp4jEvaluator());
        StringTemplateReader templateReader = new StringTemplateReader();

        ExerciseTemplate template1 = templateReader.readTemplate("d+a=c");
        ExerciseTemplate template2 = templateReader.readTemplate("a-b+c-d=e");

        ExerciseGenerator generator1 = new FromTemplateGenerator(template1, variableGenerator);
        ExerciseGenerator generator2 = new FromTemplateGenerator(template2, variableGenerator);
        System.out.println(generator1.generate());
        System.out.println(generator1.generate());
        System.out.println(generator1.generate());
        System.out.println(generator2.generate());
        System.out.println(generator2.generate());
    }
}
