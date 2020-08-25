package sample;

import java.util.Random;

public class CardDeck {

    // Instance variables
    private static final int CARDS_IN_DECK = 52;
    private Card[] deck;
    private boolean[] cardsDealt;
    Card lastCardReturned;
    private Random rand;
    int counter, index;

    /**
     * Default constructor for CardDeck object.
     */
    public CardDeck(){
        deck = new Card[CARDS_IN_DECK];
        cardsDealt = new boolean[CARDS_IN_DECK];  // (default initial values of boolean array are false)
        lastCardReturned = new Card();
        rand = new Random();
        initializeDeck();
    }

    // Fills deck with set of 52 ordered cards beginning with Ace of spades and ending with King of clubs. (order matches PNGs)
    private void initializeDeck() {
        String[] suit = {"spade", "heart", "diamond", "club"};
        int[] rank = {14, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};  // 14 = Ace, 2-10, 11 = Jack, 12 = Queen, 13 = King
        int pngNumber;
        int deckIndex = 0;

        for (int i = 0; i < suit.length; i++){
            for (int j = 0; j < rank.length; j++){

                // Instantiate new StrinBuilder each time
                StringBuilder sb = new StringBuilder();

                // Instantiate new card object and set suit and rank
                Card card = new Card();
                card.setSuit(suit[i]);
                card.setRank(rank[j]);

                // Create file number variable to create filename, then set imageName
                pngNumber = deckIndex + 1;
                sb.append(pngNumber);
                sb.append(".png");
                card.setImageName(sb.toString());

                // Assign newly created card to deck in correct order
                deck[deckIndex] = card;
                deckIndex++;
            }
        }
    }

    /**
     * Returns a random Card from the CardDeck.
     *
     * Ensures Card has not already been dealt. If function gets called > 52x, function
     * returns the last Card dealt and will continue to do so if kept being called.
     *
     * @return Card in the CardDeck.
     */
    public Card getNext(){

        // Randomly select a card that hasn't been dealt already
        if (counter < 52) {
            do{
                index = rand.nextInt(52);  // selects random integer from 0-51
            } while (cardsDealt[index]);

            // Now indicate card has been dealt, so it can't be dealt in future
            cardsDealt[index] = true;

            // Keep track of last card and increment counter
            lastCardReturned = deck[index];
            counter++;
            return deck[index];
        }
        else {
            return lastCardReturned;
        }
    }

    //added this method to for the shuffle/reset game
    public void shuffleDeck(){
        counter = 0;
        for(int i = 0; i<52; ++i){
            cardsDealt[i] = false;
        }
    }


    // TEMPORARY method used for debugging/demonstration
    public void showCardsDealt(){
        System.out.println("I had forgotten that a boolean array defaults to false when initialized.");
        for (int i = 0; i < cardsDealt.length; i++){
            if (i != 0 && i % 13 == 0)
                System.out.println();
            System.out.println(i + 1 + ". " + cardsDealt[i]);
        }
        System.out.println();
    }

    // TEMPORARY method used for debugging/demonstration
    public void showDeck(){
        System.out.println("Simply prints ordered deck that matches PNG image ordering.");
        for (int i = 0; i < deck.length; i++){
            if (i != 0 && i % 13 == 0)
                System.out.println();
            System.out.println(deck[i].getSuit() + " " + deck[i].getRank() + " " + deck[i].getImageName());
        }
        System.out.println();
    }
}
