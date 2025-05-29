package PokerGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// 딜러 클래스
class Dealer {
    void CalculatePoint(ArrayList<Player> players) {
        PokerHandChecker checker = new PokerHandChecker();

        for (Player player : players) {
            for (PokerHand hand : PokerHand.values()) {
                if (hand.matches(player.getCards(), checker)) {
                    int points = hand.getPoints(player.getCards(), checker);
                    player.setPoints(points);
                    System.out.println(hand.getName());
                    break;
                }
            }
        }

        System.out.println();
        CheckDupleOnePair(players); // 원페어가 여러명일 때
        FindWinner(players);
    }

    public void FindOnePairPlayers(ArrayList<Player> players, ArrayList<Player> onePairPlayers){
        for(Player player : players) {
            if(player.getPoints() == 120000000) { // 원페어 점수인 경우
                onePairPlayers.add(player);
            }
        }
    }

    public void FindWinnerFromOnePairs(Map<Player, ArrayList<Integer>> highPairPlayers) {
        if (highPairPlayers.size() > 1) {
            ArrayList<Player> sameOnepairPlayer = new ArrayList<>(highPairPlayers.keySet());
            Player finalWinner = sameOnepairPlayer.getFirst();
            ArrayList<Integer> winnerCards = highPairPlayers.get(finalWinner);

            for (int i = 1; i < sameOnepairPlayer.size(); i++) {
                Player currentPlayer = sameOnepairPlayer.get(i);
                ArrayList<Integer> currentCards = highPairPlayers.get(currentPlayer);

                // 페어가 아닌 값들은 내림차순으로 정렬되어 있기에 가장 빠른 순서의 카드의 차이로 판단할 수 있음
                CompareNonPairCards(currentPlayer, currentCards, winnerCards, finalWinner);
            }
            finalWinner.setPoints(120000000 + finalWinner.getCards()[0].number()); // 페어 값을 더해 점수 구분
            System.out.println("최종 승자: " + finalWinner.getNickName() + ", 점수: " + finalWinner.getPoints());
        }
    }

    public void CompareNonPairCards(Player currentPlayer, ArrayList<Integer> currentCards, ArrayList<Integer> winnerCards, Player finalWinner){
        for (int j = 0; j < currentCards.size(); j++) {
            System.out.println("카드 비교 [" + j + "]: " + currentCards.get(j) + " vs " + winnerCards.get(j));
            if (currentCards.get(j) > winnerCards.get(j)) {
                finalWinner = currentPlayer;
                winnerCards = currentCards;
                break;
            } else if (currentCards.get(j) < winnerCards.get(j)) {
                break;
            }
            // 같으면 다음 카드 비교
        }
    }

    public void CheckDupleOnePair(ArrayList<Player> players) {
        // 원페어 플레이어들만 찾기
        ArrayList<Player> onePairPlayers = new ArrayList<>();

        FindOnePairPlayers(players, onePairPlayers);

        // 원페어 플레이어가 한 명이면 처리할 필요 없음
        if(onePairPlayers.size() <= 1) {
            System.out.println("원페어 플레이어가 없거나 한 명뿐이므로 추가 비교 필요 없음");
            return;
        }

        System.out.println("원페어 플레이어 수: " + onePairPlayers.size() + " - 추가 비교 시작");

        int highestPairValue = 0;
        // 같은 페어 값을 가진 플레이어들
        Map<Player, ArrayList<Integer>> highPairPlayers = new HashMap<>();

        //같은 페어 값을 가진 플레이어들 순위 정하기
        for(Player player : onePairPlayers) {
            Map<Integer, Integer> cardCount = new HashMap<>();
            // 카드별 몇장씩 있는지 카운트
            CountPerCards(player, cardCount);

            // 페어 값이 아닌 카드들
            ArrayList<Integer> others = new ArrayList<>();
            // 페어 값과 나머지 카드 찾기
            int pairValue = FindPairsAndOthers(cardCount, others);
            System.out.println(player.getNickName() + "의 페어 값: " + pairValue + ", 나머지 카드: " + others);

            // 더 높은 페어를 발견하면 이전 플레이어들 지우기
            if(pairValue > highestPairValue) {
                highestPairValue = pairValue;
                highPairPlayers.clear();
                highPairPlayers.put(player, others);
            }
            else if(pairValue == highestPairValue) {// 같은 페어 값이면 나중에 비교를 위해 저장
                highPairPlayers.put(player, others);
            }
        }

        // 각 플레이어의 페어 카드와 나머지 카드 찾기 & 가장 높은 페어 찾기
        System.out.println("최고 페어 값: " + highestPairValue + ", 동일 페어 플레이어 수: " + highPairPlayers.size());

        // 페어 값이 같은 플레이어가 여러 명이면 나머지 카드 비교
        if(highPairPlayers.size() > 1) {
            FindWinnerFromOnePairs(highPairPlayers);
        } else { // 페어 값이 같은 플레이어가 없을때
            Player winner = highPairPlayers.keySet().iterator().next();
            winner.setPoints(120000000 + highestPairValue);
            System.out.println("단일 원페어 승자: " + winner.getNickName() + ", 점수: " + winner.getPoints());
        }
    }

    public void CountPerCards(Player player, Map<Integer, Integer> cardCount){
        for(Card card : player.getCards()) {
            int num = card.number();
            if(num == 1) num = 14; // A는 가장 높은 숫자
            cardCount.put(num, cardCount.getOrDefault(num, 0) + 1);
        }
    }

    public int FindPairsAndOthers(Map<Integer, Integer> cardCount, ArrayList<Integer> others) {
        int pairValue = 0;
        for(Integer key : cardCount.keySet()) {
            if(cardCount.get(key) == 2) { // 페어이면
                pairValue = key;
            } else {
                others.add(key);
            }
        }
        others.sort(Collections.reverseOrder()); // 내림치순
        return pairValue;
    }

    public void FindWinner(ArrayList<Player> players){
        int winnerIndex = 0;
        int max = players.getFirst().getPoints();
        for (int i = 0; i < players.size(); i++) { // 최대값 찾기
            if (max < players.get(i).getPoints()) {
                max = players.get(i).getPoints();
                winnerIndex = i;
            }
            System.out.println(players.get(i).getNickName() + " : " + players.get(i).getPoints());
            players.get(i).setPoints(0); // 다음 포인트 게산을 위한 초기화
        }
        CompensateWinner(players, winnerIndex);
    }

    public void CompensateWinner(ArrayList<Player> players, int winnerIndex){
        for (int i = 0; i < players.size(); i++) {
            if (i == winnerIndex) { // 이기면 100원, 승리 포인트 1
                players.get(i).addWin();
                players.get(i).addMoney(100);
                System.out.println("승자는 " + i + "입니다");
                System.out.println("------------------------------------");
            }
            else // 지면 0원, 패배 포인트 1
                players.get(i).addLoss();
        }
    }
}

