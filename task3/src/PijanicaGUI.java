import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class PijanicaGUI extends JFrame {
    private JTextArea player1Cards;
    private JTextArea player2Cards;
    private JTextArea lastTurn;
    private JButton playButton;
    private Pijanica1 game;

    public PijanicaGUI() {
        setTitle("Pijanica1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());
        game = new Pijanica1();
        player1Cards = new JTextArea();
        player2Cards = new JTextArea();
        player1Cards.setEditable(false);
        player2Cards.setEditable(false);
        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new GridLayout(1, 2));
        cardsPanel.add(new JScrollPane(player1Cards));
        cardsPanel.add(new JScrollPane(player2Cards));
        lastTurn = new JTextArea();
        lastTurn.setEditable(false);
        JPanel lastTurnPanel = new JPanel();
        lastTurnPanel.setLayout(new BorderLayout());
        lastTurnPanel.add(new JLabel("Last turn:"), BorderLayout.NORTH);
        lastTurnPanel.add(new JScrollPane(lastTurn), BorderLayout.CENTER);
        playButton = new JButton("Play");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playButton);
        add(cardsPanel, BorderLayout.CENTER);
        add(lastTurnPanel, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.NORTH);
        updateCards();
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.playTurn();
                if (game.isGameOver()) {
                    playButton.setEnabled(false);
                    JOptionPane.showMessageDialog(PijanicaGUI.this, game.getWinnerMessage(), "Game over", JOptionPane.INFORMATION_MESSAGE);
                }
                updateCards();
                updateLastTurn();
            }
        });
    }

    private void updateCards() {
        // Обновляем текстовые поля для карт игроков
        player1Cards.setText(game.getPlayer1Cards());
        player2Cards.setText(game.getPlayer2Cards());
    }

    private void updateLastTurn() {
        lastTurn.setText(game.getLastTurn());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PijanicaGUI().setVisible(true);
            }
        });
    }
}

class Pijanica1 {
    private Queue<Integer> player1;
    private Queue<Integer> player2;
    private StringBuilder lastTurn;

    public Pijanica1() {
        // Создаем колоду из 36 карт
        ArrayList<Integer> deck = new ArrayList<>(36);
        for (int i = 0; i < 4; i++) {
            for (int j = 6; j <= 14; j++) {
                deck.add(j);
            }
        }

        // Перемешиваем колоду
        Collections.shuffle(deck);

        // Раздаем карты игрокам
        player1 = new LinkedList<>();
        player2 = new LinkedList<>();
        for (int i = 0; i < 36; i += 2) {
            player1.add(deck.get(i));
            player2.add(deck.get(i + 1));
        }

        // Настраиваем текст для последнего хода
        lastTurn = new StringBuilder();
        lastTurn.append("Player 1: ");
        lastTurn.append(player1.peek());
        lastTurn.append("\n");
        lastTurn.append("Player 2: ");
        lastTurn.append(player2.peek());
        lastTurn.append("\n");
        lastTurn.append("---\n");
    }

    public String getPlayer1Cards() {
        return player1.toString();
    }

    public String getPlayer2Cards() {
        return player2.toString();
    }

    public String getLastTurn() {
        return lastTurn.toString();
    }

    public void playTurn() {
        int card1 = player1.poll();
        int card2 = player2.poll();

        // Сравниваем карты
        if (card1 > card2) {
            player1.add(card1);
            player1.add(card2);
            lastTurn.append("Player 1 wins the turn\n");
        } else if (card1 < card2) {
            player2.add(card2);
            player2.add(card1);
            lastTurn.append("Player 2 wins the turn\n");
        } else {
            // Если карты равны, происходит "спор"
            // Берем по карте у каждого игрока и снова сравниваем
            if (player1.isEmpty() || player2.isEmpty()) {
                // Если у одного из игроков нет карт для спора, другой игрок берет обе карты
                if (player1.isEmpty()) {
                    player2.add(card1);
                    player2.add(card2);
                    lastTurn.append("Player 2 wins the turn (no cards for war)\n");
                } else {
                    player1.add(card1);
                    player1.add(card2);
                    lastTurn.append("Player 1 wins the turn (no cards for war)\n");
                }
            } else {
                int card1Spor = player1.poll();
                int card2Spor = player2.poll();
                if (card1Spor > card2Spor) {
                    // Если у первого игрока карта в споре старше, он берет все 4 карты
                    player1.add(card1);
                    player1.add(card2);
                    player1.add(card1Spor);
                    player1.add(card2Spor);
                    lastTurn.append("Player 1 wins the war\n");
                } else {
                    // Если у второго игрока карта в споре старше, он берет все 4 карты
                    player2.add(card2);
                    player2.add(card1);
                    player2.add(card2Spor);
                    player1.add(card1Spor);
                    lastTurn.append("Player 2 wins the war\n");
                }
            }
        }

        lastTurn.append("Player 1: ");
        lastTurn.append(player1.peek());
        lastTurn.append("\n");
        lastTurn.append("Player 2: ");
        lastTurn.append(player2.peek());
        lastTurn.append("\n");
        lastTurn.append("---\n");
    }

    public boolean isGameOver() {
        // Проверяем, закончилась ли игра
        return player1.isEmpty() || player2.isEmpty();
    }

    public String getWinnerMessage() {
        // Возвращаем сообщение о победителе
        if (player1.isEmpty()) {
            return "Player 2 wins!";
        } else {
            return "Player 1 wins!";
        }
    }
}
