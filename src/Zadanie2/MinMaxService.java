package Zadanie2;

import java.util.List;

public class MinMaxService {
    public static <T extends Comparable<T>> MinMax<T> getMinAndMax(List<T> elements) {
        if (elements == null || elements.isEmpty()) {
            throw new IllegalArgumentException("List cannot be null or empty");
        }

        T min = null;
        T max = null;

        for (T element : elements) {
            if (element != null) {
                if (min == null || element.compareTo(min) < 0) {
                    min = element;
                }
                if (max == null || element.compareTo(max) > 0) {
                    max = element;
                }
            }
        }

        if (min == null || max == null) {
            throw new IllegalArgumentException("List cannot contain only null elements");
        }

        return new MinMax<>(min, max);
    }
}


