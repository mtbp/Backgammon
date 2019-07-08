package Model;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Model {

    Dice[] dice = new Dice[2];
    Point[] point = new Point[24];
    Checker[] whiteChecker = new Checker[15];
    Checker[] blackChecker = new Checker[15];
    Profile whiteProfile;// = new Profile(1,Color.WHITE);
    Profile blackProfile;
    public Game game;

    int[][] moveCheck = new int[2][24];

    // pass to view
    public int[] pointInfoToDraw = new int[24];

    public boolean[] canMove = new boolean[26];
    boolean[] canMoveMax = new boolean[26];
    boolean[] canMoveMin = new boolean[26];


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

    /*
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
    */

    public int[] rollDice(){
        //System.out.println("rolling dice...");

        int[] output = new int[2];
        dice[0].toss(); // first player
        dice[1].toss(); // second player
        output[0] = dice[0].value;
        output[1] = dice[1].value;
        dice[0].isPlayed = false;
        dice[1].isPlayed = false;
        //System.out.println("rolling dice is done");
        //System.out.println(dice[0].value );
        //output[0] = 6;
        //output[1] = 2;
        //dice[0].value = 6;
        //dice[1].value = 2;

        return output;
    }

    public void setProfile(int c){
        //System.out.println(dice[0].value+ " "+ dice[1].value);
        Color color;
        if(c == 0){
            color = Color.WHITE;
        }
        else{
            color = Color.BLACK;
        }
        int playerNo;
        if(dice[0].value > dice[1].value) {
            playerNo = 1;
        }
        else{
            playerNo = 2;
        }
        //game.turn = color;
        if(color == Color.WHITE){
            whiteProfile = new Profile(playerNo, Color.WHITE);
            blackProfile = new Profile(3 - playerNo, Color.BLACK);
        }
        else {
            whiteProfile = new Profile(3 - playerNo, Color.WHITE);
            blackProfile = new Profile(playerNo, Color.BLACK);
        }
        game = new Game(color);

        //System.out.println(whiteProfile.playerNo+" "+blackProfile.playerNo);
    }

    ////////////////////////////////////////game starts!!!!!!!!

    public boolean[] checkCheckers(){
        //System.out.println("hellooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        int diceNo;
        if(!dice[0].isPlayed && !dice[1].isPlayed)
            diceNo = 0;
        else
            diceNo = 1;
        // diceNo : 0 -> 2 dices should play - 1 -> 1 dice should play
        //game.turn = Color.WHITE;
        int maxIndex = 0, minIndex = 0;
        int index = 0;
        if(diceNo == 0) {
            if (dice[0].value > dice[1].value) {
                maxIndex = 0;
            } else {
                maxIndex = 1;
            }
            minIndex = 1 - maxIndex;
        }
        else{
            if(!dice[0].isPlayed)    index = 1;
        }
        //System.out.println(index);
         int dest, dest2;
        //boolean possible = false;
        //dice[0].value = 5;
        for(int i = 0; i < 26; i++){
            canMove[i] = false;
            canMoveMax[i] = false;
            canMoveMin[i] = false;
        }

        if(game.turn == Color.WHITE){
            if(diceNo == 1) { // 1 dice should move
                if (whiteProfile.checkersHit > 0) {
                    dest = 25 - dice[index].value;
                    if (point[dest-1].status != PointStatus.BLACK)
                        canMove[25] = true;
                }

                else if (whiteProfile.checkCanTakeOut(whiteChecker)) {
                    if (whiteProfile.lastCheckerIndex <= dice[index].value)
                        canMove[whiteProfile.lastCheckerIndex] = true;
                    else {
                        for (int i = 1; i <= whiteProfile.lastCheckerIndex; i++) {
                            dest = i - dice[index].value;
                            if ((dest > 0 && point[dest-1].status != PointStatus.BLACK) || dest == 0)
                                canMove[i] = true;
                        }
                    }
                }

                else {
                    for (int i = 1; i <= 24; i++) {
                        if (point[i-1].status == PointStatus.WHITE || point[i-1].status == PointStatus.WHITEBLOT) {
                            dest = i - dice[index].value;
                            if ((dest > 0 && point[dest-1].status != PointStatus.BLACK))
                                canMove[i] = true;
                        }
                    }
                }
            }

            else if(diceNo == 0) {

                if (whiteProfile.checkersHit > 0) {
                    dest = 25 - dice[index].value;
                    if (point[dest].status != PointStatus.BLACK)
                        canMove[25] = true;
                }

                else if (whiteProfile.checkCanTakeOut(whiteChecker)) {
                    if (whiteProfile.lastCheckerIndex <= dice[index].value)
                        canMove[whiteProfile.lastCheckerIndex] = true;
                    else {
                        for (int i = 1; i <= whiteProfile.lastCheckerIndex; i++) {
                            dest = i - dice[index].value;
                            if ((dest > 0 && point[dest].status != PointStatus.BLACK) || dest == 0)
                                canMove[i] = true;
                        }
                    }
                }

                else {
                    //System.out.println("hellooooo111111");
                    for (int i = 0; i < 24; i++) {
                        if (point[i].status == PointStatus.BLACK || point[i].status == PointStatus.BLACKBLOT) {
                            moveCheck[0][i] = 100 + point[i].checkersNumber;

                        } else if (point[i].status == PointStatus.FREE) {
                            moveCheck[0][i] = 0;
                        } else {
                            moveCheck[0][i] = point[i].checkersNumber;
                        }
                    }


                    // no need to these 2!
                    //moveCheck[0][0] = 0;
                    //moveCheck[0][25] = 0;

                    for (int j = 0; j < 24; j++) {
                        moveCheck[1][j] = moveCheck[0][j];
                    }

                    int count = 0;
                    //int firstSmallerDice = 0;
                    for (int i = 1; i < 25; i++) {
                        if (moveCheck[0][i-1] > 0 && moveCheck[0][i-1] < 100) {
                            for (int j = 0; j < 24; j++) {
                                moveCheck[1][j] = moveCheck[0][j];
                            }

                            dest = i - dice[maxIndex].value;
                            if (dest > 0){
                                if (moveCheck[0][dest - 1] <= 101) {
                                    canMoveMax[i] = true;
                                    moveCheck[1][i - 1]--;
                                    if (moveCheck[1][dest - 1] == 101) moveCheck[1][dest - 1] = 1;
                                    else                               moveCheck[1][dest - 1]++;
                                    for (int j = 1; j < 25; j++) {
                                        if (moveCheck[1][j - 1] > 0 && moveCheck[1][j - 1] < 100) {
                                            dest2 = j - dice[minIndex].value;
                                            if (dest2 > 0) {
                                                if (moveCheck[1][dest2 - 1] <= 101) {
                                                    canMove[i] = true;
                                                    count = 1;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (count == 0) {
                        for (int i = 1; i < 25; i++) {
                            if (moveCheck[0][i-1] > 0 && moveCheck[0][i-1] < 100) {
                                for (int j = 0; j < 24; j++) {
                                    moveCheck[1][j] = moveCheck[0][j];
                                }


                                dest = i - dice[minIndex].value;
                                if(dest>0) {
                                    if (moveCheck[0][dest - 1] <= 101) {
                                        canMoveMin[i] = true;
                                        moveCheck[1][i - 1]--;
                                        if (moveCheck[1][dest - 1] == 101) moveCheck[1][dest - 1] = 1;
                                        else                               moveCheck[1][dest - 1]++;
                                        for (int j = 1; j < 25; j++) {
                                            if (moveCheck[1][j - 1] > 0 && moveCheck[1][j - 1] < 100) {
                                                dest2 = j - dice[maxIndex].value;
                                                if (dest2 > 0) {
                                                    if (moveCheck[1][dest2 - 1] <= 101) {
                                                        canMove[i] = true;
                                                        count = 1;
                                                        game.smallerDiceFirst = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (count == 0) {
                        for (int i = 1; i < 25; i++) {
                            if (canMoveMax[i]) {
                                count = 1;
                                break;
                            }
                        }
                        if (count == 0) {
                            for (int i = 1; i < 25; i++) {
                                canMove[i] = canMoveMin[i];
                                game.smallerDiceFirst = true;
                            }
                        } else {
                            for (int i = 1; i < 25; i++) {
                                canMove[i] = canMoveMax[i];
                            }
                        }
                    }
                }
            }
        }

        else if(game.turn == Color.BLACK){
            if(diceNo == 1) { // 1 dice should move
                if (blackProfile.checkersHit > 0) {
                    dest = dice[index].value;
                    if (point[dest-1].status != PointStatus.WHITE)
                        canMove[0] = true;
                }

                else if (blackProfile.checkCanTakeOut(blackChecker)) {
                    if ((25 - blackProfile.lastCheckerIndex) <= dice[index].value)
                        canMove[blackProfile.lastCheckerIndex] = true;
                    else {
                        for (int i = 24; i >= blackProfile.lastCheckerIndex; i--) {
                            dest = i + dice[index].value;
                            if ((dest <25 && point[dest-1].status != PointStatus.WHITE) || dest == 25)
                                canMove[i] = true;
                        }
                    }
                }

                else {
                    for (int i = 1; i <= 24; i++) {
                        if (point[i-1].status == PointStatus.BLACK || point[i-1].status == PointStatus.BLACKBLOT) {
                            dest = i + dice[index].value;
                            if ((dest < 25 && point[dest-1].status != PointStatus.WHITE))
                                canMove[i] = true;
                        }
                    }
                }
            }

            else if(diceNo == 0) {

                if (blackProfile.checkersHit > 0) {
                    dest = dice[index].value;
                    if (point[dest-1].status != PointStatus.WHITE)
                        canMove[0] = true;
                }
                else if (blackProfile.checkCanTakeOut(blackChecker)) {
                    if ((25-blackProfile.lastCheckerIndex) <= dice[index].value)
                        canMove[blackProfile.lastCheckerIndex] = true;
                    else {
                        for (int i = 1; i <= blackProfile.lastCheckerIndex; i++) {
                            dest = i + dice[index].value;
                            if ((dest < 25 && point[dest-1].status != PointStatus.WHITE) || dest == 25)
                                canMove[i] = true;
                        }
                    }
                }
                else {
                    //System.out.println("hellooooo111111");
                    for (int i = 0; i < 24; i++) {
                        if (point[i].status == PointStatus.WHITE || point[i].status == PointStatus.WHITEBLOT) {
                            moveCheck[0][i] = 100 + point[i].checkersNumber;

                        } else if (point[i].status == PointStatus.FREE) {
                            moveCheck[0][i] = 0;
                        } else {
                            moveCheck[0][i] = point[i].checkersNumber;
                        }
                    }


                    // no need to these 2!
                    //moveCheck[0][0] = 0;
                    //moveCheck[0][25] = 0;


                    int count = 0;
                    for (int i = 1; i < 25; i++) {
                        if (moveCheck[0][i-1] > 0 && moveCheck[0][i-1] < 100) {
                            for (int j = 0; j < 24; j++) {
                                moveCheck[1][j] = moveCheck[0][j];
                            }

                            dest = i + dice[maxIndex].value;
                            if (dest < 25){
                                if (moveCheck[0][dest - 1] <= 101) {
                                    canMoveMax[i] = true;
                                    moveCheck[1][i - 1]--;
                                    if (moveCheck[1][dest - 1] == 101) moveCheck[1][dest - 1] = 1;
                                    else                               moveCheck[1][dest - 1]++;
                                    for (int j = 1; j < 25; j++) {
                                        if (moveCheck[1][j - 1] > 0 && moveCheck[1][j - 1] < 100) {
                                            dest2 = j + dice[minIndex].value;
                                            if (dest2 < 25) {
                                                if (moveCheck[1][dest2 - 1] <= 101) {
                                                    canMove[i] = true;
                                                    count = 1;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (count == 0) {
                        for (int i = 1; i < 25; i++) {
                            if (moveCheck[0][i-1] > 0 && moveCheck[0][i-1] < 100) {
                                for (int j = 0; j < 24; j++) {
                                    moveCheck[1][j] = moveCheck[0][j];
                                }

                                dest = i + dice[minIndex].value;
                                if(dest < 25) {
                                    if (moveCheck[0][dest - 1] <= 101) {
                                        canMoveMin[i] = true;
                                        moveCheck[1][i - 1]--;
                                        if (moveCheck[1][dest - 1] == 101) moveCheck[1][dest - 1] = 1;
                                        else                               moveCheck[1][dest - 1]++;
                                        for (int j = 1; j < 25; j++) {
                                            if (moveCheck[1][j - 1] > 0 && moveCheck[1][j - 1] < 100) {
                                                dest2 = j + dice[maxIndex].value;
                                                if (dest2 < 25) {
                                                    if (moveCheck[1][dest2 - 1] <= 101) {
                                                        canMove[i] = true;
                                                        count = 1;
                                                        game.smallerDiceFirst = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (count == 0) {
                        for (int i = 1; i < 25; i++) {
                            if (canMoveMax[i]) {
                                count = 1;
                                break;
                            }
                        }
                        if (count == 0) {
                            for (int i = 1; i < 25; i++) {
                                canMove[i] = canMoveMin[i];
                                game.smallerDiceFirst = true;
                            }
                        } else {
                            for (int i = 1; i < 25; i++) {
                                canMove[i] = canMoveMax[i];
                            }
                        }

                    }

                }
            }
        }

        System.out.print("canmove:");
        for(int i = 0; i < 26; i++){
            System.out.print(" " + i + " " + canMove[i]);
        }
        System.out.println(" ");

        return canMove;
    }

    boolean[] canPoint = new boolean[24];

    public boolean[] checkPoint(int pointNo){ // pointNo -> [1,24]
        for(int i = 0; i < 24; i++){
            canPoint[i] = false;
        }

        int minDice = 0;
        int maxDice = 1;

        if(dice[0].value > dice[1].value){
            minDice = 1;
            maxDice = 0;
        }

        int firstDice = maxDice;
        int secondDice = minDice;
        int diceIsPlayed = 0;
        int diceIndex = 0;

        if(!dice[0].isPlayed && !dice[1].isPlayed){
            if(game.smallerDiceFirst){
                firstDice = minDice;
                secondDice = maxDice;
            }
        }
        else {
            diceIsPlayed = 1;
            if(dice[0].isPlayed){
                diceIndex = 1;
            }
            else{
                diceIndex = 0;
            }

        }
        if(game.turn == Color.WHITE) {
            if(diceIsPlayed == 1) {
                if (pointNo - 1 - dice[diceIndex].value > 0) {
                    if (point[pointNo - 1 - dice[diceIndex].value].status != PointStatus.BLACK) {
                        canPoint[pointNo - 1 - dice[diceIndex].value] = true;
                    }
                }
            }
            else {
                int n = pointNo - 1 - dice[firstDice].value;
                if (n > 0) {
                    if (point[n].status != PointStatus.BLACK) {
                        canPoint[n] = true;
                        if (n - dice[secondDice].value > 0) {
                            if (point[n - dice[secondDice].value].status != PointStatus.BLACK) {
                                canPoint[n - dice[secondDice].value] = true;
                            }
                        }
                    }
                }
            }
        }
        else if(game.turn == Color.BLACK) {
            if(diceIsPlayed == 1) {
                if(pointNo - 1 + dice[diceIndex].value < 24) {
                    if (point[pointNo - 1 + dice[diceIndex].value].status != PointStatus.WHITE) {
                        canPoint[pointNo - 1 + dice[diceIndex].value] = true;
                    }
                }
            }
            else{
                int n = pointNo - 1 + dice[firstDice].value;
                if(n < 24) {
                    if (point[n].status != PointStatus.WHITE) {
                        canPoint[n] = true;
                        if(n + dice[secondDice].value < 24) {
                            if (point[n + dice[secondDice].value].status != PointStatus.WHITE) {
                                canPoint[n + dice[secondDice].value] = true;
                            }
                        }
                    }
                }
            }
        }
        return canPoint;
    }

    public boolean isMovePossible( int origin, int destination) { //[0,23]

        int diceValue;
        int diceIndex;
        int diceSum = 0;
        if (!dice[0].isPlayed && !dice[1].isPlayed){
            if (game.smallerDiceFirst) {
                diceValue = (dice[0].value > dice[1].value) ? dice[1].value : dice[0].value;
                diceIndex = (dice[0].value > dice[1].value) ? 1 : 0;
            } else {
                diceValue = (dice[0].value < dice[1].value) ? dice[1].value : dice[0].value;
                diceIndex = (dice[0].value < dice[1].value) ? 1 : 0;
            }
            diceSum = dice[0].value + dice[1].value;
        }
        else if(!dice[0].isPlayed){
            diceValue = dice[0].value;
            diceIndex = 0;
        }
        else{
            diceValue = dice[1].value;
            diceIndex = 1;
        }

        //System.out.println(diceValue+" "+diceIndex+" "+diceSum+" "+origin+" "+destination);

        boolean possible = false;
        if(game.turn == Color.WHITE){
            if(point[origin].status == PointStatus.WHITEBLOT | point[origin].status == PointStatus.WHITE){
                if(point[destination].status != PointStatus.BLACK){
                    if(point[origin].colomnNo - point[destination].colomnNo == diceValue){
                        possible = true;
                        dice[diceIndex].isPlayed = true;
                    }
                    else if (!dice[0].isPlayed && !dice[1].isPlayed) {
                        if (point[origin].colomnNo - point[destination].colomnNo == diceSum) {
                            possible = true;
                            dice[0].isPlayed = true;
                            dice[1].isPlayed = true;
                        }
                    }
                }
            }
            //System.out.println(possible);

            int indexChecker = point[origin].lastCheckerIndex;
            if(possible){
                /*if(point[destination].status == PointStatus.BLACKBLOT){ //hit is happening
                    blackProfile.checkersHit++;
                    blackChecker[point[destination].lastCheckerIndex].pointNo = 0;
                    point[destination].removeChecker();
                }*/
                System.out.println("destination: " + destination);
                System.out.println("indexChecker: " + indexChecker);
                whiteChecker[indexChecker].pointNo = destination + 1;
                System.out.println("wchecker: " + whiteChecker[indexChecker].pointNo);

                point[origin].removeChecker();
                point[origin].setPointStatus();
                //System.out.println("origin checkers: "+point[origin].checkersNumber);
                //System.out.println("origin status:" + point[origin].status);
                point[destination].addChecker(indexChecker, Color.WHITE);
                point[destination].setPointStatus();
                //System.out.println("destination checkers: "+point[destination].checkersNumber);
                //System.out.println("destination status:" + point[destination].status);

            }
        }
        if(game.turn == Color.BLACK){
            if(point[origin].status == PointStatus.BLACKBLOT | point[origin].status == PointStatus.BLACK){
                if(point[destination].status != PointStatus.WHITE){
                    if(point[destination].colomnNo - point[origin].colomnNo == diceValue){
                        possible = true;
                        dice[diceIndex].isPlayed = true;
                    }
                    else if (!dice[0].isPlayed && !dice[1].isPlayed) {
                        if (point[destination].colomnNo - point[origin].colomnNo == diceSum) {
                            possible = true;
                            dice[0].isPlayed = true;
                            dice[1].isPlayed = true;
                        }
                    }
                }
            }
            int indexChecker = point[origin].lastCheckerIndex;
            if(possible){
                if(point[destination].status == PointStatus.WHITEBLOT){ //hit is happening
                    whiteProfile.checkersHit++;
                    whiteChecker[point[destination].lastCheckerIndex].pointNo = 25;
                    point[destination].removeChecker();
                }
                blackChecker[indexChecker].pointNo = destination;
                point[origin].removeChecker();
                point[origin].setPointStatus();
                point[destination].addChecker(indexChecker, Color.BLACK);
                point[destination].setPointStatus();

            }
        }
        return possible;
    }

    public int dicePlayed(){
        int i = 0;
        if(dice[0].isPlayed && !dice[1].isPlayed)
            i = 1;
        else if(!dice[0].isPlayed && dice[1].isPlayed)
            i = 2;
        else if(dice[0].isPlayed && dice[1].isPlayed)
            i = 3;
        return i;
    }

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

    public void nextTurn(){
        game.smallerDiceFirst = false;
        for(int i = 0; i < 15; i++){
            /*if(game.turn == Color.WHITE){
                whiteChecker[i].pointNo = whiteChecker[i].newPointNo;
            }
            else{
                blackChecker[i].pointNo = blackChecker[i].newPointNo;
            }*/
        }
        game.changeTurn();

    }







}
