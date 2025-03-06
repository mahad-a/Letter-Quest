import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private String enterGuess;
    private Model model;
    public Controller(Model model){
        this.model = model;

    }

    public String getEnterGuess() {
        return enterGuess;
    }

    public void askWord(){
        enterGuess = JOptionPane.showInputDialog(null, "Enter a 5 letter word.");
        enterGuess.toLowerCase();
        if (enterGuess.length() != 5){
            throw new IllegalArgumentException("Word must be 5 letters.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] position = e.getActionCommand().split(" ");
        String w = JOptionPane.showInputDialog(null, "Enter a letter.");
        model.setValue(Integer.parseInt(position[0]), Integer.parseInt(position[1]), w);
    }
}
