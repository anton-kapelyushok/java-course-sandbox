package home.sandbox.tailrecursion;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class TailRecFunctionExecutor<R> {
    private boolean returned;
    private R result;
    private Arguments args;
    private TailRecFunction<R> fun;

    public R getResult(TailRecFunction<R> fun, Arguments args) {
        this.returned = false;
        this.result = null;
        this.fun = fun;
        this.args = args;

        Consumer<R> onReturn = result -> {
            this.result = result;
            this.returned = true;
        };
        BiConsumer<TailRecFunction<R>, Arguments> onNext = (nextFun, nextArgs) -> {
            this.fun = nextFun;
            this.args = nextArgs;
        };

        while (!this.returned) {
            this.fun.call(this.args, onReturn, onNext, this.fun);
        }

        return this.result;
    }
}