package PokerGame;

// 플레이어 클래스 - 플레이어 정보 관리
class Player {
    private final String nickName;
    private int winGames;
    private int loseGame;
    private int points;
    private int gameMoney;
    private Card[] cards;

    Player(String nickName) {
        this.nickName = nickName;
        this.winGames = 0;
        this.loseGame = 0;
        this.points = 0;
        this.gameMoney = 10000;
        this.cards = new Card[5];
    }

    public String getNickName() {
        return nickName;
    }

    public int getWinGames() {
        return winGames;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addWin() {
        winGames++;
    }

    public void addLoss() {
        loseGame++;
    }

    public void addMoney(int amount) {
        gameMoney += amount;
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return String.format("%-20s | gameMoney : %-8d | Wins : %-4d | Loses : %-4d",
                nickName, gameMoney, winGames, loseGame);
    }
}