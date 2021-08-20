import java.io.*;
import java.util.*;


/**
 * Connect 4 logic works
 * Try to redo with graphical interface
 * check CS lecture for that
 *
 * Author: Pranav Tiwari
 * Time: Wed. Jun 16, 2021 4:37 PM CST
 *
 * Description: Program for playing connect 4 with 2 players. Please follow the prompts.
 * 1 refers to a piece from player 1
 * 2 refers to a piece from player 2
 */

public class ConnectFour {
    public static void main(String args[]){
        int[][] board = new int[6][7];

        int red=1;
        int yellow=2;

        Scanner input = new Scanner(System.in);

        boolean play=true;
        display(board);

        while(play){
            System.out.println("Player 1, pick the row you want to drop your piece in");
            int placeR = input.nextInt();


            for(int x=0; x<board[0].length; x++){
                if(board[x][placeR]==0) {
                    board[x][placeR] = 1;
                    break;
                }
            }
            play=showBoard(board);
            if(play==false)
                break;

            System.out.println("Player 2, pick the row you want to drop your piece in");
            int placeY = input.nextInt();

            for(int x=0; x<board[0].length; x++){
                if(board[x][placeY]==0) {
                    board[x][placeY] = 2;
                    break;
                }
            }

            play=showBoard(board);
        }
    }

    public static boolean showBoard(int [][]board){
        int winner=done(board);

        display(board);

        if(winner==1) {
            System.out.print("Player 1 has won! Player 2 is vanquished.");
            return false;
        }
        else if(winner==2){
            System.out.print("Player 2 has won! Player 1 is vanquished.");
            return false;
        }

        return true;
    }


    public static void display(int[][] board){  //prints out graphical display of board
        System.out.print("\n\n\n");
        for(int x=board.length-1; x>=0; x--){
            System.out.print("|");
            for(int y=0; y<board[0].length; y++){
                System.out.print(board[x][y]+" ");
            }
            System.out.print("|\n");
        }

        System.out.println("----------------");
        System.out.print(" 0 1 2 3 4 5 6 \n");
    }

    public static int done(int[][]board){
        for(int x=0; x<board.length; x++){
            for(int y=0; y<board[0].length; y++){
                if(board[x][y]==1||board[x][y]==2){
                    if(check(board, x, y))
                        return board[x][y];
                }
            }
        }
        return 0;
    }

    private static boolean check(int[][]board, int xCoord, int yCoord){
        int checker = board[xCoord][yCoord];

        int inRow = 0;
        int inColumn =0;
        int inDiaLtR =0;
        int inDiaRtL =0;

        for(int x=0; x<board[1].length; x++) {  //search row
            if(board[xCoord][x]==checker)
                inRow++;
            else
                inRow=0;
            if(inRow==4)
                return true;
        }

        for(int x=0; x<board.length; x++) {     //search column
            if(board[x][yCoord]==checker)
                inColumn++;
            else
                inColumn=0;
            if(inColumn==4)
                return true;
        }

        for(int x=0; x<board.length; x++) {     //search diagonal Left to Right
            int tempC = xCoord+x;
            int tempCY = yCoord+x;

            if(tempC<board.length && tempCY<board[0].length) {
                if (board[tempC][tempCY] == checker)
                    inDiaLtR++;
                else
                    inDiaLtR=0;
            }

            if(inDiaLtR==4)
                return true;
        }

        //does not work
        for(int x=0; x<board.length; x++) {     //search diagonal Right to Left
            int tempC = xCoord-x;
            int tempCY = yCoord+x;

            if(tempC>=0 && tempCY>=0) {
                if (board[tempC][tempCY] == checker)
                    inDiaRtL++;
                else
                    inDiaRtL=0;
            }

            if(inDiaRtL==4)
                return true;
        }

        return false;
    }
}
