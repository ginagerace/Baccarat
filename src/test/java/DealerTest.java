import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class DealerTest {

	BaccaratDealer dealer;

	@Test	//constructor
	void testConstructor(){
		dealer = new BaccaratDealer();
		assertEquals(0, dealer.deck.size(), "deck should be empty");
	}

	@BeforeEach
	void init() {
		dealer = new BaccaratDealer();
		dealer.generateDeck();
	}

	@Test	//generateDeck
	void testNewDeckSize() {
		assertEquals(52, dealer.deck.size(), "incorrect initial size");
	}

	@Test	//generateDeck
	void testNewDeckFirstCard() {
		Card c = dealer.deck.get(0);
		assertEquals("hearts", c.suite, "incorrect first card");
		assertEquals(1, c.value, "incorrect first card");
	}

	@Test	//generateDeck
	void testNewDeckLastCard() {
		Card c = dealer.deck.get(51);
		assertEquals("clubs", c.suite, "incorrect last card");
		assertEquals(13, c.value, "incorrect last card");
	}

	@Test	//dealHand
	void testDealHandSize() {
		ArrayList<Card> hand = dealer.dealHand();
		assertEquals(2, hand.size(), "size should be 2");
	}

	@Test	//dealHand
	void testDealDeckSize() {
		ArrayList<Card> hand = dealer.dealHand();
		assertEquals(50, dealer.deck.size(), "size should be 50");
	}

	@Test	//drawOne
	void testDrawHandSize() {
		Card hand = dealer.drawOne();
		assertNotNull(hand.suite);
	}

	@Test	//drawOne
	void testDrawDeckSize() {
		Card hand = dealer.drawOne();
		assertEquals(51, dealer.deck.size(), "size should be 51");
	}

	@Test	//shuffleDeck
	void testShuffleSize(){
		dealer.shuffleDeck();
		assertEquals(52, dealer.deck.size(), "incorrect initial size");
	}

	@Test	//shuffleDeck
	void testShuffled(){
		dealer.shuffleDeck();
		assertFalse(dealer.deck.get(0).suite.equals("hearts") && dealer.deck.get(1).suite.equals("hearts") && dealer.deck.get(2).suite.equals("hearts"));
	}

	@Test	//deckSize
	void testDeckSize(){
		dealer.deck.clear();
		assertEquals(0, dealer.deckSize(), "deck should be empty");
		dealer.deck.add(new Card("hearts", 1));
		assertEquals(1, dealer.deckSize(), "incorrect deck size");
		dealer.deck.add(new Card("spades", 7));
		assertEquals(2, dealer.deckSize(), "incorrect deck size");
	}

	@Test	//deckSize
	void testDeckSizeAfterDraw(){
		Card hand = dealer.drawOne();
		assertEquals(51, dealer.deckSize(), "incorrect deck size");
	}

	@Test	//deckSize
	void testDeckSizeAfterDeal(){
		ArrayList<Card> hand = dealer.dealHand();
		assertEquals(50, dealer.deckSize(), "incorrect deck size");
	}

}
