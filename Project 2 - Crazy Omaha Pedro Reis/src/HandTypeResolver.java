import objects.Card;
import objects.CardRank;
import objects.CardSuit;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HandTypeResolver {
    Set<CardSuit> ALL_SUITS = Set.of(CardSuit.HEARTS, CardSuit.CLUBS, CardSuit.DIAMONDS, CardSuit.SPADES);

    public static Set<CardSuit> getUniqueSuitsOfCards(List<Card> cards) {
        return cards.stream().map(Card::getSuit).collect(Collectors.toSet());
    }

    public static Set<CardRank> getUniqueRanksOfCards(List<Card> cards) {
        return cards.stream().map(Card::getRank).collect(Collectors.toSet());
    }

    // Hand type determine methods below -------------------------------------------------------------------------------

    // NON_RAINBOW("Non-Rainbow", "2♣3♣4♣5♦8♦J♥", "You don’t have at least one of each suit.")
    public static boolean isNonRainbow(List<Card> cards) {
        return !isRainbow(cards); // check if not a rainbow
    }

    // RAINBOW("Rainbow", "8♣4♥6♦Q♠K♠A♣", "You have one of each suit.")
    public static boolean isRainbow(List<Card> cards) {
        Set<CardSuit> suits = getUniqueSuitsOfCards(cards);
        return suits.size() == 4; // check if all 4 suits are there
    }

    // SWINGERS("Swingers", "K♠Q♠8♦3♥K♦Q♦", "You have two sets of suited Kings and Queens.")
    public static boolean isSwingers(List<Card> cards) {
        List<Card> kingCards = cards.stream().filter(x -> x.rank == CardRank.KING).toList(); // get all kings
        Set<CardSuit> kingSuits = getUniqueSuitsOfCards(kingCards); // get all king suits

        List<Card> queenCards = cards.stream().filter(x -> x.rank == CardRank.QUEEN).toList(); // get all kings
        Set<CardSuit> queenSuits = getUniqueSuitsOfCards(queenCards); // get all queen suits

        Set<CardSuit> intersection = new HashSet<>(kingSuits);
        intersection.retainAll(queenSuits); // get the intersection set of kingSuits and queenSuits

        return intersection.size() >= 2;
    }

    // MONOCHROMATIC("Monochromatic", "8♦4♦Q♥4♦9♥K♦", "Your cards are either all black or all red.")
    public static boolean isMonochromatic(List<Card> cards) {
        Set<CardSuit> suits = getUniqueSuitsOfCards(cards);

        // check if all red or all black
        return suits.stream().allMatch(CardSuit::isRed) || suits.stream().allMatch(CardSuit::isBlack);
    }

    // THREE_PAIR("3 Pair", "4♠4♦8♣8♦9♥9♠", "Cards consist of exactly three unique ranks.")
    public static boolean isThreePair(List<Card> cards) {
        Set<CardRank> ranks = getUniqueRanksOfCards(cards);
        return ranks.size() == 3; // check if 3 ranks are there
    }

    // MONARCHY("Monarchy", "4♠8♠T♥J♠Q♠K♠", "You hold A Jack, Queen and King of the same suit and no other face cards.")
    public static boolean isMonarchy(List<Card> cards) {
        List<Card> faceCards = cards.stream().filter(x -> x.rank.isFaceCard()).toList(); // get all face cards
        if (faceCards.size() != 3) return false; // if there aren't 3 face cards (for K Q J) return false

        Set<CardSuit> faceCardSuits = getUniqueSuitsOfCards(faceCards); // get unique suits of face cards
        if (faceCardSuits.size() != 1) return false; // if face cards are from different suits return false

        // no need to check if the cards are K Q and J, since in a card pack there is no other way to select 3 face cards from the same suit
        return true;
    }

    // EVEN("Even", "2♥4♠6♦8♣T♣T♥", "All your cards are a 2,4,6,8 or 10.")
    public static boolean isEven(List<Card> cards) {
        return cards.stream().allMatch(x -> x.rank.isEven());
    }

    // ODD("Odd", "3♠5♥7♦7♦9♣9♠", "All of your cards are a 3,5,7 or 9.")
    public static boolean isOdd(List<Card> cards) {
        return cards.stream().allMatch(x -> x.rank.isOdd());
    }

    // FLUSH("Flush", "3♠5♠6♠7♠J♠Q♠", "All six of your cards are the same suit.")
    public static boolean isFlush(List<Card> cards) {
        Set<CardSuit> suits = getUniqueSuitsOfCards(cards);
        return suits.size() == 1; // check if exactly 1 suit is there
    }

    // TRIPLETS("Triplets", "3♠3♦3♥T♦T♣T♥", "You have two different three of a kinds.")
    public static boolean isTriplets(List<Card> cards) {
        Set<CardRank> ranks = getUniqueRanksOfCards(cards);
        if (ranks.size() != 2) return false; // check if 2 unique ranks are there

        CardRank anyOfTheTwoRanks = ranks.stream().findAny().get(); // get anyone out of the 2 ranks available
        long count = cards.stream().filter(x -> x.rank == anyOfTheTwoRanks).count(); // get the card count with the above rank

        return count == 3; // checks if the count is 3
    }

    // OVERFULL_HOUSE("Overfull House", "5♠5♦5♥5♣J♠J♣", "Four of a kind and a pair.")
    public static boolean isOverfullHouse(List<Card> cards) {
        Set<CardRank> ranks = getUniqueRanksOfCards(cards);
        if (ranks.size() != 2) return false; // check if 2 unique ranks are there

        CardRank anyOfTheTwoRanks = ranks.stream().findAny().get(); // get anyone out of the 2 ranks available
        long count = cards.stream().filter(x -> x.rank == anyOfTheTwoRanks).count(); // get the card count with the above rank

        return count == 4 || count == 2; // checks if the count is 4 or 2
    }

    // HOMOSAPIENS("Homosapiens", "J♠J♦J♣Q♠Q♦K♣", "All your cards are face cards.")
    public static boolean isHomoSapiens(List<Card> cards) {
        return cards.stream().allMatch(x -> x.rank.isFaceCard()); // check if all are face cards
    }

    // KINGDOM("Kingdom", "4♠8♠T♠J♠Q♠K♠", "(Monarchy + flush) Same as a Monarchy but the remaining cards are of the same.")
    public static boolean isKingdom(List<Card> cards) {
        return isMonarchy(cards) && isFlush(cards);
    }

    // ORGY("Orgy", "J♠J♦J♣J♥Q♠Q♥", "All your cards are Jacks and Queens.")
    public static boolean isOrgy(List<Card> cards) {
        long queensCount = cards.stream().filter(x -> x.rank == CardRank.QUEEN).count(); // count # of queens
        long jacksCount = cards.stream().filter(x -> x.rank == CardRank.JACK).count(); // count # of jacks

        return (queensCount + jacksCount) == 6; // check if all 6 are queen or jack
    }

    // POLITICS("Politics", "J♠J♦Q♠Q♦K♠K♦", "You hold two Monarchys.")
    public static boolean isPolitics(List<Card> cards) {
        Set<CardSuit> suits = getUniqueSuitsOfCards(cards);
        if (suits.size() != 2) return false; // checks exactly 2 types of cards present
        if (cards.stream().filter(x -> x.rank == CardRank.KING).count() != 2) return false; // checks for 2 kings
        if (cards.stream().filter(x -> x.rank == CardRank.QUEEN).count() != 2) return false; // checks for 2 queens
        if (cards.stream().filter(x -> x.rank == CardRank.JACK).count() != 2) return false; // checks for 2 jacks

        return true;
    }

    // DINNER_PARTY("Dinner Party", "Q♠Q♦Q♥K♠K♦K♥", "All your cards are suited kings and queens.")
    public static boolean isDinnerParty(List<Card> cards) {
        Set<CardSuit> suits = getUniqueSuitsOfCards(cards);
        if (suits.size() != 3) return false; // checks exactly 3 types of cards present
        if (cards.stream().filter(x -> x.rank == CardRank.KING).count() != 3) return false; // checks for 3 kings
        if (cards.stream().filter(x -> x.rank == CardRank.QUEEN).count() != 3) return false; // checks for 3 queens

        return true;
    }
}
