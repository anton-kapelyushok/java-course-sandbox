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
        Map<String, AttachedMethod> invocationMap = new HashMap<>();

        T proxy = null;
        for (Method originalMethod : methods) {
            AttachedMethod newMethod = new AttachedMethod() {
                @Override
                @SneakyThrows
                public Object invoke(Object[] args) {
                    return originalMethod.invoke(t, args);
                }
            };
            for (ProxyConfigurator proxyConfigurator : proxyConfigurators) {
                MethodProducer methodProducer = proxyConfigurator.proxy(type, originalMethod, newMethod);

                if (methodProducer != null) {
                    if (proxy == null) {
                        proxy = createProxy(type, invocationMap);
                    }
                    newMethod = methodProducer.produce(t, proxy);
                }

            }
            invocationMap.put(getMethodKey(originalMethod), newMethod);
        }

        if (proxy == null) {
            return t;
        } else {
            return proxy;
        }
    }

    private String getMethodKey(Method method) {
        return method.getName() + Arrays.toString(method.getParameterTypes());
    }

    @SuppressWarnings("unchecked")
    private <T> T createProxy(Class<?> type, Map<String, AttachedMethod> invocationMap) {
        return (T) Proxy.newProxyInstance(
                type.getClassLoader(),
                type.getInterfaces(),
                (proxy, method, args) -> invocationMap.get(getMethodKey(method)).invoke(args)
        );
    }

    @SuppressWarnings("unchecked")
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










