package BackGammonModel;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Model {
    Dice[] dice;
    Point[] point;
    public Checker[] whiteChecker;
    Checker[] blackChecker;
    Profile whiteProfile;
    Profile blackProfile;
    Game game;

    int[] pointCheckersNo = new int[24];
    Color[] pointCheckersColor = new Color[24];

    public Model(Dice[] dice, Point[] point, Checker[] whiteChecker, Checker[] blackChecker, Profile whiteProfile, Profile blackProfile, Game game) {
        dice = new Dice[2];
        this.dice[0] = dice[0];
        this.dice[1] = dice[1];

        point = new Point[24];
        for(int i = 0; i < 24; i++){
            this.point[i] = point[i];
        }

        whiteChecker = new Checker[15];
        blackChecker = new Checker[15];
        for(int i = 0; i < 15; i++){
            this.whiteChecker[i] = whiteChecker[i];
            this.blackChecker[i] = blackChecker[i];
        }

        this.whiteProfile = whiteProfile;
        this.blackProfile = blackProfile;
        this.game = game;
    }

    void diceToss(){
        dice[1].toss(); // for start : for white player
        dice[2].toss(); // for start : for black player
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

    void nextTurn(){
        game.changeTurn();
    }
}
