package Model;

public class Game {
    Color turn;
    //Color orientation; // WHITE :
    // timer
    int whiteOutNo = 0;
    int BlackOutNo = 0;
    int whiteWin = 0;
    int blackWin = 0;
    int whiteHit = 0;
    int blackHit = 0;
    int whiteDice = 0;
    int blackDice = 0;
    boolean smallerDiceFirst = false;

    public Game(Color turn){
        this.turn = turn;
    }

    int addDice(int diceValue){
        int value;
        if(turn == Color.WHITE){
            whiteDice += diceValue;
            value = whiteDice;
        }
        else{
            blackDice += diceValue;
            value = blackDice;
        }
        return value;
    }

    void changeTurn(){
        if (turn == Color.BLACK){
            turn = Color.WHITE;
        }
        else {
            turn = Color.BLACK;
        }
    }




}
