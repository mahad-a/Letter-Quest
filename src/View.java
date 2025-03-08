import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View implements ModelView{
    Model model;
    Controller controller;
    JButton[][] buttons;

    public View(){
        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(5,5));
        frame.setTitle("Letter Quest");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        model = new Model();
        controller = new Controller(model);
        buttons = new JButton[5][5];

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        menuBar.add(menu);
        JMenuItem newGame = new JMenuItem("New game");
        menu.add(newGame);

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        controller.askWord();

        setUpGame(frame);

        frame.setVisible(true);
        frame.setJMenuBar(menuBar);
    }

    private void startNewGame() {
        model = new Model();
        controller = new Controller(model);
        controller.askWord();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
                buttons[i][j].setBackground(null);
            }
        }
    }

    public void setUpGame(JFrame frame){
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                JButton button = new JButton(" ");
                button.setActionCommand(i + " " + j);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String[] position = e.getActionCommand().split(" ");
                        String w = (JOptionPane.showInputDialog(null, "Enter your guess.")).toLowerCase();

                        for (int i = 0; i < 5; i++) {
                            if (buttons[i][0].isEnabled()) {
                                checkGuess(i, w);
                                break;
                            }
                        }
                    }
                });
                this.buttons[i][j] = button;
                frame.add(button);
            }
        }
    }

    public void correctGuess(int row, int col, String text){
        buttons[row][col].setText(text);
        buttons[row][col].setBackground(Color.GREEN);
    }

    public void misplacedGuess(int row, int col, String text){
        buttons[row][col].setText(text);
        buttons[row][col].setBackground(Color.YELLOW);
    }

    public void checkGuess(int row, String text) {
        int count = 0;
        String answer = controller.getEnterGuess();
        boolean[] matched = new boolean[5];

        if (text.length() < 5) {
            text = text + "?".repeat(5 - text.length());
        }

        for (int i = 0; i < 5; i++) {
            buttons[row][i].setText(String.valueOf(text.charAt(i)));
            buttons[row][i].setEnabled(false);

            if (answer.charAt(i) == text.charAt(i)) {
                correctGuess(row, i, String.valueOf(text.charAt(i)));
                matched[i] = true;
                count++;
            }
        }

        for (int i = 0; i < 5; i++) {
            if (matched[i]) continue;

            for (int j = 0; j < 5; j++) {
                if (i != j && !matched[j] && text.charAt(i) == answer.charAt(j)) {
                    misplacedGuess(row, i, String.valueOf(text.charAt(i)));
                    break;
                }
            }
        }

        if (count == 5) {
            JOptionPane.showMessageDialog(null, "Congrats! You guessed the word.");
            disableAllButtons();
            return;
        }

        if (row == 4) {
            JOptionPane.showMessageDialog(null, ("Failed! You didn't guessed the word. The word was " + answer));
            disableAllButtons();
        }
    }

    public void disableAllButtons() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    @Override
    public void handleStatusUpdate(Event e){
        System.out.println(controller.getEnterGuess());
        this.buttons[e.getX()][e.getY()].setText(e.getGuess());
    }

}
