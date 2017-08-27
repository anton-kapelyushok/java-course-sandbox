package home.sandbox.exercises;

import lombok.Getter;
import lombok.ToString;
import lombok.Value;

@Value
public class Exercise {
    private String question;
    private String answer;
}
