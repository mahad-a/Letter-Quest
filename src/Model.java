import java.util.ArrayList;

public class Model {
    ArrayList<String> guess;
    char first,second,third,fourth,fifth;
    String grid[][];
    View view;
    Controller controller;
    ArrayList<ModelView> views;

    public enum Status {CORRECT, MISPLACED, INCORRECT};
    private Status status;
    
    public Model() {
        /*String word
        char first = word.charAt(0);
        guess.add(first);
        char second = word.charAt(1);
        guess.add(second);
        char third = word.charAt(2);
        guess.add(third);
        char fourth = word.charAt(3);
        guess.add(fourth);
        char fifth = word.charAt(4);
        guess.add(fifth);*/

        grid = new String[5][5];
        this.views = new ArrayList<ModelView>();

        for(int i = 0; i < 5; i ++){
            for(int j = 0; j < 5; j++){
                grid[i][j] = "";
            }
        }
        this.status = Status.INCORRECT;
        this.guess = new ArrayList<>();
    }

    public ArrayList<String> getGuess() {
        return guess;
    }

    public char getFirst() {
        return first;
    }

    public char getSecond() {
        return second;
    }

    public char getThird() {
        return third;
    }

    public char getFourth() {
        return fourth;
    }

    public char getFifth() {
        return fifth;
    }

    public void checkWin(Controller controller, String word){
        guess.add(word.strip());
        String c = controller.getEnterGuess();
        for(int i = 0; i < 5; i ++){
            for(int j = 0; j < 5; j++){
                if(grid[i][j].equals("")){ // if it has no text
                    status = Status.INCORRECT;
                }
            }
        }

        for(int i = 0; i < 5; i ++){
            for(int j = 0; j < 5; j++){
                if (guess.contains(grid[i][j])){
                    if (guess.toString().equals(grid[i][j])){
                        view.correctGuess(i, j, c); // letter in correct spot
                        status = Status.CORRECT;
                        return;
                    } else {
                        view.misplacedGuess(i,j, c); // right letter, wrong spot
                        status = Status.MISPLACED;
                    }
                }
            }
        }
    }

    public int count(int count1){
        return count1++;
    }

    public void setValue(int x,int y,String guessedWord){
        grid[x][y] = guessedWord;
        for (ModelView view: views){
            view.handleStatusUpdate(new Event(this, guessedWord, x, y));
        }
    }
}
