import java.util.ArrayList;

public class Model {
    private ArrayList<String> guess;
    private char first,second,third,fourth,fifth;
    private String[][] grid;
    private ArrayList<ModelView> views;
    private Status status;
    private String answer;
    
    public Model() {
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

    public String getAnswer() {
        return answer;
    }

    public void addView(ModelView view) {
        views.add(view);
    }

    public void notifyViews(int x,int y,String guessedWord){
        grid[x][y] = guessedWord;
        for (ModelView view: views){
            view.handleStatusUpdate(new Event(this, guessedWord, x, y));
        }
    }
}
