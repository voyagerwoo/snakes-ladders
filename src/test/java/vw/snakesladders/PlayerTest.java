package vw.snakesladders;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    void movePlayer() {
        Player player = new Player("Player 1", emptyMap(), emptyMap(), 100);
        String statement = player.play(1,4);

        assertThat(player.getCursor()).isEqualTo(5);
        assertThat(statement).isEqualTo("Player 1 is on square 5");
    }

    @Test
    void movePlayerWithSnakes() {
        Map<Integer, Integer> snakes = new HashMap<Integer, Integer>(){{
            put(12, 4);
        }};
        Player player = new Player("Player 2", snakes, emptyMap(), 100);

        String statement1 = player.play(6,6);
        String statement2 = player.play(2,6);

        assertThat(player.getCursor()).isEqualTo(4);
        assertThat(statement1).isEqualTo("Player 2 is on square 4");
        assertThat(statement2).isEqualTo("Player 2 is on square 4");
    }

    @Test
    void movePlayerWithLadders() {
        Map<Integer, Integer> ladders = new HashMap<Integer, Integer>(){{
            put(5, 15);
        }};
        Player player = new Player("Player 3", emptyMap(), ladders, 100);

        String statement1 = player.play(2,3);
        String statement2 = player.play(2,2);

        assertThat(player.getCursor()).isEqualTo(19);
        assertThat(statement1).isEqualTo("Player 3 is on square 15");
        assertThat(statement2).isEqualTo("Player 3 is on square 19");
    }

    @Test
    void winPlayer() {
        Player player = new Player("Player 4", emptyMap(), emptyMap(), 100);
        player.setCursor(92);

        String statement1 = player.play(3, 5);
        String statement2 = player.play(3, 5);

        assertThat(player.getCursor()).isEqualTo(100);
        assertThat(statement1).isEqualTo("Player 4 Wins!");
        assertThat(statement2).isEqualTo("Game over!");
    }

    @Test
    void winPlayer1AndLoasPlayer2() {
        Player player1 = new Player("Player 1", emptyMap(), emptyMap(), 100);
        Player player2 = new Player("Player 2", emptyMap(), emptyMap(), 100);
        player1.setCursor(92);
        player2.setCursor(91);

        String statement1 = player1.play(3, 5);
        player2.setLose();
        String statement2 = player2.play(3, 5);

        assertThat(player1.getCursor()).isEqualTo(100);
        assertThat(statement1).isEqualTo("Player 1 Wins!");
        assertThat(statement2).isEqualTo("Game over!");
    }

    @Test
    void movePlayerBeyondGoal() {
        Player player = new Player("Player 5", emptyMap(), emptyMap(), 100);
        player.setCursor(98);

        String statement = player.play(3, 2);

        assertThat(player.getCursor()).isEqualTo(97);
        assertThat(statement).isEqualTo("Player 5 is on square 97");
    }

}
