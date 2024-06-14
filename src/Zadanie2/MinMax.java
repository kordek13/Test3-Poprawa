package Zadanie2;

public final class MinMax<T extends Comparable<T>> {
    private final T min;
    private final T max;

    public MinMax(T min, T max) {
        this.min = min;
        this.max = max;
    }

    public T getMin() {
        return min;
    }

    public T getMax() {
        return max;
    }
}