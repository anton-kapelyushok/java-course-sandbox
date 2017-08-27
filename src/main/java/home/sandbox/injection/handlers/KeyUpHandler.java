package home.sandbox.injection.handlers;

import home.sandbox.injection.Event;

public class KeyUpHandler implements Handler {
    @Override
    public void handle(Event event) {
        System.out.println("Key up");
    }
}
