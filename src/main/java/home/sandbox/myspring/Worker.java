package home.sandbox.myspring;

public interface Worker {
    void work();
    void drinkBeer();

    @Benchmark
    void drinkBeer(int count);
}
