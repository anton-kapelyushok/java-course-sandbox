package home.sandbox.tailrecursion;

import lombok.Builder;
import lombok.Singular;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class SumN implements TailRecFunction<Integer> {
    @Override
    public void call(
            Arguments args,
            Consumer<Integer> onReturn,
            BiConsumer<TailRecFunction<Integer>,
                    Arguments> onNext, TailRecFunction<Integer> self
    ) {
        int n = args.get("n");
        int acc = args.get("acc");

        if (n == 0) {
            onReturn.accept(acc);
        } else {
            Arguments newArgs = Arguments.builder()
                    .arg("n", n - 1)
                    .arg("acc", acc + n)
                    .build();
            onNext.accept(self, newArgs);
        }
    }
}
