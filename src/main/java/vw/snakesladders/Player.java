package vw.snakesladders;

import java.util.Map;

public class Player {
    private String name;
    private int cursor = 0;
    private final Map<Integer, Integer> snakes;
    private final Map<Integer, Integer> ladders;
    private final int goal;

    public Player(String name, Map<Integer, Integer> snakes, Map<Integer, Integer> ladders, int goal) {
        // todo check validation of snakes, ladders;
        this.name = name;
        this.snakes = snakes;
        this.ladders = ladders;
        this.goal = goal;
    }


    public String play(int dies1, int dies2) {
        cursor += dies1 + dies2;
        if (cursor > goal) {
            cursor = goal - (cursor - goal);
        }

        if (isPlacedInSnakeBox(cursor)) {
            cursor = snakes.get(cursor);
        } else if (isPlacedInLadderBox(cursor)) {
            cursor = ladders.get(cursor);
        }

        return name + " is on square " + cursor;
    }

    private boolean isPlacedInLadderBox(int cursor) {
        return ladders.get(cursor) != null;
    }

    private boolean isPlacedInSnakeBox(int cursor) {
        return snakes.get(cursor) != null;
    }

    int getCursor() {
        return cursor;
    }

    void setCursor(int cursor) {
        this.cursor = cursor;
    }
}
