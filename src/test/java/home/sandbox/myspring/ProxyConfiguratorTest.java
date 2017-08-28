package home.sandbox.myspring;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProxyConfiguratorTest {
    @Before
    public void clearProxyTestInvocationhandler() {
        ProxyTestInvocationHandler.getInstance().clear();
    }

    @Test
    public void testTwoAnnotationsProcessed() {
        ProxyTestAnnotated object = ObjectFactory.getInstance().createObject(ProxyTestAnnotated.class);
        object.twoAnnotations();
        String calledWith = ProxyTestInvocationHandler.getInstance().lastCalledWith;
        Assert.assertTrue(calledWith.contains("ProxyTesttwoAnnotations"));
        Assert.assertTrue(calledWith.contains("AnotherProxyTesttwoAnnotations"));
    }

    @Test
    public void testOnlyOneAnnotationProcessed() {
        ProxyTestAnnotated object = ObjectFactory.getInstance().createObject(ProxyTestAnnotated.class);
        object.oneAnnotation();
        Assert.assertEquals("ProxyTestoneAnnotation", ProxyTestInvocationHandler.getInstance().lastCalledWith);
    }

    @Test
    public void testNoFunctionalityAddedWhenAnnotationIsMissing() {
        ProxyTestAnnotated object = ObjectFactory.getInstance().createObject(ProxyTestAnnotated.class);
        object.noAnnotation();
        Assert.assertEquals("", ProxyTestInvocationHandler.getInstance().lastCalledWith);
    }
}

