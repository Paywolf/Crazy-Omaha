package objects;

public enum CardSuit {
    HEARTS('h', "\u2665"),
    DIAMONDS('d', "\u2666"),
    CLUBS('c', "\u2663"),
    SPADES('s', "\u2660");

    private final char name;
    private final String unicodeSymbol;

    CardSuit(char name, String unicodeSymbol) {
        this.name = name;
        this.unicodeSymbol = unicodeSymbol;
    }

    public char getName() {
        return name;
    }

    public String getUnicodeSymbol() {
        return unicodeSymbol;
    }

    public boolean isRed(){
        return this == HEARTS || this == DIAMONDS;
    }

    public boolean isBlack(){
        return !isRed();
    }

    @Override
    public String toString() {
        return Character.toString(name);
    }

    public static CardSuit fromChar(char name) {
        for (CardSuit val : CardSuit.values()) {
            if (Character.toUpperCase(val.name) == Character.toUpperCase(name)) {
                return val;
            }
        }
        throw new IllegalArgumentException("No enum constant with suit name: " + name);
    }

}