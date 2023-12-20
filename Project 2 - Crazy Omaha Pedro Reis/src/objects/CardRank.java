package objects;

public enum CardRank {
    ACE('A'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    TEN('T'),
    JACK('J'),
    QUEEN('Q'),
    KING('K');

    private final char name;

    CardRank(char name) {
        this.name = name;
    }

    public char getName() {
        return name;
    }

    public boolean isEven() {
        return this == TWO || this == FOUR || this == SIX || this == EIGHT || this == TEN;
    }

    public boolean isOdd() {
        return this == THREE || this == FIVE || this == SEVEN || this == NINE;
    }

    public boolean isFaceCard() {
        return this == KING || this == QUEEN || this == JACK;
    }

    @Override
    public String toString() {
        return Character.toString(name);
    }

    // gets the enum instance from given char name
    public static CardRank fromChar(char name) {
        for (CardRank val : CardRank.values()) {
            if (Character.toUpperCase(val.name) == Character.toUpperCase(name)) {
                return val;
            }
        }
        throw new IllegalArgumentException("No enum constant with rank name: " + name);
    }
}