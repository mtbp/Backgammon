package Model;

import java.util.ArrayList;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Point {

    int checkersNumber = 0;
    Color color;
    int colomnNo;
    //int blackColomnNo;
    PointStatus status = PointStatus.FREE;
    int lastCheckerIndex;
    ArrayList<Integer> checkerIndex = new ArrayList<>();
    boolean mark = false;

    public Point(int colomnNo){
        this.colomnNo = colomnNo;
        switch(colomnNo){
            case 1:{
                this.color = Color.BLACK;
                this.checkersNumber = 2;
                this.status = PointStatus.BLACK;
                this.lastCheckerIndex = 1;
                checkerIndex.add(0);
                checkerIndex.add(1);
                break;
            }
            case 6:{
                this.color = Color.WHITE;
                this.checkersNumber = 5;
                this.status = PointStatus.WHITE;
                this.lastCheckerIndex = 14;
                checkerIndex.add(10);
                checkerIndex.add(11);
                checkerIndex.add(12);
                checkerIndex.add(13);
                checkerIndex.add(14);
                break;
            }
            case 8:{
                this.color = Color.WHITE;
                this.checkersNumber = 3;
                this.status = PointStatus.WHITE;
                this.lastCheckerIndex = 9;
                checkerIndex.add(7);
                checkerIndex.add(8);
                checkerIndex.add(9);
                break;
            }
            case 12:{
                this.color = Color.BLACK;
                this.checkersNumber = 5;
                this.status = PointStatus.BLACK;
                this.lastCheckerIndex = 6;
                checkerIndex.add(2);
                checkerIndex.add(3);
                checkerIndex.add(4);
                checkerIndex.add(5);
                checkerIndex.add(6);
                break;
            }
            case 13:{
                this.color = Color.WHITE;
                this.checkersNumber = 5;
                this.status = PointStatus.WHITE;
                this.lastCheckerIndex = 6;
                checkerIndex.add(2);
                checkerIndex.add(3);
                checkerIndex.add(4);
                checkerIndex.add(5);
                checkerIndex.add(6);
                break;
            }
            case 17:{
                this.color = Color.BLACK;
                this.checkersNumber = 3;
                this.status = PointStatus.BLACK;
                this.lastCheckerIndex = 9;
                checkerIndex.add(7);
                checkerIndex.add(8);
                checkerIndex.add(9);
                break;
            }
            case 19:{
                this.color = Color.BLACK;
                this.checkersNumber = 5;
                this.status = PointStatus.BLACK;
                this.lastCheckerIndex = 14;
                checkerIndex.add(10);
                checkerIndex.add(11);
                checkerIndex.add(12);
                checkerIndex.add(13);
                checkerIndex.add(14);
                break;
            }
            case 24:{
                this.color = Color.WHITE;
                this.checkersNumber = 2;
                this.status = PointStatus.WHITE;
                this.lastCheckerIndex = 1;
                checkerIndex.add(0);
                checkerIndex.add(1);
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

    void setPointStatus(){
        if (this.checkersNumber == 0){
            this.status = PointStatus.FREE;
            System.out.println("im in free");

        }
        if(this.color == Color.WHITE) {
            if(this.checkersNumber == 1){
                System.out.println("im in");
                this.status = PointStatus.WHITEBLOT;
            }
            else{
                this.status = PointStatus.WHITE;
            }
        }
        if(this.color == Color.BLACK) {
            if(this.checkersNumber == 1){
                this.status = PointStatus.BLACKBLOT;
            }
            else{
                this.status = PointStatus.BLACK;
            }
        }
    }

    void addChecker(int index, Color color){
        if(this.color == color){
            this.lastCheckerIndex = index;
            this.checkerIndex.add(index);
        }
        else{
            this.color = color;
            //this.checkerIndex.remove(0);
            this.checkerIndex.add(index);
        }
        this.checkersNumber++;
        ///////checkernumber
    }

    void removeChecker(){
        this.checkersNumber--;
        //this.setPointStatus();
        this.checkerIndex.remove(checkerIndex.size()-1);
        if(this.checkersNumber > 0) this.lastCheckerIndex = checkerIndex.get(checkerIndex.size()-1);
    }







}
