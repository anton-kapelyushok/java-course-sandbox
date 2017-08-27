package home.sandbox.injection;

import home.sandbox.injection.handlers.Handler;
import lombok.Builder;
import lombok.Singular;

import java.util.HashMap;
import java.util.Map;

@Builder
public class Service {
    @Singular("on")
    private Map<String, Handler> handlers = new HashMap<>();
    public void serve(Event event) {
        Handler handler = handlers.get(event.getType());
        if (handler != null) {
            handler.handle(event);
        }
    }
}
