public class Card {
    //members
    String suite;
    int value;
    String symbol;

    //constructor
    Card(String theSuite, int theValue){
        this.suite = theSuite;
        this.value = theValue;
    }

    Card(String theSuite, int theValue, String theSymbol){
        this.suite = theSuite;
        this.value = theValue;
        this.symbol = theSymbol;
    }

}
