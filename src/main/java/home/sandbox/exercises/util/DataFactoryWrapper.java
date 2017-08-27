package home.sandbox.exercises.util;


import org.fluttercode.datafactory.impl.DataFactory;

public class DataFactoryWrapper {
    private static DataFactory dataFactory = new DataFactory();
    public static DataFactory get() {
        return dataFactory;
    }
}
