package home.sandbox.tailrecursion;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

interface TailRecFunction<R> {
    void call(
            Arguments args,
            Consumer<R> onReturn,
            BiConsumer<TailRecFunction<R>, Arguments> onNext,
            TailRecFunction<R> self
    );
}
