import java.io.*;
import java.util.*;

public class MancalaPlay {
    static int PINF = 99999999;
    static int NINF = -99999999;

    public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {
        Scanner sc = new Scanner(new File("test.txt"));
        int[][] initialPits = new int[2][6];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                initialPits[i][j] = sc.nextInt();
            }
        }
        int choicePlayer = 0;
        int choiceH = 0;
        Scanner sc1 = new Scanner(System.in);
        System.out.println("1\t 2 Player\n2\t Single Player\n3\t Computer vs Computer");
        choicePlayer = sc1.nextInt();
        if (choicePlayer == 1) {
            System.out.println("WelCome to multiplayer mode");
            MancalaBoard mancalaBoard = new MancalaBoard(initialPits, 0, 0);
            mancalaBoard.setTurn("P1");
            System.out.println(mancalaBoard);
            System.out.println(mancalaBoard.getTurn());
            int move = sc1.nextInt();
            mancalaBoard.callMove(move);
            System.out.println(mancalaBoard);
            while (mancalaBoard.getWinner().equalsIgnoreCase("RN")) {
                System.out.println(mancalaBoard.getTurn());
                move = sc1.nextInt();
                mancalaBoard.callMove(move);
                System.out.println(mancalaBoard);
            }
            System.out.println("Winner is " + mancalaBoard.getWinner());
        } else if (choicePlayer == 2) {
            System.out.println("Play With Computer");
            MancalaBoard mancalaBoard = new MancalaBoard(initialPits, 0, 0);
            mancalaBoard.setTurn("P1");
            MancalaSolver solver = new MancalaSolver(mancalaBoard);
            System.out.println(mancalaBoard);
            System.out.println(mancalaBoard.getTurn());
            int move = sc1.nextInt();
            mancalaBoard.callMove(move);
            System.out.println(mancalaBoard);
            System.out.println(mancalaBoard.getTurn());

            while (mancalaBoard.getWinner().equalsIgnoreCase("RN")) {
                if (mancalaBoard.getTurn().equalsIgnoreCase("Player 1's Move")) {
                    System.out.println(mancalaBoard.getTurn());
                    move = sc1.nextInt();
                    mancalaBoard.callMove(move);
                } else if (mancalaBoard.getTurn().equalsIgnoreCase("Player 2's Move")) {
                    //mancalaBoard.setCompState(true);
                    System.out.println("comp-> " + mancalaBoard.getTurn());
                    solver.callMinMax(mancalaBoard, 5, NINF, PINF, true, 3);
                    move = solver.getCurrentMove();
                    mancalaBoard.callMove(move + 1);
                    System.out.println("Computer");
                    while (mancalaBoard.isP2bonus()) {
                        System.out.println(mancalaBoard);
                        solver.callMinMax(mancalaBoard, 5, NINF, PINF, true, 3);
                        move = solver.getCurrentMove();
                        mancalaBoard.callMove(move + 1);
                        System.out.println("Computer Bonus");
                    }
                    //mancalaBoard.setCompState(false);
                }
                if (!mancalaBoard.isP2bonus()) System.out.println(mancalaBoard);
            }
            System.out.println("Winner is " + mancalaBoard.getWinner());
        } else if (choicePlayer == 3) {
            System.out.println("Computer vs Computer");
            MancalaBoard mancalaBoard = new MancalaBoard(initialPits, 0, 0);
            mancalaBoard.setTurn("P1");
            MancalaSolver solver = new MancalaSolver(mancalaBoard);
            System.out.println(mancalaBoard);
            System.out.println(mancalaBoard.getTurn());
            int move = 0;
            while (mancalaBoard.getWinner().equalsIgnoreCase("RN")) {
                if (mancalaBoard.getTurn().equalsIgnoreCase("Player 1's Move")) {
                    //mancalaBoard.setCompState(true);
                    System.out.println("comp1-> " + mancalaBoard.getTurn());
                    solver.callMinMax(mancalaBoard, 5, NINF, PINF, true, 1);
                    move = solver.getCurrentMove();
                    mancalaBoard.callMove(move + 1);
                    while (mancalaBoard.isP1bonus()) {
                        System.out.println("Computer1 Bonus");
                        System.out.println(mancalaBoard);
                        solver.callMinMax(mancalaBoard, 5, NINF, PINF, true, 1);
                        move = solver.getCurrentMove();
                        mancalaBoard.callMove(move + 1);
                    }
                    if (!mancalaBoard.isP1bonus()) {
                        System.out.println(mancalaBoard);
                    }
                    //mancalaBoard.setCompState(false);
                } else if (mancalaBoard.getTurn().equalsIgnoreCase("Player 2's Move")) {
                    //mancalaBoard.setCompState(true);
                    System.out.println("comp2-> " + mancalaBoard.getTurn());
                    solver.callMinMax(mancalaBoard, 5, NINF, PINF, true, 2);
                    move = solver.getCurrentMove();
                    mancalaBoard.callMove(move + 1);
                    while (mancalaBoard.isP2bonus()) {
                        System.out.println("Computer2 Bonus before move");
                        System.out.println(mancalaBoard);
                        solver.callMinMax(mancalaBoard, 5, NINF, PINF, true, 2);
                        move = solver.getCurrentMove();
                        mancalaBoard.callMove(move + 1);
                        System.out.println("Computer2 Bonus after move");
                        System.out.println(mancalaBoard);
                    }
                    if (!mancalaBoard.isP2bonus()) {
                        System.out.println(mancalaBoard);
                    }
                    //mancalaBoard.setCompState(false);
                }
                System.out.println(mancalaBoard);
            }
            System.out.println("Winner is " + mancalaBoard.getWinner());
        } else {
            MancalaBoard mancalaBoard = new MancalaBoard(initialPits, 0, 0);
            mancalaBoard.setTurn("P1");
            MancalaSolver solver = new MancalaSolver(mancalaBoard);
            System.out.println(mancalaBoard);
            System.out.println(mancalaBoard.getTurn());
            int move = 0;
            solver.callMinMax(mancalaBoard, 2, NINF, PINF, true, 1);
            move = solver.getCurrentMove();
            mancalaBoard.callMove(move + 1);
            System.out.println(mancalaBoard);
        }
//        =======================================================
//                                Test
//        MancalaBoard mancalaBoard = new MancalaBoard(initialPits, 0, 0);
//        mancalaBoard.setTurn("P1");
//        MancalaSolver solver = new MancalaSolver(mancalaBoard);
//        System.out.println(mancalaBoard);
//        System.out.println(mancalaBoard.getTurn());
//        int move = 0;
//        solver.callMinMax(mancalaBoard, 5, NINF, PINF, true);
//        move = solver.getCurrentMove();
//        mancalaBoard.callMove(move + 1);
//        System.out.println(mancalaBoard);
//        =======================================================
//        solver.callMinMax(mancalaBoard, 5, NINF, PINF, false);
//        move = solver.getCurrentMove();
//        mancalaBoard.callMove(move + 1);
//        System.out.println(mancalaBoard);
//        solver.callMinMax(mancalaBoard, 5, NINF, PINF, false);
//        move = solver.getCurrentMove();
//        mancalaBoard.callMove(move + 1);
//        System.out.println(mancalaBoard);

//        =======================================================
//                                C2C


//        =======================================================
//                                P2C


//        =======================================================
//                              P2P

//        =================================================================
    }
}
