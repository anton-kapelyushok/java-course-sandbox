package home.sandbox.injection;

import home.sandbox.injection.handlers.Handler;
import lombok.Value;

import java.util.HashMap;
import java.util.Map;

@Value
public class Event {
    private String type;
}
