package home.sandbox.myspring;

import java.lang.reflect.Method;

public class ProxyTestAnnotationProxyConfigurator implements ProxyConfigurator {
    @Override
    public AttachedMethod proxy(Method originalMethod, AttachedMethod prev) {
        if (!originalMethod.isAnnotationPresent(ProxyTest.class)) {
            return prev;
        }
        return (args) -> {
            ProxyTestInvocationHandler.getInstance().call("ProxyTest" + originalMethod.getName());
            return prev.invoke(args);
        };
    }
}
