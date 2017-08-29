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
        ExerciseTemplate template3 = templateReader.readTemplate("c/6+a/b=e");

        ExerciseGenerator generator1 = new FromTemplateGenerator(template1, variableGenerator);
        ExerciseGenerator generator2 = new FromTemplateGenerator(template2, variableGenerator);
        ExerciseGenerator generator3 = new FromTemplateGenerator(template3, variableGenerator);
        System.out.println(generator1.generate());
        System.out.println(generator1.generate());
        System.out.println(generator1.generate());
        System.out.println(generator2.generate());
        System.out.println(generator3.generate());
    }
}
