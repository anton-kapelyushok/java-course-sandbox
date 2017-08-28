package home.sandbox.myspring;

public class ProxyTestAnnotatedImpl implements ProxyTestAnnotated {
    @Override
    public void noAnnotation() {

    }

    @ProxyTest
    @Override
    public void oneAnnotation() {

    }

    @ProxyTest
    @AnotherProxyTest
    @Override
    public void twoAnnotations() {

    }


}
