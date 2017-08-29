package home.sandbox.myspring;

import java.lang.reflect.Method;

public class ProxyTestAnnotationProxyConfigurator implements ProxyConfigurator {
    @Override
    public MethodProducer proxy(Class<?> type, Method originalMethod, AttachedMethod prev) {
        if (!originalMethod.isAnnotationPresent(ProxyTestAnnotation.class)) {
            return null;
        }
        return (object, proxy) -> (args) -> {
            ProxyTestInvocationHandler.getInstance().call("ProxyTest");
            return prev.invoke(args);
        };
    }
}
