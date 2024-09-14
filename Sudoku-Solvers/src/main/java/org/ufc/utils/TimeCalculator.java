package org.ufc.utils;

import java.util.function.Supplier;

public final class TimeCalculator {
    private TimeCalculator() {}

    public static ExecutionResult calcularTempoDeExecucao(Supplier<Boolean> supplier) {
        long start = System.currentTimeMillis();
        Boolean solved = supplier.get();
        long now = System.currentTimeMillis();

        double total = ((now - start) / 1_000.0);
        return new ExecutionResult(solved, total);
    }
}
