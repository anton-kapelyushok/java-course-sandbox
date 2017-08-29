package home.sandbox.myspring;

public class ProxyTestAnnotatedImpl implements ProxyTestAnnotated {
    @Override
    public void noAnnotation() {
        ProxyTestInvocationHandler.getInstance().call("noAnnotation");
    }

    @ProxyTestAnnotation
    @Override
    public void oneAnnotation() {
        ProxyTestInvocationHandler.getInstance().call("oneAnnotation");
    }

    @AnotherProxyTestAnnotation
    @ProxyTestAnnotation
    @Override
    public void twoAnnotations() {
        ProxyTestInvocationHandler.getInstance().call("twoAnnotations");
    }


}
