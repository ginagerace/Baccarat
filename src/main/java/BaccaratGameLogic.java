import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.lang.Math;

public class BaccaratGameLogic extends BaccaratGame{

    //methods
    public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2) {
        int playerSum, dealerSum;
        String winner;

        //hand 1 = player, hand2 = dealer
        playerSum = handTotal(hand1);
        dealerSum = handTotal(hand2);

        if(playerSum == dealerSum)
          {winner = "Draw";}
        else if ( 9-playerSum < 9-dealerSum)
          {winner = "Player";}
        else
          {winner = "Banker";}

        return winner;
    }

    public int handTotal(ArrayList<Card> hand){
        int sum = 0;
        int add;
        for (int i = 0; i < hand.size(); i++){
            if (hand.get(i).value > 9)
                add = 0;
            else
                add = hand.get(i).value;
            sum += add;
        }
        if (sum > 9)
        {sum = sum%10;}
        return sum;
    }

    public boolean evaluateBankerDraw (ArrayList<Card> hand, Card playerCard) {
        //GTE to 7, no draw
        if (handTotal(hand) >= 7) {
            return false;
        }
        //LTE to 2, draw
        else if(handTotal(hand) <= 2) {
            return true;
        }

        else if (handTotal(hand) == 3) {
            if (playerCard.value == 8) {
                return false;
            } else {
                return true;
            }
        }
        else if (handTotal(hand) == 4) {
            if ((playerCard.value == 0) || (playerCard.value == 1) || (playerCard.value == 8) || (playerCard.value == 9)) {
                return false;
            } else {
                return true;
            }
        }
        else if (handTotal(hand) == 5) {
            if ((playerCard.value == 0) || (playerCard.value == 1) ||(playerCard.value == 2) || (playerCard.value == 3) || (playerCard.value == 8) || (playerCard.value == 9)) {
                return false;
            } else {
                return true;
            }
        }

        else{ //(handTotal(hand) == 6)
            if ((playerCard.value == 6) || (playerCard.value == 7))
            {return true;}
            else
            {return false;}
        }
    }

    public boolean evaluatePlayerDraw(ArrayList<Card> hand){
        //if players hand is GTE to 6, no card is drawn
        //else (players hand is LTE to 5, card is drawn
        return handTotal(hand) < 6;
    }

    //added method to check for a natural win
    public String naturalWin(ArrayList<Card> hand1, ArrayList<Card> hand2){
        int playerSum, dealerSum;
        String winner;

        //hand 1 = player, hand2 = dealer
        playerSum = handTotal(hand1);
        dealerSum = handTotal(hand2);

        if(playerSum==9 && dealerSum==9)
            return "Draw";
        else if(playerSum==8 && dealerSum==8)
            return "Draw";
        else if(playerSum == 9)
            return "Player";
        else if(dealerSum == 9)
            return "Banker";
        else if(playerSum == 8)
            return "Player";
        else if(dealerSum == 8)
            return "Banker";
        else
            return "None";
    }
}