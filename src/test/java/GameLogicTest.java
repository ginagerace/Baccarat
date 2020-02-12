import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class GameLogicTest {

    BaccaratGameLogic gameLogic;
    ArrayList<Card> playerHand;
    ArrayList<Card> bankerHand;

    @BeforeEach
    void init(){
        gameLogic = new BaccaratGameLogic();
        playerHand = new ArrayList<Card>();
        bankerHand = new ArrayList<Card>();
    }

    @Test   //whoWon
    void testDraw(){
        playerHand.add(new Card("heart", 1));
        playerHand.add(new Card("spades", 3));
        bankerHand.add(new Card("heart", 2));
        bankerHand.add(new Card("spades", 2));
        assertEquals("Draw", gameLogic.whoWon(playerHand,bankerHand), "tie game");
    }

    @Test   //whoWon
    void testDraw2(){
        playerHand.add(new Card("heart", 8));
        playerHand.add(new Card("spades", 8));
        bankerHand.add(new Card("heart", 4));
        bankerHand.add(new Card("spades", 2));
        assertEquals("Draw", gameLogic.whoWon(playerHand,bankerHand), "tie game");
    }

    @Test   //whoWon
    void testPlayerWins(){
        playerHand.add(new Card("heart", 6));
        playerHand.add(new Card("spades", 3));
        bankerHand.add(new Card("heart", 2));
        bankerHand.add(new Card("spades", 2));
        assertEquals("Player", gameLogic.whoWon(playerHand,bankerHand), "player wins");
    }

    @Test   //whoWon
    void testPlayerWins2(){
        playerHand.add(new Card("heart", 9));
        playerHand.add(new Card("spades", 9));
        bankerHand.add(new Card("heart", 2));
        bankerHand.add(new Card("spades", 5));
        assertEquals("Player", gameLogic.whoWon(playerHand,bankerHand), "player wins");
    }

    @Test   //whoWon
    void testBankerWins(){
        playerHand.add(new Card("heart", 6));
        playerHand.add(new Card("spades", 2));
        bankerHand.add(new Card("heart", 7));
        bankerHand.add(new Card("spades", 2));
        assertEquals("Banker", gameLogic.whoWon(playerHand,bankerHand), "banker wins");
    }

    @Test   //whoWon
    void testBankerWins2(){
        playerHand.add(new Card("heart", 6));
        playerHand.add(new Card("spades", 6));
        bankerHand.add(new Card("heart", 4));
        bankerHand.add(new Card("spades", 2));
        assertEquals("Banker", gameLogic.whoWon(playerHand,bankerHand), "banker wins");
    }

    @Test   //handTotal
    void testAllFaceCards(){
        playerHand.add(new Card("heart", 10));
        playerHand.add(new Card("spades", 11));
        playerHand.add(new Card("heart", 12));
        playerHand.add(new Card("spades", 13));
        assertEquals(0, gameLogic.handTotal(playerHand), "incorrect total");
    }

    @Test   //handTotal
    void testLessThan9(){
        playerHand.add(new Card("heart", 1));
        playerHand.add(new Card("spades", 4));
        assertEquals(5, gameLogic.handTotal(playerHand), "incorrect total");
    }

    @Test   //handTotal
    void testEqualTo9(){
        playerHand.add(new Card("heart", 5));
        playerHand.add(new Card("spades", 4));
        assertEquals(9, gameLogic.handTotal(playerHand), "incorrect total");
    }

    @Test   //handTotal
    void testGreaterThan9(){
        playerHand.add(new Card("heart", 8));
        playerHand.add(new Card("spades", 4));
        assertEquals(2, gameLogic.handTotal(playerHand), "incorrect total");
    }

    @Test   //evaluateBankerDraw
    void testBankerGTE7() {
        Card player = new Card("clubs", 4);
        bankerHand.add(new Card("heart", 8));
        bankerHand.add(new Card("spades", 10));
        assertFalse(gameLogic.evaluateBankerDraw(bankerHand, player));
    }

    @Test   //evaluateBankerDraw
    void testBankerLTE2() {
        Card player = new Card("clubs", 4);
        bankerHand.add(new Card("heart", 1));
        bankerHand.add(new Card("spades", 1));
        assertTrue(gameLogic.evaluateBankerDraw(bankerHand, player));
    }

    @Test   //evaluateBankerDraw
    void testBanker3() {
        Card player = new Card("clubs", 8);
        bankerHand.add(new Card("heart", 1));
        bankerHand.add(new Card("spades", 2));
        assertFalse(gameLogic.evaluateBankerDraw(bankerHand, player));
    }

    @Test   //evaluateBankerDraw
    void testBanker4() {
        Card player = new Card("clubs", 2);
        bankerHand.add(new Card("heart", 4));
        bankerHand.add(new Card("spades", 10));
        assertTrue(gameLogic.evaluateBankerDraw(bankerHand, player));
    }

    @Test   //evaluateBankerDraw
    void testBanker5() {
        Card player = new Card("clubs", 4);
        bankerHand.add(new Card("heart", 3));
        bankerHand.add(new Card("spades", 2));
        assertTrue(gameLogic.evaluateBankerDraw(bankerHand, player));
    }

    @Test   //evaluateBankerDraw
    void testBanker6() {
        Card player = new Card("clubs", 3);
        bankerHand.add(new Card("heart", 4));
        bankerHand.add(new Card("spades", 2));
        assertFalse(gameLogic.evaluateBankerDraw(bankerHand, player));
    }

    @Test   //evaluatePlayerDraw
    void testPlayerDraw() {
        playerHand.add(new Card("heart", 1));
        playerHand.add(new Card("spades", 3));
        assertTrue(gameLogic.evaluatePlayerDraw(playerHand));
    }

    @Test   //evaluatePlayerDraw
    void testPlayerNoDraw(){
        playerHand.add(new Card("diamond", 7));
        playerHand.add(new Card("clubs", 1));
        assertFalse(gameLogic.evaluatePlayerDraw(playerHand));
    }

    @Test   //evaluatePlayerDraw
    void testPlayerDraw5(){
        playerHand.add(new Card("diamond", 4));
        playerHand.add(new Card("clubs", 1));
        assertTrue(gameLogic.evaluatePlayerDraw(playerHand));
    }

    @Test   //evaluatePlayerDraw
    void testPlayerDraw6(){
        playerHand.add(new Card("hearts", 5));
        playerHand.add(new Card("clubs", 1));
        assertFalse(gameLogic.evaluatePlayerDraw(playerHand));
    }

    @Test   //naturalWin
    void testNoWinner(){
        playerHand.add(new Card("heart", 6));
        playerHand.add(new Card("spades", 6));
        bankerHand.add(new Card("heart", 4));
        bankerHand.add(new Card("spades", 2));
        assertEquals("None", gameLogic.naturalWin(playerHand,bankerHand), "no natural win");
    }

    @Test   //naturalWin
    void testBanker(){
        playerHand.add(new Card("heart", 6));
        playerHand.add(new Card("spades", 6));
        bankerHand.add(new Card("heart", 4));
        bankerHand.add(new Card("spades", 4));
        assertEquals("Banker", gameLogic.naturalWin(playerHand,bankerHand), "banker wins");
    }

}
