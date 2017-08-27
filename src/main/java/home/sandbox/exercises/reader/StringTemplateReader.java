package home.sandbox.exercises.reader;

import home.sandbox.exercises.ExerciseTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringTemplateReader implements TemplateReader<String> {
    private String cleanTemplate(String templateString) {
        templateString = templateString.toLowerCase();
        templateString =  templateString.replaceAll("\\s+", "");
        return templateString;
    }

    @Override
    public ExerciseTemplate readTemplate(String templateString) {
        templateString = cleanTemplate(templateString);
        String[] templateParts = templateString.split(";");
        String questionWithAnswerTemplate = templateParts[0];
        String[] questionParts = questionWithAnswerTemplate.split("=");


        String question = questionParts[0];
        String answerVariable = questionParts[1];
        List<String> restrictions = Arrays.asList(templateParts).subList(1, templateParts.length);
        List<String> variables = questionWithAnswerTemplate.chars()
                .mapToObj(i -> (char)i)
                .filter(c -> 'a' <= c && c <= 'z')
                .map(Object::toString)
                .collect(Collectors.toList());


        return ExerciseTemplate.builder()
                .question(question)
                .answerVariable(answerVariable)
                .restrictions(restrictions)
                .variables(variables)
                .build();
    }

    public static void main(String[] args) {
        System.out.println(new StringTemplateReader().readTemplate("a+b=c;a<2;b>2"));
    }
}
