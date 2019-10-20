package spacealert.core.boardElements.positions;

import java.util.Arrays;
import java.util.List;

public enum Zone {
	RED,
	WHITE,
    BLUE;

    public static List<Zone> all = Arrays.asList(values());
}
