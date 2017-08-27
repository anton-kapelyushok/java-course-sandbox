package home.sandbox.tailrecursion;

import lombok.Builder;
import lombok.Singular;

import java.util.HashMap;
import java.util.Map;

@Builder
public class Arguments  {
    @Singular("arg")
    private Map<String, Object> args = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T get(String name) {
        return (T) args.get(name);
    }
}