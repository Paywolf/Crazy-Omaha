package objects;

public class Card {
    public CardRank rank; // A, 2, 3, ..., K
    public CardSuit suit; // c, d, s, h

    // creates a Card based on the text as given in the input args
    public Card(String textRepresentation){
        rank = CardRank.fromChar(textRepresentation.charAt(0));
        suit = CardSuit.fromChar(textRepresentation.charAt(1));
    }

    public CardRank getRank() {
        return rank;
    }

    public CardSuit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return Character.valueOf(rank.getName()).toString().toUpperCase() + "-"  + suit.getUnicodeSymbol();
    }
}
