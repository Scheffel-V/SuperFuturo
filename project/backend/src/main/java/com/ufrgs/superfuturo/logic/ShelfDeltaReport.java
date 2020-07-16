package com.ufrgs.superfuturo.logic;

import com.ufrgs.superfuturo.model.InputObject;

public class ShelfDeltaReport {
    final InputObject inputObject;
    final int delta;

    public ShelfDeltaReport(final InputObject obj, final InputObject frontObject, final int delta) {
        obj.setClasse(frontObject.getClasse());
        this.inputObject = obj;
        this.delta = delta;

        if (Math.abs(delta) > 1) {
            throw new ArithmeticException("For now we are limited in only accepting deltas of 1 product per shelf delta report");
        }
    }

    public static ShelfDeltaReport EmptyReport() {
        return new ShelfDeltaReport();
    }

    private ShelfDeltaReport() {
        this.inputObject = null;
        this.delta = 0;
    }
}
