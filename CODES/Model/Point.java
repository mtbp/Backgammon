package Model;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Point {

    int checkersNumber = 0;
    Color color;
    int colomnNo;
    //int blackColomnNo;
    PointStatus status = PointStatus.FREE;
    int lastCheckerIndex;
    boolean mark = false;

    public Point(int colomnNo){
        this.colomnNo = colomnNo;
        switch(colomnNo){
            case 1:{
                this.color = Color.BLACK;
                this.checkersNumber = 2;
                this.status = PointStatus.BLACK;
                this.lastCheckerIndex = 1;
                break;
            }
            case 6:{
                this.color = Color.WHITE;
                this.checkersNumber = 5;
                this.status = PointStatus.WHITE;
                this.lastCheckerIndex = 14;
                break;
            }
            case 8:{
                this.color = Color.WHITE;
                this.checkersNumber = 3;
                this.status = PointStatus.WHITE;
                this.lastCheckerIndex = 9;
                break;
            }
            case 12:{
                this.color = Color.BLACK;
                this.checkersNumber = 5;
                this.status = PointStatus.BLACK;
                this.lastCheckerIndex = 6;
                break;
            }
            case 13:{
                this.color = Color.WHITE;
                this.checkersNumber = 5;
                this.status = PointStatus.WHITE;
                this.lastCheckerIndex = 6;
                break;
            }
            case 17:{
                this.color = Color.BLACK;
                this.checkersNumber = 3;
                this.status = PointStatus.BLACK;
                this.lastCheckerIndex = 9;
                break;
            }
            case 19:{
                this.color = Color.BLACK;
                this.checkersNumber = 5;
                this.status = PointStatus.BLACK;
                this.lastCheckerIndex = 14;
                break;
            }
            case 24:{
                this.color = Color.WHITE;
                this.checkersNumber = 2;
                this.status = PointStatus.WHITE;
                this.lastCheckerIndex = 1;
                break;
            }
            default:{}
        }



        this.checkersNumber = checkersNumber;
        this.status = status;
        this.colomnNo = colomnNo;
        this.color = color;
        //this.whiteColomnNo = whiteColomnNo;
        //this.blackColomnNo = blackColomnNo;
    }

    public boolean checkMove(Color color){
        boolean move = FALSE;
        if(color == Color.WHITE) {
            if (this.status == PointStatus.WHITE | this.status == PointStatus.BLACKBLOT | this.status == PointStatus.FREE){
                move = TRUE;
            }
        }
        else if(color == Color.BLACK) {
            if (this.status == PointStatus.BLACK | this.status == PointStatus.WHITEBLOT | this.status == PointStatus.FREE){
                move = TRUE;
            }
        }
        return move;
    }







}
