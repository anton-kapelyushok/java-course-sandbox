package home.sandbox.myspring;

import java.lang.reflect.Method;

public class AnotherProxyTestAnnotationProxyConfigurator implements ProxyConfigurator {
    @Override
    public AttachedMethod proxy(Method originalMethod, AttachedMethod prev) {
        if (!originalMethod.isAnnotationPresent(AnotherProxyTest.class)) {
            return prev;
        }
        return (args) -> {
            ProxyTestInvocationHandler.getInstance().call("AnotherProxyTest" + originalMethod.getName());
            return prev.invoke(args);
        };
    }
}
