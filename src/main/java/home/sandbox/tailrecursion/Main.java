package home.sandbox.tailrecursion;

public class Main {
    public static void main(String[] args) {
        System.out.println(
                new TailRecFunctionExecutor<Integer>()
                        .getResult(
                                new SumN(),
                                Arguments.builder()
                                        .arg("acc", 0)
                                        .arg("n", 1000000)
                                        .build()
                        )
        );
    }
}
