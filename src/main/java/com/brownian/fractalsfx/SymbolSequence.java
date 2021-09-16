package com.brownian.fractalsfx;

import java.util.stream.Stream;

public interface SymbolSequence<E extends Enum<E>> {
    Stream<E> getSequence(int level);
}
