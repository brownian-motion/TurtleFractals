package com.brownian.fractalsfx;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public final class CacheingSequence<E extends Enum<E>> implements SymbolSequence<E> {
    private final SymbolSequence<E> sequence;
    private final List<List<E>> cache = new ArrayList<>();

    public CacheingSequence(SymbolSequence<E> sequence) {
        this.sequence = sequence;
    }

    @Override
    public Stream<E> getSequence(int level) {
        if (cache.size() < level) {
            for (int i = cache.size(); i <= level; i++) {
                cache.add(sequence.getSequence(i).toList());
            }
        }
        return cache.get(level).stream();
    }
}
