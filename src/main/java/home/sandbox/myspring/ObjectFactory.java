package home.sandbox.myspring;

import lombok.SneakyThrows;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class ObjectFactory {
    private static ObjectFactory ourInstance = new ObjectFactory();
    private Config config = new JavaConfig();
    private Reflections scanner = new Reflections("home.sandbox");
    private List<ObjectConfigurator> objectConfigurators = new ArrayList<>();
    private List<ProxyConfigurator> proxyConfigurators = new ArrayList<>();

    public static ObjectFactory getInstance() {
        return ourInstance;
    }

    @SneakyThrows
    private ObjectFactory() {
        resolveObjectConfigurators();
        resolveProxyConfigurators();
    }

    private void resolveObjectConfigurators() throws InstantiationException, IllegalAccessException {
        Set<Class<? extends ObjectConfigurator>> classes = scanner.getSubTypesOf(ObjectConfigurator.class);
        for (Class<? extends ObjectConfigurator> aClass : classes) {
            objectConfigurators.add(aClass.newInstance());
        }
    }


    private void resolveProxyConfigurators() throws InstantiationException, IllegalAccessException {
        Set<Class<? extends ProxyConfigurator>> classes = scanner.getSubTypesOf(ProxyConfigurator.class);
        for (Class<? extends ProxyConfigurator> aClass : classes) {
            proxyConfigurators.add(aClass.newInstance());
        }
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public <T> T createObject(Class<T> type) {
        type = resolveImpl(type);
        final T t = type.newInstance();
        configure(t);
        invokeInitMethod(type, t);

        return configureProxyIfNeeded(t);
    }

    @SuppressWarnings("unchecked")
    private <T> T configureProxyIfNeeded(T t) {
        Class<?> type = t.getClass();
        Set<Method> methods = ReflectionUtils.getMethods(type);
        Map<String, DetachedMethod> invocationMap = new HashMap<>();

        boolean needProxy = false;
        for (Method originalMethod : methods) {
            DetachedMethod defaultMethod = new DetachedMethod() {
                @Override
                @SneakyThrows
                public Object invoke(Object obj, Object[] args) {
                    return originalMethod.invoke(obj, args);
                }
            };
            DetachedMethod newMethod = defaultMethod;
            for (ProxyConfigurator proxyConfigurator : proxyConfigurators) {
                newMethod = proxyConfigurator.proxy(originalMethod, newMethod);
            }
            if (defaultMethod != newMethod) {
                needProxy = true;
            }
            invocationMap.put(originalMethod.getName(), newMethod);
        }

        if (!needProxy) {
            return t;
        }

        return (T) Proxy.newProxyInstance(
                type.getClassLoader(),
                type.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return invocationMap.get(method.getName()).invoke(t, args);
                    }
                }
        );
    }


    private <T> void invokeInitMethod(Class<T> type, T t) throws IllegalAccessException, InvocationTargetException {
        Set<Method> methods = ReflectionUtils.getAllMethods(type, method -> method.isAnnotationPresent(PostConstruct.class));
        for (Method method : methods) {
            method.setAccessible(true);
            method.invoke(t);
        }
    }

    private <T> void configure(T t) {
        for (ObjectConfigurator objectConfigurator : objectConfigurators) {
            objectConfigurator.configure(t);
        }
    }

    private <T> Class<T> resolveImpl(Class<T> type) {
        if (type.isInterface()) {
            Class<T> implClass = config.getImplClass(type);
            if (implClass == null) {
                Set<Class<? extends T>> classes = scanner.getSubTypesOf(type);
                if (classes.size() != 1) {
                    throw new RuntimeException(type + " should have only one impl, otherwise update config");
                }
                implClass = (Class<T>) classes.iterator().next();
            }
            type = implClass;
        }
        return type;
    }
}










