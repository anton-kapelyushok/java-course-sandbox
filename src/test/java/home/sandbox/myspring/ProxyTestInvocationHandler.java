package home.sandbox.myspring;

public class ProxyTestInvocationHandler {
    public String lastCalledWith = "";

    private static ProxyTestInvocationHandler ourInstance = new ProxyTestInvocationHandler();

    public static ProxyTestInvocationHandler getInstance() {
        return ourInstance;
    }

    private ProxyTestInvocationHandler() {
    }

    public void call(String s) {
        lastCalledWith += s;
    }

    public void clear() {
        lastCalledWith = "";
    }
}
