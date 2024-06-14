package Zadanie2;

import java.util.List;

public class MinMaxService {
    public static <T extends Comparable<T>> MinMax<T> getMinAndMax(List<T> elements) {
        if (elements == null || elements.isEmpty()) {
            throw new IllegalArgumentException("List cannot be null or empty");
        }

        T min = elements.get(0);
        T max = elements.get(0);

        for (T element : elements) {
            if (element.compareTo(min) < 0) {
                min = element;
            }
            if (element.compareTo(max) > 0) {
                max = element;
            }
        }

        return new MinMax<>(min, max);
    }
}

