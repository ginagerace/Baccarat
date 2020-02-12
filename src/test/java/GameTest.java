import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class GameTest {

    BaccaratGame theGame;

    @BeforeEach
    void init() {
        theGame = new BaccaratGame();
        theGame.playerHand = new ArrayList<Card>();
        theGame.bankerHand = new ArrayList<Card>();
        theGame.gameLogic = new BaccaratGameLogic();
    }

    @Test   //evaluateWinnings
    void testWonBet(){
        theGame.currentBet = 10.00;
        theGame.betWinner = "Player";
        theGame.playerHand.add(new Card("heart", 3));
        theGame.playerHand.add(new Card("spades", 6));
        theGame.bankerHand.add(new Card("heart", 4));
        theGame.bankerHand.add(new Card("spades", 2));
        assertEquals(10.00, theGame.evaluateWinnings(), "bet won");
    }

    @Test   //evaluateWinnings
    void testLostBet(){
        theGame.currentBet = 10.00;
        theGame.betWinner = "Player";
        theGame.playerHand.add(new Card("heart", 3));
        theGame.playerHand.add(new Card("spades", 1));
        theGame.bankerHand.add(new Card("heart", 4));
        theGame.bankerHand.add(new Card("spades", 3));
        assertEquals(-10.00, theGame.evaluateWinnings(), "bet lost");
    }
}
