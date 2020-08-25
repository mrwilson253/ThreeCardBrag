package sample;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ThreeCard {
    //determine winner out of two players
        //three 3's or an ace
        //2 or 3 of the same card or suit
        //king, queen, or jack
        //if two players both have winning hands:
            //trip 3's or ace > pair or trips of same suit or number > face card
            //nobody wins in tie
    //local card arrays for logic
    private Card[] p1, p2;
    //local winner variable
    private int winner;
    //win condition
    private String winCondition;

    public ThreeCard(){}

    //setPlayerHands method
        //passed two array references of cards
        //sets local players hands to those arrays and calls whoWon();
    public void setPlayerHands(Card[] player1, Card[] player2){
        p1 = player1;
        p2 = player2;

        whoWon();
    }
    //getWinningHand method
        //returns 0 if neither hand is a winner
        //returns 1 if player 1 has won
        //returns 2 if player 2 has won
        //returns 3 if both hands are winners of equal ranking
        //if called before it has received any card arrays, return 0
    public int getWinningHand(){
        return winner;
    }
    public String getWinCondition(){return winCondition;}
    //getRules method
        //returns string with the rules, can be used for the JOP in the rulesmenu item on action
    public String getRules(){
        String rules = "Three 3's > Three Ace's > Pair Ace's >\n" +
                " Ace > Three Face cards > Flush > \n" +
                "Three of other cards > Pair of Face cards > Pair of other cards > \n" +
                "Face card > Nothing ";

        return rules;
    }
    //whoWon method
        //contains the logic for checking all of the combinations of cards in the two hands
        //and when on them is a winner. maybe set three tiers of winning correspond to the rules
    private void whoWon(){
        //Lists to track the value and suit of the cards in the players hands
        List<Integer> cardsp1 = new ArrayList<>();
        List<Integer> cardsp2 = new ArrayList<>();
        List<String> suitsp1 = new ArrayList<>();
        List<String> suitsp2 = new ArrayList<>();
        for(int i = 0; i<3; ++i){
            //getting the ranks and suits to put into the appropriate lists
            cardsp1.add(p1[i].getRank());
            cardsp2.add(p2[i].getRank());
            suitsp1.add(p1[i].getSuit());
            suitsp2.add(p2[i].getSuit());
        }
        //using a point system to determine winner
        int p1points=0, p2points=0;
        //for loop to iterate through the list of players and their hands, setting score to track win condition
        for(int j = 0; j<2; ++j) {
            //booleans for helping in defining what is in the players hand
            boolean trips=false; //50
            boolean threeAce=false; //45
            boolean pairAce=false; //40
            boolean ace=false; //35
            boolean threeFace=false; //30
            boolean flush=false; //25
            boolean threeKind=false; //20
            boolean pairFace=false; //15
            boolean pair=false; //10
            boolean face=false; //5
            //generic versions of the player specific lists for hand defining logic
            List<Integer> cards = new ArrayList<>();
            List<String> suits = new ArrayList<>();
            //counter to track the value of the players hand
            int counter=0;
            //defining which player variables to track
            if(j==0){
                cards=cardsp1;
                suits=suitsp1;
            }if(j==1){
                cards=cardsp2;
                suits=suitsp2;
            }
            //checking for win conditions and the value those conditions
            if (cards.get(0) > 10 && cards.get(0) < 14 || cards.get(1) > 10 && cards.get(1) < 14 || cards.get(2) > 10 && cards.get(2) < 14) {
                face = true;
                counter = 5;
                if(cards.get(0)==11||cards.get(1)==11||cards.get(2)==11) counter=6;
                if(cards.get(0)==12||cards.get(1)==12||cards.get(2)==12) counter=7;
                if(cards.get(0)==13||cards.get(1)==13||cards.get(2)==13) counter=8;
            }
            if (cards.get(0) == cards.get(1) || cards.get(0) == cards.get(2) || cards.get(1) == cards.get(2)) {
                pair = true;
                counter=10;
            }
            if (cards.get(0) == cards.get(1) && cards.get(0) == cards.get(2)) {
                threeKind = true;
                counter=20;
            }
            if (cards.get(0) == 14 || cards.get(1) == 14 || cards.get(2) == 14) {
                ace = true;
                counter=35;
            }
            if (cards.get(0) == 14 && cards.get(1) == 14 || cards.get(0) == 14 && cards.get(2) == 14 || cards.get(1) == 14 && cards.get(2) == 14) {
                pairAce = true;
                counter=40;
            }
            if (pair && face) {
                pairFace = true;
                counter=15;
            }
            if (threeKind && face) {
                threeFace = true;
                counter=30;
            }
            if (threeKind && ace) {
                threeAce = true;
                counter=45;
            }
            if (cards.get(0) == 3 && threeKind) {
                trips = true;
                counter=50;
            }
            if (suits.get(0) == suits.get(1) && suits.get(0) == suits.get(2)) {
                flush = true;
                counter=25;
            }
            if(j==0){
                p1points+=counter;  //awarding points to the appropriate player
                trips=false; //50
                threeAce=false; //45
                pairAce=false; //40
                ace=false; //35
                threeFace=false; //30
                flush=false; //25
                threeKind=false; //20
                pairFace=false; //15
                pair=false; //10
                face=false; //5
            }
            if(j==1){
                p2points+=counter;
                trips=false; //50
                threeAce=false; //45
                pairAce=false; //40
                ace=false; //35
                threeFace=false; //30
                flush=false; //25
                threeKind=false; //20
                pairFace=false; //15
                pair=false; //10
                face=false; //5
            }
        }
        int winCounter=0;
        if(p1points>p2points) winCounter=p1points;
        if(p2points>p1points) winCounter=p2points;
        switch (winCounter){
            case 5:
                winCondition = " wins with high face card!";
                break;
            case 6:
                winCondition = " wins with high face card!";
                break;
            case 7:
                winCondition = " wins with high face card!";
                break;
            case 8:
                winCondition = " wins with high face card!";
                break;
            case 10:
                winCondition = " wins with a pair!";
                break;
            case 15:
                winCondition = " wins with a pair with face card!";
                break;
            case 20:
                winCondition = " wins with three of a kind!";
                break;
            case 25:
                winCondition = " wins with a flush!";
                break;
            case 30:
                winCondition = " wins with three of kind of face cards!";
                break;
            case 35:
                winCondition = " wins with an ace!";
                break;
            case 40:
                winCondition = " wins with a pair with ace!";
                break;
            case 45:
                winCondition = " wins with three of a kind aces!";
                break;
            case 50:
                winCondition = " wins with trip 3's";
                break;
        }
        //setting the index of win value
        if(p1points==0 && p2points==0){
            winner=0;
        }if(p1points>p2points){
            winner=1;
        }if(p2points>p1points){
            winner=2;
        }if(p1points==p2points){
            winner=3;
        }
    }

}
/********************************************************************************************
 * Unused junk when players card arrays were actually integer arrays for index
 ********************************************************************************************/
/*
//for loop to define the players hand to discover win condition
            for (int i = 0; i < 3; ++i) {
                //attaining suit for Flush condition
                if (player[i] < 13) {
                    suits.add("spade");
                }
                if (player[i] > 12 && player[i] < 26) {
                    suits.add("heart");
                }
                if (player[i] > 25 && player[i] < 39) {
                    suits.add("diamond");
                } else {
                    suits.add("club");
                }
                //adjusting the card to find the value without suit
                int value = player[i];
                if (player[i] > 12 && player[i] < 26) value = player[i] - 13;
                if (player[i] > 25 && player[i] < 39) value = player[i] - 26;
                if (player[i] > 38) value = player[i] - 39;
                switch (value) {
                    case 0:
                        cards.add(14);
                        break;
                    case 1:
                        cards.add(2);
                        break;
                    case 2:
                        cards.add(3);
                        break;
                    case 3:
                        cards.add(4);
                        break;
                    case 4:
                        cards.add(5);
                        break;
                    case 5:
                        cards.add(6);
                        break;
                    case 6:
                        cards.add(7);
                        break;
                    case 7:
                        cards.add(8);
                        break;
                    case 8:
                        cards.add(9);
                        break;
                    case 9:
                        cards.add(10);
                        break;
                    case 10:
                        cards.add(11);
                        break;
                    case 11:
                        cards.add(12);
                        break;
                    case 12:
                        cards.add(13);
                        break;
                }
            }
 */
