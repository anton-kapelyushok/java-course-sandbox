package home.sandbox.myspring;

public class WorkerImpl implements Worker {
    @Override
    public void work() {
        System.out.println("Working....");
    }

    @Override
    @Benchmark
    public void drinkBeer() {
        System.out.println("Пиво вкуснее чем работа!!!");
    }
}
