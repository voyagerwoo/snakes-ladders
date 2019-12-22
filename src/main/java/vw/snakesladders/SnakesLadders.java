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
    private boolean gameOver = false;

    public SnakesLadders(int numOfPlayer, int goal, Map<Integer, Integer> snakes, Map<Integer, Integer> ladders) {
        if (numOfPlayer < 0)
            throw new IllegalArgumentException("'numOfPlayer' should be positive integer.");
        if (goal < 0)
            throw new IllegalArgumentException("'goal' should be positive integer.");
        assertSnakesValidation(goal, snakes);
        assertLaddersValidation(goal, ladders);

        this.numOfPlayer = numOfPlayer;
        this.goal = goal;
        this.snakes = snakes;
        this.ladders = ladders;
        this.players = IntStream.range(1, numOfPlayer+1)
                .mapToObj(num -> new Player("Player " + num, snakes, ladders, goal))
                .collect(Collectors.toList());
    }

    public String play(int dies1, int dies2) {
        Player player = players.get(playerCursor);
        String statement = player.play(dies1, dies2);
        if (player.isWin()) {
            players.stream().filter(it -> it != player).forEach(Player::setLose);
            gameOver = true;
            return statement;
        }

        if (dies1 == dies2)
            return statement;
        playerCursor = (playerCursor += 1) % numOfPlayer;
        return statement;
    }

    List<Player> getPlayers() {
        return players;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private void assertSnakesValidation(int goal, Map<Integer, Integer> snakes) {
        snakes.forEach((key, value) -> {
            if (value < 0 || key < 0)
                throw new IllegalArgumentException("'snake' key and value should be positive integer.");
            if (value > goal || key > goal)
                throw new IllegalArgumentException("'snake' key and value should be less than 'goal'.");
            if (key < value)
                throw new IllegalArgumentException("'snake' key should be larger than value. (key:"+key+", value:"+value+")");
        });
    }

    private void assertLaddersValidation(int goal, Map<Integer, Integer> ladders) {
        ladders.forEach((key, value) -> {
            if (value < 0 || key < 0)
                throw new IllegalArgumentException("'ladders' key and value should be positive integer.");
            if (value > goal || key > goal)
                throw new IllegalArgumentException("'ladders' key and value should be less than 'goal'.");
            if (key > value)
                throw new IllegalArgumentException("'ladders' key should be less than value. (key:"+key+", value:"+value+")");
        });
    }


}
