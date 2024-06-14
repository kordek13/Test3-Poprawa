package Zadanie2;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5);
        MinMax<Integer> intMinMax = MinMaxService.getMinAndMax(integers);
        System.out.println("Min integer: " + intMinMax.getMin());
        System.out.println("Max integer: " + intMinMax.getMax());

        List<String> strings = Arrays.asList("Krzysztof", "Anna", "Ryszard", "Jadwiga");
        MinMax<String> stringMinMax = MinMaxService.getMinAndMax(strings);
        System.out.println("Min string: " + stringMinMax.getMin());
        System.out.println("Max string: " + stringMinMax.getMax());
    }
}
