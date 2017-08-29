package home.sandbox.myspring;

import java.lang.reflect.Method;

public class AnotherProxyTestAnnotationProxyConfigurator implements ProxyConfigurator {
    @Override
    public MethodProducer proxy(Class<?> type, Method originalMethod, AttachedMethod prev) {
        if (!originalMethod.isAnnotationPresent(AnotherProxyTestAnnotation.class)) {
            return null;
        }
        return (object, proxy) -> (args) -> {
            ProxyTestInvocationHandler.getInstance().call("AnotherProxyTest");
            return prev.invoke(args);
        };
    }
}
