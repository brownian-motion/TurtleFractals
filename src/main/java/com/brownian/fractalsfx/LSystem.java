package com.brownian.fractalsfx;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public record LSystem<E extends Enum<E>>(Map<E, List<E>> rewriteRules,
                                         List<E> level0Sequence) implements SymbolSequence<E> {
    public Stream<E> getSequence(int level) {
        assert level >= 0;

        Stream<E> result = level0Sequence.stream();
        for (int i = 0; i < level; i++) {
            result = result.flatMap(e -> rewriteRules.getOrDefault(e, Collections.singletonList(e)).stream());
        }
        return result;
    }
}
