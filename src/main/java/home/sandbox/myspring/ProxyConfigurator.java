package home.sandbox.myspring;

import java.lang.reflect.Method;

public interface ProxyConfigurator {
    AttachedMethod proxy(Method originalMethod, AttachedMethod prev);
}
