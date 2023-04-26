package utils;

import java.util.List;
import java.util.function.ToIntFunction;

public class Utils {
    public static <T> Integer findNextCodigo(List<T> elements, ToIntFunction<T> getter) {
        return elements.stream().mapToInt(getter).max().orElse(0) + 1;
    }
}
