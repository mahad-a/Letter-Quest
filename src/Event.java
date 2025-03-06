import java.util.EventObject;

public class Event extends EventObject {
    Status status;
    int x;
    int y;
    String guess;

    public Event(Model model, String guess, int x, int y){
        super(model);
        this.guess = guess;
        this.x = x;
        this.y = y;
    }

    public int getX(){ return x;}
    public int getY(){ return y;}

    public String getGuess(){return guess;}

}
