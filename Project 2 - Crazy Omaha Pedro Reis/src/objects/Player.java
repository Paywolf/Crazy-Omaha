package objects;

import java.util.List;

// Represents a single player
public class Player {
    private String name; // name of the player (ex: Player 2)
    private List<Card> cards; // set of 10 cards of the player (community + dealt)
    private PokerHand bestHand; // the rank of the best 6 card hand the player can create with his 10 cards

    // constructor
    public Player(String name, List<Card> cards, PokerHand bestHand) {
        this.name = name;
        this.cards = cards;
        this.bestHand = bestHand;
    }

    // getters and setters
    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public PokerHand getBestHand() {
        return bestHand;
    }

    public void setBestHand(PokerHand bestHand) {
        this.bestHand = bestHand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
