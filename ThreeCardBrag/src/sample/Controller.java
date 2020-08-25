package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    CardDeck cardDeck = new CardDeck();
    ThreeCard threeCard = new ThreeCard();
    //local hands
    Card[] p1 = new Card[3];
    Card[] p2 = new Card[3];
    //local strings and counters
    private String p1Name = "Player 1", p2Name = "Player 2", result, winner;
    private int count = 0, countP1Wins=0, countP2Wins=0, countDraws=0;

    @FXML
    private MenuItem menuRules;

    @FXML
    private ImageView imgP1One;

    @FXML
    private ImageView imgP1Two;

    @FXML
    private ImageView imgP1Three;

    @FXML
    private TextArea txtResults;

    @FXML
    private Button btnDeal;

    @FXML
    private ImageView imgP2One;

    @FXML
    private ImageView imgP2Two;

    @FXML
    private ImageView imgP2Three;

    @FXML
    private Label lblP1;

    @FXML
    private Label lblP2;

    @FXML
    private Button btnWon;

    @FXML
    private TextField txtP1;

    @FXML
    private TextField txtP2;

    @FXML
    private Button btnShuffle;

    //deals the cards and calls game methods, tracks hands dealt and prints relevant messages
    @FXML
    void onActionDeal(ActionEvent event) {
        btnDeal.setVisible(false);
        btnWon.setVisible(true);
        dealTheCards();
        playerNames();
        count++;
        if(count == 9){
            if(countP1Wins>countP2Wins) winner=p1Name;
            if(countP2Wins>countP1Wins) winner=p2Name;
            else if (countP1Wins==countP2Wins) winner = "It's a draw!";
            txtResults.setText("The deck has been dealt!\nPlease press Shuffle to start a new game." +
                    "\n"+p1Name+": "+countP1Wins+" wins\n"+p2Name+": "+countP2Wins+
                    " wins\nDraws: "+countDraws+"\nThe winner is: "+winner);
            btnWon.setVisible(false);
            btnShuffle.setVisible(true);
            resetImages();
        }
    }

    //about menu rules for hand rankings
    @FXML
    void onActionMenuRules(ActionEvent event) {
        JOptionPane.showMessageDialog(null, threeCard.getRules());
    }

    //calls determineWinner and sets the txt box
    @FXML
    void onActionWhoWon(ActionEvent event) {
        btnDeal.setVisible(true);
        btnWon.setVisible(false);
        determineWinner();
        txtResults.setText(result);
    }

    //Reset game
    @FXML
    void onActionShuffle(ActionEvent event) {

        cardDeck.shuffleDeck();
        btnDeal.setVisible(true);
        btnShuffle.setVisible(false);
        txtResults.setText("");
        resetImages();
        count = 0;
    }

    private void dealTheCards(){
        //arrays of card image names
        String[] p1CardNames = new String[3];
        String[] p2CardNames = new String[3];
        //lists of the imageviews for setting the images to the cards
        List<ImageView> p1Images = new ArrayList<>();
        List<ImageView> p2Images = new ArrayList<>();
        p1Images.add(imgP1One);
        p1Images.add(imgP1Two);
        p1Images.add(imgP1Three);
        p2Images.add(imgP2One);
        p2Images.add(imgP2Two);
        p2Images.add(imgP2Three);

        for(int i = 0; i<3; ++i){
            //getting a card to put in players hand
            p1[i] = cardDeck.getNext();
            //getting the image name of the card
            p1CardNames[i] = p1[i].getImageName();
            //creating the image object
            Image image = new Image(this.getClass().getResource("images/"+p1CardNames[i]).toExternalForm());
            //putting the image into the image view
            p1Images.get(i).setImage(image);
        }
        for(int i = 0; i<3; ++i){
            p2[i] = cardDeck.getNext();
            p2CardNames[i] = p2[i].getImageName();
            Image image = new Image(this.getClass().getResource("images/"+p2CardNames[i]).toExternalForm());
            p2Images.get(i).setImage(image);
        }
    }

    //finds the winner, creates result statement, and counts the wins/draws
    private void determineWinner(){
        threeCard.setPlayerHands(p1, p2);
        int winner = threeCard.getWinningHand();
        String winCondition = threeCard.getWinCondition();
        switch (winner){
            case 0:
                result = "Nobody wins, neither hand has any value.";
                break;
            case 1:
                result = p1Name+winCondition;
                countP1Wins++;
                break;
            case 2:
                result = p2Name+winCondition;
                countP2Wins++;
                break;
            case 3:
                result = "It's a draw!";
                countDraws++;
                break;
        }
    }

    /***************************************
     * This doesnt work when player name is not entered
     ***************************************/
    private void playerNames(){
        p1Name = txtP1.getText();
        if(p1Name == ""){
            p1Name = "Player One";
        }
        p2Name = txtP2.getText();
        if(p2Name == ""){
            p2Name = "Player Two";
        }
        lblP1.setText(p1Name);
        lblP2.setText(p2Name);
    }

    private void resetImages(){
        Image placeholderP1 = new Image(this.getClass().getResource("images/b2fv.png").toExternalForm());
        Image placeholderP2 = new Image(this.getClass().getResource("images/b1fv.png").toExternalForm());
        imgP1One.setImage(placeholderP1);
        imgP1Two.setImage(placeholderP1);
        imgP1Three.setImage(placeholderP1);
        imgP2One.setImage(placeholderP2);
        imgP2Two.setImage(placeholderP2);
        imgP2Three.setImage(placeholderP2);
    }

    //initializing the form
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnWon.setVisible(false);
        btnShuffle.setVisible(false);
        resetImages();
    }
}

