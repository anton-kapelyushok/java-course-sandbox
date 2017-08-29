package home.sandbox.myspring;

import java.lang.reflect.Method;

public interface ProxyConfigurator {
    MethodProducer proxy(Class<?> type, Method originalMethod, AttachedMethod prev);
}
