package home.sandbox.injection;

import home.sandbox.injection.handlers.ClickHandler;
import home.sandbox.injection.handlers.KeyDownHandler;
import home.sandbox.injection.handlers.KeyUpHandler;
import lombok.SneakyThrows;
import org.fluttercode.datafactory.impl.DataFactory;

import java.util.Arrays;
import java.util.List;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        Service service = Service.builder()
                .on("click", new ClickHandler())
                .on("keydown", new KeyDownHandler())
                .on("keyup", new KeyUpHandler())
                .build();

        DataFactory dataFactory = new DataFactory();

        List<String> events = Arrays.asList("click", "keydown", "keyup");

        while (true) {
            Event event = new Event(dataFactory.getItem(events));
            service.serve(event);
            Thread.sleep(200);
        }
    }
}
