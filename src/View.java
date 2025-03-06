import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View implements ModelView{
    Model model;
    Controller controller;
    JButton buttons[][];
    JTextField input;
    public View(){
        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(5,5));
        frame.setTitle("Guess it!");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        model = new Model();
        controller = new Controller(model);
        buttons = new JButton[5][5];

        controller.askWord();

        JTextField input = new JTextField();

        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                JButton button = new JButton(" ");
                button.setActionCommand(i + " " + j);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String[] position = e.getActionCommand().split(" ");
                        String w = JOptionPane.showInputDialog(null, "Enter your guess.");
                        //model.setValue(Integer.parseInt(position[0]), Integer.parseInt(position[1]), w);
                        if (buttons[0][0].isEnabled()) {
                            checkGuess(0, w);
                        } else if (buttons[1][0].isEnabled()) {
                            checkGuess(1, w);
                        } else if (buttons[2][0].isEnabled()) {
                            checkGuess(2, w);
                        } else if (buttons[3][0].isEnabled()) {
                            checkGuess(3, w);
                        } else if (buttons[4][0].isEnabled()) {
                            checkGuess(4, w);
                        }
                    }
                });
                this.buttons[i][j] = button;
                frame.add(button);
            }
        }
        // frame.add(input);

        frame.setVisible(true);
    }

    public void correctGuess(int row, int col, String text){
        buttons[row][col].setText(text);
        buttons[row][col].setBackground(Color.GREEN);
    }

    public void misplacedGuess(int row, int col, String text){
        buttons[row][col].setText(text);
        buttons[row][col].setBackground(Color.YELLOW);
    }

    public void checkGuess(int row, String text){
        int count = 0;
        buttons[row][0].setText((String.valueOf(text.charAt(0))));
        buttons[row][0].setEnabled(false);
        if (controller.getEnterGuess().charAt(0) == ((text.charAt(0)))){
            correctGuess(row, 0, (String.valueOf(text.charAt(0))));
            count++;
        }
        buttons[row][1].setText((String.valueOf(text.charAt(1))));
        buttons[row][1].setEnabled(false);
        if (controller.getEnterGuess().charAt(1) == ((text.charAt(1)))) {
            correctGuess(row, 1, (String.valueOf(text.charAt(1))));
            count++;
        }
        buttons[row][2].setText((String.valueOf(text.charAt(2))));
        buttons[row][2].setEnabled(false);

        if (controller.getEnterGuess().charAt(2) == ((text.charAt(2)))) {
            correctGuess(row, 2, (String.valueOf(text.charAt(2))));
            count++;

        }
        buttons[row][3].setText((String.valueOf(text.charAt(3))));
        buttons[row][3].setEnabled(false);

        if (controller.getEnterGuess().charAt(3) == ((text.charAt(3)))) {
            correctGuess(row, 3, (String.valueOf(text.charAt(3))));
            count++;

        }
        buttons[row][4].setText((String.valueOf(text.charAt(4))));
        buttons[row][4].setEnabled(false);

        if (controller.getEnterGuess().charAt(4) == ((text.charAt(4)))) {
            correctGuess(row, 4, (String.valueOf(text.charAt(4))));
            count++;

        }
        int i = 5;
        while (i > 0) {
            if (controller.getEnterGuess().charAt(i) == ((text.charAt(0)))) {
                misplacedGuess(row, 0, (String.valueOf(text.charAt(0))));
            }

            if (controller.getEnterGuess().charAt(i) == ((text.charAt(1)))) {
                misplacedGuess(row, 1, (String.valueOf(text.charAt(1))));
            }

            if (controller.getEnterGuess().charAt(i) == ((text.charAt(2)))) {
                misplacedGuess(row, 2, (String.valueOf(text.charAt(2))));
            }

            if (controller.getEnterGuess().charAt(i) == ((text.charAt(3)))) {
                misplacedGuess(row, 3, (String.valueOf(text.charAt(3))));
            }

            if (controller.getEnterGuess().charAt(i) == ((text.charAt(4)))) {
                misplacedGuess(row, 4, (String.valueOf(text.charAt(4))));
            }
            i++;
        }
        if (count >= 5){
            //Object q = JOptionPane.showMessageDialog((Component) null, "Congrats!", "Victory", 3);
        }
    }



    @Override
    public void handleStatusUpdate(Event e){
        System.out.println(controller.getEnterGuess());
        this.buttons[e.getX()][e.getY()].setText(e.getGuess());
    }

}
