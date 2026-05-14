import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {

    JButton buttons[][] = new JButton[3][3];
    boolean playerX = true;
    JLabel status;

    TicTacToe() {

        setTitle("Tic Tac Toe");
        setSize(400, 450);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        status = new JLabel("Player X Turn");
        status.setHorizontalAlignment(JLabel.CENTER);
        status.setFont(new Font("Arial", Font.BOLD, 20));
        add(status, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,3));

        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {

                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 50));
                buttons[i][j].addActionListener(this);

                panel.add(buttons[i][j]);
            }
        }

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton)e.getSource();

        if(!button.getText().equals("")) {
            return;
        }

        if(playerX) {
            button.setText("X");
            status.setText("Player O Turn");
        } else {
            button.setText("O");
            status.setText("Player X Turn");
        }

        playerX = !playerX;

        checkWinner();
    }

    void checkWinner() {

        for(int i=0; i<3; i++) {

            if(buttons[i][0].getText().equals(buttons[i][1].getText()) &&
               buttons[i][1].getText().equals(buttons[i][2].getText()) &&
               !buttons[i][0].getText().equals("")) {

                winner(buttons[i][0].getText());
            }

            if(buttons[0][i].getText().equals(buttons[1][i].getText()) &&
               buttons[1][i].getText().equals(buttons[2][i].getText()) &&
               !buttons[0][i].getText().equals("")) {

                winner(buttons[0][i].getText());
            }
        }

        if(buttons[0][0].getText().equals(buttons[1][1].getText()) &&
           buttons[1][1].getText().equals(buttons[2][2].getText()) &&
           !buttons[0][0].getText().equals("")) {

            winner(buttons[0][0].getText());
        }

        if(buttons[0][2].getText().equals(buttons[1][1].getText()) &&
           buttons[1][1].getText().equals(buttons[2][0].getText()) &&
           !buttons[0][2].getText().equals("")) {

            winner(buttons[0][2].getText());
        }
    }

    void winner(String player) {

        JOptionPane.showMessageDialog(this, "Player " + player + " Wins!");

        int option = JOptionPane.showConfirmDialog(this,
                "Do you want to play again?");

        if(option == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    void resetGame() {

        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                buttons[i][j].setText("");
            }
        }

        playerX = true;
        status.setText("Player X Turn");
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}