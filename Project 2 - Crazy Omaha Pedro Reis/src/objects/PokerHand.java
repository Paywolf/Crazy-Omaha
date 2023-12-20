package objects;

// Represents the rank of a hand that can be created with 6 cards
public enum PokerHand {
    NON_RAINBOW("Non-Rainbow", "2♣3♣4♣5♦8♦J♥", "You don’t have at least one of each suit."),
    RAINBOW("Rainbow", "8♣4♥6♦Q♠K♠A♣", "You have one of each suit."),
    SWINGERS("Swingers", "K♠Q♠8♦3♥K♦Q♦", "You have two sets of suited Kings and Queens."),
    MONOCHROMATIC("Monochromatic", "8♦4♦Q♥4♦9♥K♦", "Your cards are either all black or all red."),
    THREE_PAIR("3 Pair", "4♠4♦8♣8♦9♥9♠", "Cards consist of exactly three unique ranks."),
    MONARCHY("Monarchy", "4♠8♠T♥J♠Q♠K♠", "You hold A Jack, Queen and King of the same suit and no other face cards."),
    EVEN("Even", "2♥4♠6♦8♣T♣T♥", "All your cards are a 2,4,6,8 or 10."),
    ODD("Odd", "3♠5♥7♦7♦9♣9♠", "All of your cards are a 3,5,7 or 9."),
    FLUSH("Flush", "3♠5♠6♠7♠J♠Q♠", "All six of your cards are the same suit."),
    TRIPLETS("Triplets", "3♠3♦3♥T♦T♣T♥", "You have two different three of a kinds."),
    OVERFULL_HOUSE("Overfull House", "5♠5♦5♥5♣J♠J♣", "Four of a kind and a pair."),
    HOMOSAPIENS("Homosapiens", "J♠J♦J♣Q♠Q♦K♣", "All your cards are face cards."),
    KINGDOM("Kingdom", "4♠8♠T♠J♠Q♠K♠", "(Monarchy + flush) Same as a Monarchy but the remaining cards are of the same."),
    ORGY("Orgy", "J♠J♦J♣J♥Q♠Q♥", "All your cards are Jacks and Queens."),
    POLITICS("Politics", "J♠J♦Q♠Q♦K♠K♦", "You hold two Monarchys."),
    DINNER_PARTY("Dinner Party", "Q♠Q♦Q♥K♠K♦K♥", "All your cards are suited kings and queens.");

    private final String name;
    private final String example;
    private final String description;

    PokerHand(String name, String example, String description) {
        this.name = name;
        this.example = example;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getExample() {
        return example;
    }

    public String getDescription() {
        return description;
    }
}
