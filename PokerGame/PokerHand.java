package PokerGame;

enum PokerHand {
    ROYAL_FLUSH("RoyalFlush", 200_000_000) {
        public boolean matches(Card[] cards, PokerHandChecker checker) {
            return checker.isRoyalFlush(cards);
        }
    },
    STRAIGHT_FLUSH("StraightFlush", 190_000_000) {
        public boolean matches(Card[] cards, PokerHandChecker checker) {
            return checker.isStraightFlush(cards);
        }
    },
    FOUR_OF_A_KIND("FourOfAKind", 180_000_000) {
        public boolean matches(Card[] cards, PokerHandChecker checker) {
            return checker.isFourOfAKind(cards);
        }
    },
    FULL_HOUSE("FullHouse", 170_000_000) {
        public boolean matches(Card[] cards, PokerHandChecker checker) {
            return checker.isFullHouse(cards);
        }
    },
    FLUSH("Flush", 160_000_000) {
        public boolean matches(Card[] cards, PokerHandChecker checker) {
            return checker.isFlush(cards);
        }
    },
    STRAIGHT("Straight", 150_000_000) {
        public boolean matches(Card[] cards, PokerHandChecker checker) {
            return checker.isStraight(cards);
        }
    },
    THREE_OF_A_KIND("ThreeOfAKind", 140_000_000) {
        public boolean matches(Card[] cards, PokerHandChecker checker) {
            return checker.isThreeOfAKind(cards);
        }
    },
    TWO_PAIR("TwoPair", 130_000_000) {
        public boolean matches(Card[] cards, PokerHandChecker checker) {
            return checker.isTwoPair(cards);
        }
    },
    ONE_PAIR("OnePair", 120_000_000) {
        public boolean matches(Card[] cards, PokerHandChecker checker) {
            return checker.isOnePair(cards);
        }
    },
    HIGH_CARD("HighCard", 0) {
        public boolean matches(Card[] cards, PokerHandChecker checker) {
            return true; // 항상 마지막에 시도
        }

        @Override
        public int getPoints(Card[] cards, PokerHandChecker checker) {
            return checker.getHighCardScore(cards);
        }
    };

    private final String name;
    private final int basePoints;

    PokerHand(String name, int basePoints) {
        this.name = name;
        this.basePoints = basePoints;
    }

    public abstract boolean matches(Card[] cards, PokerHandChecker checker);

    public int getPoints(Card[] cards, PokerHandChecker checker) {
        return basePoints;
    }

    public String getName() {
        return name;
    }
}

