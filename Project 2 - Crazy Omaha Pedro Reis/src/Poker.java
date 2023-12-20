import objects.Card;
import objects.Player;
import objects.PokerHand;

import java.util.*;
import java.util.stream.IntStream;

public class Poker {
    public static void main(String[] args) {
        runPoker(args);
    }

    public static List<Player> runPoker(String[] args) {
        List<Card> argCards = Arrays.stream(args).map(Card::new).toList(); // convert input strings to Cards

        if (argCards.size() % 5 != 0) throw new IllegalArgumentException();

        int playersCount = (args.length - 5) / 5; // calc number of players based on args count

        List<List<Card>> playerCardLists = getCardListsOfPlayers(argCards, playersCount);

        List<Player> players = IntStream.range(0, playersCount).mapToObj(
                i -> new Player(
                        "Player " + (i + 1), // sets player name
                        playerCardLists.get(i), // sets the 10 card list of the player
                        PokerHand.NON_RAINBOW // set the best hand to lowest to start with
                )
        ).toList();

        for (Player player : players) {
            player.setBestHand(getBestHandForOfPlayer(player.getCards())); // best hand calculation logic for player happens here
        }

        ArrayList<Player> sortedPlayers = new ArrayList<>(players); // new list to hold the players sorted by the best hand
        sortedPlayers.sort(Comparator.comparing(Player::getBestHand).reversed()); // sort in the descending order of the best hand
        for (int i = 0; i < sortedPlayers.size(); i++) {
            System.out.println((i + 1) + ": " + sortedPlayers.get(i).getName()); // prints result to the console in the expected format
        }

        return sortedPlayers; // return value is used only for testing purposes, the result has been printed to console above
    }

    // for a given 10 cards (dealt 5+ community 5) of a person, get the best Hand that can be created
    // input `playerCards` is a list of 10 cards
    private static PokerHand getBestHandForOfPlayer(List<Card> playerCards) {
        // get all combinations of 6 cards that can be made with the 10 cards
        List<List<Card>> sixCardsCombinations = Util.getAllPossibleCombinations(playerCards, 6);

        PokerHand bestHandSoFar = PokerHand.NON_RAINBOW; // initialize to the lowest value

        for (List<Card> sixCardCombination : sixCardsCombinations) { // iterates through possible 6 card sets
            PokerHand handForCombination = getHandForSixCardCombination(sixCardCombination); // get the hand for current 6 cards
            bestHandSoFar = Collections.max(Arrays.asList(bestHandSoFar, handForCombination)); // updates the best hand if current 6 cards creates a better hand
        }

        return bestHandSoFar;
    }

    // gets the PokerHand corresponding to a set of 6 cards chosen
    // input `cards` is a list of 6 cards
    private static PokerHand getHandForSixCardCombination(List<Card> cards) {
        if (HandTypeResolver.isDinnerParty(cards)) return PokerHand.DINNER_PARTY;
        if (HandTypeResolver.isPolitics(cards)) return PokerHand.POLITICS;
        if (HandTypeResolver.isOrgy(cards)) return PokerHand.ORGY;
        if (HandTypeResolver.isKingdom(cards)) return PokerHand.KINGDOM;
        if (HandTypeResolver.isHomoSapiens(cards)) return PokerHand.HOMOSAPIENS;
        if (HandTypeResolver.isOverfullHouse(cards)) return PokerHand.OVERFULL_HOUSE;
        if (HandTypeResolver.isTriplets(cards)) return PokerHand.TRIPLETS;
        if (HandTypeResolver.isFlush(cards)) return PokerHand.FLUSH;
        if (HandTypeResolver.isOdd(cards)) return PokerHand.ODD;
        if (HandTypeResolver.isEven(cards)) return PokerHand.EVEN;
        if (HandTypeResolver.isMonarchy(cards)) return PokerHand.MONARCHY;
        if (HandTypeResolver.isThreePair(cards)) return PokerHand.THREE_PAIR;
        if (HandTypeResolver.isMonochromatic(cards)) return PokerHand.MONOCHROMATIC;
        if (HandTypeResolver.isSwingers(cards)) return PokerHand.SWINGERS;
        if (HandTypeResolver.isRainbow(cards)) return PokerHand.RAINBOW;
        if (HandTypeResolver.isNonRainbow(cards)) return PokerHand.NON_RAINBOW;

        return PokerHand.NON_RAINBOW;
    }

    // returns a list of card lists; i th value is the card list of the i th player (including his dealt cards and community cards)
    private static List<List<Card>> getCardListsOfPlayers(List<Card> argCards, int playersCount) {
        List<Card> communityCards = argCards.subList(0, 5);

        // playerCardLists is a list of card lists; i th value is the card list of the i th player (including his dealt cards and community cards)
        List<List<Card>> playerCardLists = new ArrayList<>();

        for (int i = 0; i < playersCount; i++) {
            List<Card> playerCards = new ArrayList<>(); // creates a list of cards to hold the cards of the i th player
            playerCards.addAll(communityCards); // adds community cards
            playerCards.addAll(argCards.subList((i + 1) * 5, (i + 2) * 5)); // adds cards dealt to i th player

            playerCardLists.add(playerCards); // adds the created list to the list of lists
        }

        return playerCardLists;
    }
}
