package org.ufc.execution;

public record ExecutionResult(
        boolean solved,
        double timeExpended
) {
}
