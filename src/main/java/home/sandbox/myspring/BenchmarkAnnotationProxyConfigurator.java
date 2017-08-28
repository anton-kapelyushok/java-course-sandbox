package home.sandbox.myspring;

import java.lang.reflect.Method;

public class BenchmarkAnnotationProxyConfigurator implements ProxyConfigurator {
    @Override
    public AttachedMethod proxy(Method method, AttachedMethod prev) {
        if (!method.isAnnotationPresent(Benchmark.class)) {
            return prev;
        }
        return (args) -> {
            long start = System.nanoTime();
            Object result = prev.invoke(args);
            long end = System.nanoTime();
            System.out.println("BenchmarkAnnotationProxyConfigurator: " + (end - start));
            return result;
        };
    }
}
