package spacealert.core.boardElements.positions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Zone {
    RED,
    WHITE,
    BLUE;

    public static List<Zone> all = Arrays.asList(values());

    public static List<Zone> otherThan(Zone zone) {
        return all.stream().filter(x -> x != zone).collect(Collectors.toList());
    }
}
