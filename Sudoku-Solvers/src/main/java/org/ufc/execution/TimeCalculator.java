package org.ufc.execution;

import java.util.function.BooleanSupplier;

public final class TimeCalculator {
    private TimeCalculator() {}

    public static ExecutionResult calcularTempoDeExecucao(BooleanSupplier supplier) {
        long start = System.currentTimeMillis();
        boolean solved = supplier.getAsBoolean();
        long now = System.currentTimeMillis();

        double total = ((now - start) / 1_000.0);
        return new ExecutionResult(solved, total);
    }
}
