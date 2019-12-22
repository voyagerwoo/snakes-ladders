package vw.snakesladders;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SnakesLaddersTest {
    @Test
    void numOfPlayerShouldBePositiveInteger() {
        assertThatThrownBy(() ->
                new SnakesLadders(-1, 0, emptyMap(), emptyMap())
        )
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("'numOfPlayer' should be positive integer.");
    }

    @Test
    void goalShouldBePositiveInteger() {
        assertThatThrownBy(() ->
                new SnakesLadders(2, -100, emptyMap(), emptyMap())
        )
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("'goal' should be positive integer.");
    }

    @Test
    void snakeKeyAndValueShouldBePositiveInteger() {
        assertThatThrownBy(() ->
                {
                    HashMap<Integer, Integer> snakes = new HashMap<Integer, Integer>() {{
                        put(110, -10);
                    }};
                    new SnakesLadders(2, 100, snakes, emptyMap());
                }
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("'snake' key and value should be positive integer.");
    }

    @Test
    void snakeKeyAndValueShouldBeLessThanGoal() {
        assertThatThrownBy(() ->
                {
                    HashMap<Integer, Integer> snakes = new HashMap<Integer, Integer>() {{
                        put(110, 10);
                    }};
                    new SnakesLadders(2, 100, snakes, emptyMap());
                }
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("'snake' key and value should be less than 'goal'.");
    }

    @Test
    void snakeKeyShouldBeLargerThanValue() {
        assertThatThrownBy(() ->
                {
                    HashMap<Integer, Integer> snakes = new HashMap<Integer, Integer>() {{
                        put(10, 99);
                    }};
                    new SnakesLadders(2, 100, snakes, emptyMap());
                }
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("'snake' key should be larger than value. (key:10, value:99)");
    }


    @Test
    void laddersKeyAndValueShouldBePositiveInteger() {
        assertThatThrownBy(() ->
                {
                    HashMap<Integer, Integer> ladders = new HashMap<Integer, Integer>() {{
                        put(-10, 110);
                    }};
                    new SnakesLadders(2, 100, emptyMap(), ladders);
                }
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("'ladders' key and value should be positive integer.");
    }

    @Test
    void laddersKeyAndValueShouldBeLessThanGoal() {
        assertThatThrownBy(() ->
                {
                    HashMap<Integer, Integer> ladders = new HashMap<Integer, Integer>() {{
                        put(10, 110);
                    }};
                    new SnakesLadders(2, 100, emptyMap(), ladders);
                }
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("'ladders' key and value should be less than 'goal'.");
    }

    @Test
    void laddersKeyShouldBeLargerThanValue() {
        assertThatThrownBy(() ->
                {
                    HashMap<Integer, Integer> ladders = new HashMap<Integer, Integer>() {{
                        put(99, 23);
                    }};
                    new SnakesLadders(2, 100, emptyMap(), ladders);
                }
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("'ladders' key should be less than value. (key:99, value:23)");
    }

    @Test
    void playWith2PlayerWithoutSnakesAndLadders_firstPlay() {
        SnakesLadders game = getSnakesLaddersWithoutSnakesAndLadders();

        String statement1 = game.play(1, 2);

        assertThat(statement1).isEqualTo("Player 1 is on square 3");

        List<Player> players = game.getPlayers();
        assertThat(players.get(0).getCursor()).isEqualTo(3);
    }

    @Test
    void playWith2PlayerWithoutSnakesAndLadders_play2Times() {
        SnakesLadders game = getSnakesLaddersWithoutSnakesAndLadders();

        String statement1 = game.play(1, 2);
        String statement2 = game.play(1, 2);

        assertThat(statement1).isEqualTo("Player 1 is on square 3");
        assertThat(statement2).isEqualTo("Player 2 is on square 3");

        List<Player> players = game.getPlayers();
        assertThat(players.get(0).getCursor()).isEqualTo(3);
    }

    @Test
    void playWith2PlayerWithoutSnakesAndLadders_play3Times() {
        SnakesLadders game = getSnakesLaddersWithoutSnakesAndLadders();

        String statement1 = game.play(1, 2);
        String statement2 = game.play(1, 2);
        String statement3 = game.play(1, 2);

        assertThat(statement1).isEqualTo("Player 1 is on square 3");
        assertThat(statement2).isEqualTo("Player 2 is on square 3");
        assertThat(statement3).isEqualTo("Player 1 is on square 6");

        List<Player> players = game.getPlayers();
        assertThat(players.get(0).getCursor()).isEqualTo(6);
    }

    @Test
    void playWith2PlayerWithoutSnakesAndLadders_sameDices() {
        SnakesLadders game = getSnakesLaddersWithoutSnakesAndLadders();

        String statement1 = game.play(2, 2);
        String statement2 = game.play(2, 4);
        String statement3 = game.play(2, 1);


        assertThat(statement1).isEqualTo("Player 1 is on square 4");
        assertThat(statement2).isEqualTo("Player 1 is on square 10");
        assertThat(statement3).isEqualTo("Player 2 is on square 3");

        List<Player> players = game.getPlayers();
        assertThat(players.get(0).getCursor()).isEqualTo(10);
        assertThat(players.get(1).getCursor()).isEqualTo(3);
    }

    @Test
    void playWith2Player_withLadders() {
        SnakesLadders game = getSnakesLadders();

        String statement1 = game.play(4, 4);
        String statement2 = game.play(4, 3);
        String statement3 = game.play(4, 3);

        assertThat(statement1).isEqualTo("Player 1 is on square 20");
        assertThat(statement2).isEqualTo("Player 1 is on square 27");
        assertThat(statement3).isEqualTo("Player 2 is on square 7");

        List<Player> players = game.getPlayers();
        assertThat(players.get(0).getCursor()).isEqualTo(27);
        assertThat(players.get(1).getCursor()).isEqualTo(7);
    }

    @Test
    void playWith2Player_withSnakesAndLadders() {
        SnakesLadders game = getSnakesLadders();

        String statement1 = game.play(4, 4);
        String statement2 = game.play(4, 5);
        String statement3 = game.play(4, 3);

        assertThat(statement1).isEqualTo("Player 1 is on square 20");
        assertThat(statement2).isEqualTo("Player 1 is on square 7");
        assertThat(statement3).isEqualTo("Player 2 is on square 7");

        List<Player> players = game.getPlayers();
        assertThat(players.get(0).getCursor()).isEqualTo(7);
        assertThat(players.get(1).getCursor()).isEqualTo(7);
    }

    @Test
    void playWith2Player_withSnakesAndLadders_player1Win() {
        SnakesLadders game = getSnakesLadders();

        String statement1 = game.play(3, 3);
        String statement2 = game.play(5, 5);
        String statement3 = game.play(4, 4);
        String statement4 = game.play(1, 3);
        String statement5 = game.play(1, 1);
        String statement6 = game.play(1, 1);

        assertThat(statement1).isEqualTo("Player 1 is on square 6");
        assertThat(statement2).isEqualTo("Player 1 is on square 16");
        assertThat(statement3).isEqualTo("Player 1 is on square 24");
        assertThat(statement4).isEqualTo("Player 1 Wins!");
        assertThat(statement5).isEqualTo("Game over!");
        assertThat(statement6).isEqualTo("Game over!");
    }

    private SnakesLadders getSnakesLaddersWithoutSnakesAndLadders() {
        Map<Integer, Integer> snakes = emptyMap();
        Map<Integer, Integer> ladders = emptyMap();
        return new SnakesLadders(2, 100, snakes, ladders);
    }

    private SnakesLadders getSnakesLadders() {
        Map<Integer, Integer> snakes = new HashMap<Integer, Integer>() {{
            put(17, 4);
            put(18, 2);
            put(29, 7);
        }};

        Map<Integer, Integer> ladders = new HashMap<Integer, Integer>() {{
            put(8, 20);
            put(28, 100);
        }};
        return new SnakesLadders(2, 100, snakes, ladders);
    }
}
