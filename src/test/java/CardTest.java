import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CardTest {

    Card c;
    String heart = "\u2661";

    @Test
    void testOriginalConstructor(){
        c = new Card("heart", 1);
        assertEquals("heart", c.suite, "incorrect suite");
        assertEquals(1, c.value, "incorrect value");
    }

    @Test
    void testSecondConstructor(){
        c = new Card("heart", 1, heart);
        assertEquals("heart", c.suite, "incorrect suite");
        assertEquals(1, c.value, "incorrect value");
        assertEquals("\u2661", c.symbol, "incorrect symbol");
    }

}
