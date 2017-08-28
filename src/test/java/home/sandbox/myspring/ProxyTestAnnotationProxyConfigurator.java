package home.sandbox.myspring;

import java.lang.reflect.Method;

public class ProxyTestAnnotationProxyConfigurator implements ProxyConfigurator {
    @Override
    public DetachedMethod proxy(Method originalMethod, DetachedMethod prev) {
        if (!originalMethod.isAnnotationPresent(ProxyTest.class)) {
            return prev;
        }
        return (obj, args) -> {
            ProxyTestInvocationHandler.getInstance().call("ProxyTest" + originalMethod.getName());
            return prev.invoke(obj, args);
        };
    }
}
