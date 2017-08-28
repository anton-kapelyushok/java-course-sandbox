package home.sandbox.myspring;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BenchmarkAnnotationProxyConfigurator implements ProxyConfigurator {
    @Override
    public DetachedMethod proxy(Method method, DetachedMethod prev) {
        if (!method.isAnnotationPresent(Benchmark.class)) {
            return prev;
        }
        return (obj, args) -> {
            long start = System.nanoTime();
            Object result = prev.invoke(obj, args);
            long end = System.nanoTime();
            System.out.println("BenchmarkAnnotationProxyConfigurator: " + (end - start));
            return result;
        };
    }
}
