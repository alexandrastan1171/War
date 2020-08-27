import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players = new ArrayList<>();
    private int nrOfPlayers;
    private ArrayList<Pair<Player, Card>> currentGame = new ArrayList<>();
    private ArrayList<Card> lostCards = new ArrayList<>();

    public Game(int nrOfPlayers, String[] playersNames) {
        this.nrOfPlayers = nrOfPlayers;
        for (int i = 0; i < nrOfPlayers; i++) {
            players.add(new Player(playersNames[i]));
        }
        shareCards();
        startGame();
        /*for (Player player : players) {
            player.afisare();
            System.out.println("next");
        }*/
    }

    private void roundStatus() {
        int max = 0;
        boolean isWar = false;
        Player bestPlayer = players.get(0);

        for (Pair<Player, Card> player : currentGame) {
            if (max < player.getValue().getType().getValue()) {
                //System.out.println("verif!!!!"+  max+" "+player.getValue().getType().getValue());
                bestPlayer = player.getKey();
                max = player.getValue().getType().getValue();
            } else if (max == player.getValue().getType().getValue()) {
                System.out.println("WAR!!!!");
                war(player.getKey(), bestPlayer, max);
                isWar = true;
                break;
            }
        }

        if (!isWar) {
            System.out.println(bestPlayer.getName() + " gets all cards");
            bestPlayer.addCards(lostCards);
            lostCards.clear();
            currentGame.clear();
        }
    }

    private void war(Player player1, Player player2, int warCard) {

        if (player1.getPackSize() < warCard && player2.getPackSize() >= player1.getPackSize())
            warCard = player1.getPackSize();
        else if (player2.getPackSize() < warCard && player2.getPackSize() <= player1.getPackSize())
            warCard = player2.getPackSize();
        while (warCard != 0) {
            lostCards.add(player1.popCard());
            lostCards.add(player2.popCard());
            System.out.println(player1.getName() + ": " + lostCards.get(lostCards.size() - 2).getType() + " of " + lostCards.get(lostCards.size() - 2).getPattern());
            System.out.println(player2.getName() + ": " + lostCards.get(lostCards.size() - 1).getType() + " of " + lostCards.get(lostCards.size() - 1).getPattern());
            warCard--;
        }
        if (lostCards.get(lostCards.size() - 1).getType().getValue() > lostCards.get(lostCards.size() - 2).getType().getValue()) {
            System.out.println(player2.getName() + " gets all cards");
            player2.addCards(lostCards);
        } else if (lostCards.get(lostCards.size() - 1).getType().getValue() < lostCards.get(lostCards.size() - 2).getType().getValue()) {
            System.out.println(player1.getName() + " gets all cards");
            player1.addCards(lostCards);
        } else {
            war(player1, player2, lostCards.get(lostCards.size() - 1).getType().getValue());
            System.out.println("WAR AGAIN!!!!");
        }
        lostCards.clear();
        currentGame.clear();
    }

    private boolean endGame() {
        for (Player player : players) {
            if (player.getPackSize() == 0) {
                playerOutOfCards(player);
                break;
            }
        }
        if (nrOfPlayers == 1) {
            System.out.println(players.get(0).getName() + " is the winner!!!!!!");
            return true;
        }
        return false;
    }

    private void playerOutOfCards(Player player) {
        System.out.println();
        System.out.println(player.getName() + " has lost the game");
        players.remove(player);
        nrOfPlayers--;
    }

    private void startGame() {
        while (!endGame()) {
            /*try {
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(System.in));
                String next = reader.readLine();*/
            System.out.println();
            for (Player player : players) {
                Card currentCard = player.popCard();
                System.out.println(player.getName() + ": " + currentCard.getType() + " of " + currentCard.getPattern());
                currentGame.add(new Pair<>(player, currentCard));
                lostCards.add(currentCard);
            }
            roundStatus();
           /* } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }

    private void shareCards() {
        Suite suite = new Suite();
        suite.shuffleCards();
        for (int i = 0; i < 52 / nrOfPlayers; i++) {
            for (Player player : players) {
                player.addCard(suite.getCard());
            }
        }
        if (suite.getSize() != 0) {
            for (int i = 0; i < suite.getSize(); i++) {
                players.get(i).addCard(suite.getCard());
            }
        }
    }
}
