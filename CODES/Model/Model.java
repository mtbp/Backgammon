package Model;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Model {

    Dice[] dice = new Dice[2];
    Point[] point = new Point[24];
    Checker[] whiteChecker = new Checker[15];
    Checker[] blackChecker = new Checker[15];
    Profile whiteProfile;
    Profile blackProfile;
    Game game;

    // pass to view
    public int[] pointInfoToDraw = new int[24];

    public Model(){
        dice[0] = new Dice();
        dice[1] = new Dice();

        for(int i = 0; i < 24; i++){
            point[i] = new Point(i + 1);
        }

        for(int i = 0; i < 15; i++){
            whiteChecker[i] = new Checker(Color.WHITE, i);
            blackChecker[i] = new Checker(Color.BLACK, i);
        }
    }

    public boolean pressed(int x){
        System.out.println("hi " + x);
        if(point[x-1].color == Color.WHITE) {
            System.out.println("pressed white "+ x);
        }
        else if(point[x-1].color == Color.BLACK) {
            System.out.println("pressed black "+ x);
        }
        return true;
    }













    public int[] rollDiceForStart(){
        System.out.println("rolling dice...");
        int[] output = new int[3];
        dice[0].toss();
        dice[1].toss();
        output[0] = dice[0].value;
        output[1] = dice[1].value;
        if(dice[0].value == dice[1].value)
            output[2] = 3;
        else if(dice[0].value > dice[1].value)
            output[2] = 1;
        else
            output[2] = 2;
        System.out.println("rolling dice is done");
        return output;
    }

    void setTurn(int playerNo, Color color){
        game.turn = color;
        if(color == Color.WHITE){
            whiteProfile.playerNo = playerNo;
            blackProfile.playerNo = 3 - playerNo;
        }
        else {
            blackProfile.playerNo = playerNo;
            whiteProfile.playerNo = 3 - playerNo;
        }
    }

    void checkMoves(int diceValue){
        if(game.turn == Color.WHITE){
            if(whiteProfile.checkersHit > 0){
                for(int i=0; i<15; i++){
                    if(whiteChecker[i].pointNo == 25){
                        whiteChecker[i].canMove = point[25 - diceValue].checkMove(Color.WHITE);
                    }
                }
            }
            else if(whiteProfile.checkCanTakeOut(whiteChecker) == TRUE){
                int max = whiteProfile.maxPointIndex(whiteChecker);
                if(max <= diceValue){
                    for(int i = 0; i < 15; i++){
                        int n = whiteChecker[i].pointNo;
                        if(n == max && point[n].lastCheckerIndex == i){
                            whiteChecker[i].canMove = TRUE;
                        }
                    }
                }
                else {
                    for(int i = 0; i < 15; i++){
                        int n = whiteChecker[i].pointNo;
                        if(n >= max && point[n].lastCheckerIndex == i){
                            whiteChecker[i].canMove = TRUE;
                        }
                    }
                }

            }
            else{
                for(int i=0; i<15; i++){
                    if(point[whiteChecker[i].pointNo].lastCheckerIndex == i) {
                        int n = whiteChecker[i].pointNo - diceValue;
                        if (n > 0) {
                            whiteChecker[i].canMove = point[n].checkMove(Color.WHITE);
                        }
                    }
                }
            }
        }

        if(game.turn == Color.BLACK){
            if(blackProfile.checkersHit > 0){
                for(int i=0; i<15; i++){
                    if(blackChecker[i].pointNo == 0){
                        blackChecker[i].canMove = point[diceValue].checkMove(Color.BLACK);
                    }
                }
            }
            else if(blackProfile.checkCanTakeOut(blackChecker) == TRUE){
                int max = 25 - blackProfile.maxPointIndex(blackChecker);
                if(max <= diceValue){
                    for(int i = 0; i < 15; i++){
                        int n = blackChecker[i].pointNo;
                        if(n == max && point[n].lastCheckerIndex == i){
                            blackChecker[i].canMove = TRUE;
                        }
                    }
                }
                else {
                    for(int i = 0; i < 15; i++){
                        int n = blackChecker[i].pointNo;
                        if(n >= max && point[n].lastCheckerIndex == i){
                            blackChecker[i].canMove = TRUE;
                        }
                    }
                }

            }
            else{
                for(int i=0; i<15; i++){
                    if(point[blackChecker[i].pointNo].lastCheckerIndex == i) {
                        int n = blackChecker[i].pointNo + diceValue;
                        if (n < 25) {
                            blackChecker[i].canMove = point[n].checkMove(Color.BLACK);
                        }
                    }
                }
            }
        }



    }

    void checkPoint(int pointNo){
        if(game.turn == Color.WHITE) {
            int n = point[pointNo].lastCheckerIndex;

        }
    }

    boolean isMovePossible(Color color, int origin, int destination, int diceValue){
        boolean possible = FALSE;
        if(color == Color.WHITE){
            if(point[origin].status == PointStatus.WHITEBLOT | point[origin].status == PointStatus.WHITE){
                if(point[destination].status != PointStatus.BLACK){
                    if(point[origin].colomnNo - point[destination].colomnNo == diceValue){
                        possible = TRUE;
                    }
                }
            }
        }
        if(color == Color.BLACK){
            if(point[origin].status == PointStatus.BLACKBLOT | point[origin].status == PointStatus.BLACK){
                if(point[destination].status != PointStatus.WHITE){
                    if(point[destination].colomnNo - point[origin].colomnNo == diceValue){
                        possible = TRUE;
                    }
                }
            }
        }
        return possible;
    }
    /*
        void displayMove(){
            for (int i = 0; i < 24; i++){
                pointCheckersColor[i] = point[i].color;
                pointCheckersNo[i] = point[i].checkersNumber;
            }
        }

        int[] getPointCheckersNo(){
            return pointCheckersNo;
        }

        Color[] getPointCheckersColor(){
            return pointCheckersColor;
        }
        */
    public void displayView(){
        for(int i = 0; i < 24; i++) {
            if (point[i].status != PointStatus.FREE) {
                if (point[i].color == Color.WHITE) {
                    pointInfoToDraw[i] = point[i].checkersNumber;
                } else if (point[i].color == Color.BLACK) {
                    pointInfoToDraw[i] = point[i].checkersNumber + 100;
                }
            }
            else
                pointInfoToDraw[i] = 0;
        }

    }

    void nextTurn(){
        game.changeTurn();
    }







}
