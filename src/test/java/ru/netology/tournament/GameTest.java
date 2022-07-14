package ru.netology.tournament;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Player;
import ru.netology.domain.NotRegisteredException;

import static org.junit.jupiter.api.Assertions.*;


public class GameTest {
    private Game player = new Game();

    private Player player1 = new Player(1, "Liu Kang", 100);
    private Player player2 = new Player(2, "Johnny Cage", 100);
    private Player player3 = new Player(3, "Sub Zero", 300);
    private Player player4 = new Player(4, "Kitana", 250);
    private Player player5 = new Player(5, "Scorpion", 400);

    @BeforeEach
    void team() {
        player.addPlayer(player1);
        player.addPlayer(player2);
        player.addPlayer(player3);
        player.addPlayer(player4);
        player.addPlayer(player5);
    }

    @Test
    public void shouldFindAll() {
        Player[] actual = player.findAll().toArray(new Player[0]);
        Player[] expected = new Player[]{player1, player2, player3, player4, player5};

        assertArrayEquals(actual, expected);
    }

    @Test
    public void ShouldStrengthEquals() {

        int actual = 0;
        int expected = player.round("Liu Kang", "Johnny Cage");

        assertEquals(actual, expected);
    }

    @Test
    public void ShouldTheFirstPlayerIsStrongerThanTheSecond() {

        int actual = 1;
        int expected = player.round("Scorpion", "Sub Zero");

        assertEquals(actual, expected);
    }

    @Test
    public void ShouldTheFirstPlayerIsWeakerThanTheSecond() {

        int actual = 2;
        int expected = player.round("Kitana", "Sub Zero");

        assertEquals(actual, expected);
    }

    @Test
    public void ShouldRegistrationCheck() {
        boolean actual = false;
        boolean expected = player.register(new Player());

        assertEquals(actual, expected);
    }

    @Test
    public void ShouldRoundWithUnRegisteredPlayer1() {
        assertThrows(NotRegisteredException.class, () -> {
            player.round("Sonya Blade", "Sub Zero");
        });
    }

    @Test
    public void ShouldRoundWithUnRegisteredPlayer2() {
        assertThrows(NotRegisteredException.class, () -> {
            player.round("Johnny Cage", "Shang Tsung");
        });
    }
}
