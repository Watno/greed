package spacealert.core.threats;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trajectory {
    private Map<Integer, Action> actionSpaces = Map.of(1, Action.Z);
    private int length;

    private Trajectory(int length, int positionOfX, List<Integer> positionsOfY) {
        actionSpaces.put(positionOfX, Action.X);
        for (var positionOfY : positionsOfY) {
            actionSpaces.put(positionOfY, Action.Y);
        }
        this.length = length;
    }

    public static Trajectory T1 = new Trajectory(10, 5, List.of());
    public static Trajectory T2 = new Trajectory(11, 8, List.of());
    public static Trajectory T3 = new Trajectory(12, 8, List.of(3));
    public static Trajectory T4 = new Trajectory(13, 9, List.of(5));
    public static Trajectory T5 = new Trajectory(14, 11, List.of(7));
    public static Trajectory T6 = new Trajectory(15, 10, List.of(3, 7));
    public static Trajectory T7 = new Trajectory(16, 12, List.of(5, 8));

    public List<Action> getActionsBetween(int initialPosition, int targetPosition) {
        var actions = new ArrayList<Action>();
        for (int position = initialPosition - 1; position >= targetPosition; position--) {
            if (actionSpaces.containsKey(position)) {
                actions.add(actionSpaces.get(position));
            }
        }
        return actions;
    }

}
