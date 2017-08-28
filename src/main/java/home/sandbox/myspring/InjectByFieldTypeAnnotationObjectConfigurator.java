package home.sandbox.myspring;

import lombok.SneakyThrows;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;


public class InjectByFieldTypeAnnotationObjectConfigurator implements ObjectConfigurator {
    @Override
    @SneakyThrows
    public void configure(Object t) {
        Class<?> type = t.getClass();
        Set<Field> fields = ReflectionUtils.getAllFields(type, field -> field.isAnnotationPresent(InjectByType.class));
        for (Field field : fields) {
                Object o = ObjectFactory.getInstance().createObject(field.getType());
                field.setAccessible(true);
                field.set(t, o);
        }

        Set<Method> methods = ReflectionUtils.getAllMethods(type, method -> method.isAnnotationPresent(InjectByType.class));


        for (Method method : methods) {
            Class<?> typeToInject = method.getParameterTypes()[0];
            Object object = ObjectFactory.getInstance().createObject(typeToInject);
            method.setAccessible(true);
            method.invoke(t, object);
        }




















    }
}
