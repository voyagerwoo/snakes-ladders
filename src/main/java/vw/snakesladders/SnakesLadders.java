package vw.snakesladders;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SnakesLadders {
    private final int numOfPlayer;
    private final int goal;
    private final Map<Integer, Integer> snakes;
    private final Map<Integer, Integer> ladders;
    private final List<Player> players;
    private int playerCursor = 0;

    public SnakesLadders(int numOfPlayer, int goal, Map<Integer, Integer> snakes, Map<Integer, Integer> ladders) {
        this.numOfPlayer = numOfPlayer;
        this.goal = goal;
        this.snakes = snakes;
        this.ladders = ladders;
        this.players = IntStream.range(1, numOfPlayer+1)
                .mapToObj(num -> new Player("Player " + num, snakes, ladders, goal))
                .collect(Collectors.toList());
    }

    public String play(int dies1, int dies2) {
        String statement = players.get(playerCursor).play(dies1, dies2);
        if (dies1 == dies2)
            return statement;
        playerCursor = (playerCursor += 1) % numOfPlayer;
        return statement;
    }

    List<Player> getPlayers() {
        return players;
    }
}
