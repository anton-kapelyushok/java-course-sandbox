package home.sandbox.myspring;

import java.lang.reflect.Method;

public class BenchmarkAnnotationProxyConfigurator implements ProxyConfigurator {
    @Override
    public MethodProducer proxy(Class<?> type, Method method, AttachedMethod prev) {
        if (!method.isAnnotationPresent(Benchmark.class)) {
            return null;
        }
        return (object, proxy) -> (args) -> {
            long start = System.nanoTime();
            Object result = prev.invoke(args);
            long end = System.nanoTime();
            System.out.println("BenchmarkAnnotationProxyConfigurator: " + (end - start));
            return result;
        };
    }
}