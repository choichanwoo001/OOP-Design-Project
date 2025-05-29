package PokerGame;

// 카드 클래스 - 카드 정보 관리
record Card(int kind, int number) {
    static final int KIND_MAX = 4;    // 카드 무늬의 수
    static final int NUM_MAX = 13;    // 무늬별 카드 수
}

