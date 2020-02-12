import java.util.ArrayList;
import java.util.Collections;

public class BaccaratDealer {
    //members
    ArrayList<Card> deck;

    String heart = "\u2661";
    String diamond = "\u2662";
    String spade = "\u2660";
    String club = "\u2663";

    //constructor
    BaccaratDealer(){
        deck = new ArrayList<Card>();
    }

    //methods
    public void generateDeck(){
        if(deck != null)
            deck.clear();
        //add A,2,3,...,K of hearts
        for(int i=1; i<14; i++)
            deck.add(new Card("hearts", i, heart));

        //add A,2,3,...,K of spades
        for(int i=1; i<14; i++)
            deck.add(new Card("spades", i, spade));

        //add A,2,3,...,K of diamonds
        for(int i=1; i<14; i++)
            deck.add(new Card("diamonds", i, diamond));

        //add A,2,3,...,K of clubs
        for(int i=1; i<14; i++)
            deck.add(new Card("clubs", i, club));
    }

    public ArrayList<Card> dealHand(){
        ArrayList<Card> hand = new ArrayList<Card>();
        //make sure there are 2 cards in deck to be dealt
        if(deck.size()>1) {
            hand.add(deck.get(0));
            deck.remove(0);
            hand.add(deck.get(0));
            deck.remove(0);
        }
        return hand;
    }

    public Card drawOne(){
        //check if deck contains at least one card
        if(deck.size()>0) {
            Card c = deck.get(0);
            deck.remove(0);
            return c;
        }
        //if deck is empty...
        return new Card("null", -1, "null");
    }

    public void shuffleDeck(){
        if(deck != null) {
            deck.clear();
            generateDeck();
            Collections.shuffle(deck);
        }
    }

    public int deckSize(){
        if(deck != null)
            return deck.size();
        else return 0;
    }
}
