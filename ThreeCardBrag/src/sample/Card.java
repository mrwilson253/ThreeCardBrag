package sample;

public class Card {

    // Instance variables
    private int rank;          // Ace = 14, 2-10 regular numbered cards, 11 = Jack, 12 = Queen, 13 = King
    private String suit;       // spade, heart, diamond, club
    private String imageName;  // filename (e.g. 1.png)

    /**
     * Default constructor for Card object.
     */
    public Card() {

        // Initializes strings to empty strings rather than being null (rank's initial value already is 0)
        suit = "";
        imageName = "";
    }

    /**
     * Accessor to retrieve the card's suit
     * @return the card's suit
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Mutator to set/change the card's suit (spade, heart, diamond, club).
     * @param suit the suit of the card.
     */
    public void setSuit(String suit) {
        this.suit = suit;
    }

    /**
     * Accessor to retrieve the card's imageName.
     * @return the card's filename (e.g. 1.png)
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * Mutator to set/change the card's imageName.
     * @param imageName the filename of the card.
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * Accessor to retrieve the card's rank.
     * @return the card's rank.
     */
    public int getRank() {
        return rank;
    }

    /**
     * Mutator to set/change the card's rank (Ace, King, Queen, etc.).
     * @param rank the rank of the card.
     */
    public void setRank(int rank) {
        this.rank = rank;
    }
}
