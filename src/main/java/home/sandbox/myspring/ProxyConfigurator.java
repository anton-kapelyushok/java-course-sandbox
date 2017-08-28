package home.sandbox.myspring;

import java.lang.reflect.Method;

public interface ProxyConfigurator {
    DetachedMethod proxy(Method originalMethod, DetachedMethod prev);
}
