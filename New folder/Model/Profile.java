package Model;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Profile {

    int playerNo;
    Color color;

    int checkersIn = 15;
    int checkersOut = 0;
    int checkersHit = 0;
    boolean hasHit = false;
    boolean canTakeOut = false;

    int lastCheckerIndex = 24;

    public Profile(int playerNo, /*int checkersHit, int checkersIn, int checkersOut,*/ Color color){
        this.playerNo = playerNo;
        //this.checkersIn = checkersIn;
        //this.checkersOut = checkersOut;
        //this.checkersHit = checkersHit;
        this.color = color;
    }

    public boolean checkCanTakeOut(Checker[] checkers){
        int count = 0;
        if(color == Color.WHITE) {
            for (int i = 0; i < 15; i++) {
                if (checkers[i].pointNo <= 6) {
                    count++;
                }
            }
        }
        else {
            for(int i = 0; i<15; i++){
                if(checkers[i].pointNo>=19){
                    count++;
                }
            }
        }
        if(count == 15){
            return true;
        }
        else{
            return false;
        }
    }

    int maxPointIndex(Checker[] checkers){
        int count;
        if(color == Color.WHITE){
            count = 0;
            for(int i = 0; i < 15; i++){
                if(checkers[i].pointNo > count){
                    count = checkers[i].pointNo;
                }
            }
        }
        else{
            count = 25;
            for(int i = 0; i < 15; i++){
                if(checkers[i].pointNo < count){
                    count = checkers[i].pointNo;
                }
            }
        }
        return count;
    }
}




