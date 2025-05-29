package PokerGame;

import java.util.Comparator;

// 플레이어 랭크 비교기 - 승리 횟수로 정렬
class PointDescending implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        return (o1.getWinGames() - o2.getWinGames()) * -1;
    }
}
