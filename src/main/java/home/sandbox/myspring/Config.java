package home.sandbox.myspring;


public interface Config {
    <T> Class<T> getImplClass(Class<T> type);
}
