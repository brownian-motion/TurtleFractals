package com.brownian.fractalsfx;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class InductiveSequence<E extends Enum<E>> implements SymbolSequence<E> {
    private final List<List<E>> cache = new ArrayList<>();

    {
        cache.add(getInitialSequence().toList());
    }

    protected abstract Stream<E> getInitialSequence();

    protected abstract Stream<E> generateNextSequence(List<E> previousSequence);

    @Override
    public Stream<E> getSequence(int level) {
        if (cache.size() <= level) {
            for (int i = cache.size(); i <= level; i++) {
                cache.add(generateNextSequence(cache.get(i - 1)).toList());
            }
        }
        return cache.get(level).stream();
    }
}
