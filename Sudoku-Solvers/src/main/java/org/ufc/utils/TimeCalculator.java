package org.ufc.utils;

import java.util.function.Supplier;

public final class TimeCalculator {
    private TimeCalculator() {}

    public static double calcularTempoDeExecucao(Supplier supplier) {
        double start = System.currentTimeMillis();
        supplier.get();
        double now = System.currentTimeMillis();

        return (now - start) / 1000.0D;
    }
}
