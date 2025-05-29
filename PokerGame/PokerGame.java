package PokerGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// * 포커 게임 클래스 - 게임 진행을 관리
class PokerGame {
    private static final int MAX_PLAYERS = 4;
    private static final int NUMBER_OF_ROUNDS = 100;
    private final ArrayList<Player> players;
    private final Dealer dealer;
    private final Scanner scanner;

    public PokerGame() {
        this.players = new ArrayList<>();
        this.dealer = new Dealer();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        registerPlayers();
        playRounds();
        displayResults();
        scanner.close();
    }

    private void registerPlayers() {
        while (true) {
            if (players.size() >= MAX_PLAYERS) {
                System.out.println("최대 플레이어 인원은 " + MAX_PLAYERS + "명입니다. 등록을 종료하고 게임을 시작합니다.");
                break;
            }

            System.out.println("플레이어 등록을 하시려면 1번, 등록을 끝내시려면 2번을 눌러주세요. ");
            System.out.print("입력 : ");
            int option = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            if (option == 1) {
                registerSinglePlayer();
            } else if(option == 2){
                System.out.println("플레이어 등록을 종료하고 게임을 시작합니다.");
                break;
            } else{
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
            }
        }
    }

    private void registerSinglePlayer() {
        System.out.print("참여할 플레이어 이름을 입력해주세요 : ");
        String nickName = scanner.nextLine();

        if (nickName.length() > 20) {
            System.out.println("이름은 20자를 넘을 수 없습니다");
        } else {
            players.add(new Player(nickName));
        }
    }

    private void playRounds() {
        Deck deck = new Deck();
        for (int round = 0; round < NUMBER_OF_ROUNDS; round++) {
            deck.shuffle();
            dealCardsToPlayers(deck);
            dealer.CalculatePoint(players);
            System.out.println();
        }
    }

    private void dealCardsToPlayers(Deck deck) {
        ArrayList<Card> usedCards = new ArrayList<>();

        for (Player player : players) {
            Card[] playerCards = new Card[5];
            for (int i = 0; i < 5; i++) {
                Card card = getUniqueCard(deck, usedCards);
                playerCards[i] = card;
                usedCards.add(card);
            }
            player.setCards(playerCards);
        }
    }

    private Card getUniqueCard(Deck deck, List<Card> usedCards) {
        while (true) {
            Card card = deck.pick();
            if (!isCardDuplicate(card, usedCards)) {
                return card;
            }
        }
    }

    private boolean isCardDuplicate(Card card, List<Card> usedCards) {
        for (Card usedCard : usedCards) {
            if (card.number() == usedCard.number() && card.kind() == usedCard.kind()) {
                return true;
            }
        }
        return false;
    }

    private void displayResults() {
        players.sort(new PointDescending());
        System.out.println("--------------result--------------");
        for (Player player : players) {
            System.out.println(player);
        }
    }
}
