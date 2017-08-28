package home.sandbox.myspring;

import java.lang.reflect.Method;

public class AnotherProxyTestAnnotationProxyConfigurator implements ProxyConfigurator {
    @Override
    public DetachedMethod proxy(Method originalMethod, DetachedMethod prev) {
        if (!originalMethod.isAnnotationPresent(AnotherProxyTest.class)) {
            return prev;
        }
        return (obj, args) -> {
            ProxyTestInvocationHandler.getInstance().call("AnotherProxyTest" + originalMethod.getName());
            return prev.invoke(obj, args);
        };
    }
}
