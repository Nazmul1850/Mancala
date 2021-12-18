import java.util.*;

public class MancalaSolver {
    private int PINF = 99999999;
    private int NINF = -99999999;
    private ArrayList<MancalaChild> maxSolution = new ArrayList<>();
    private ArrayList<MancalaChild> minSolution = new ArrayList<>();
    private MancalaBoard initial;
    private int currentMove = 0;

    public MancalaSolver(MancalaBoard initial) {
        this.initial = initial;
    }

    public ArrayList<MancalaChild> getMaxSolution() {
        return maxSolution;
    }

    public ArrayList<MancalaChild> getMinSolution() {
        return minSolution;
    }

    public int getCurrentMove() {
        return currentMove;
    }

    void callMinMax(MancalaBoard board, int depth, int alpha, int beta, boolean maxPlayer, int com1) {
        int val = minmax(board, depth, alpha, beta, maxPlayer, com1);
        System.out.println(val);
        System.out.println(currentMove);
    }

    private int minmax(MancalaBoard br, int depth, int alpha, int beta, boolean maxPlayer, int com) {
        int eval;
        MancalaBoard board = new MancalaBoard(br.getPits(), br.getP_1Storage(), br.getP_2Storage());
        if (br.getTurn().equalsIgnoreCase("Player 1's Move")) {
            board.setTurn("P1");
        } else if (br.getTurn().equalsIgnoreCase("Player 2's Move")) {
            board.setTurn("P2");
        }
        if (depth == 0 || board.checkGoal()) return board.heurestic1();
        if (maxPlayer) {
            int maxVal = NINF;
            if (com == 1) {
                board.setTurn("P1");
                com = 2;
            } else if (com == 3) board.setTurn("P1");
            ArrayList<MancalaChild> childs = board.getChilds();
            for (MancalaChild x : childs) {
//                MancalaBoard c = x.getChildBoard();
//                MancalaBoard c1 = new MancalaBoard(c.getPits(), c.getP_1Storage(), c.getP_2Storage());
//                c1.setTurn("P1");
                eval = minmax(x.getChildBoard(), depth - 1, alpha, beta, false, com);
                if (eval >= maxVal) {
                    currentMove = x.getPosition();
                }
                //System.out.println("Child Max");
                //System.out.println(x.getChildBoard());
                maxVal = Math.max(maxVal, eval);
                alpha = Math.max(alpha, eval);
                //System.out.println("Eval -> " + eval + " maxVal ->" + maxVal + " alpha-> " + alpha + "pos-> " + x.getPosition());
                if (beta <= alpha) break;
            }
            return maxVal;
        } else {
            int minVal = PINF;
            if (com == 2) {
                //board.setTurn("P2");
                com = 1;
            } else if (com == 3) board.setTurn("P2");
            ArrayList<MancalaChild> childs = board.getChilds();
            for (MancalaChild x : childs) {
//                MancalaBoard c = x.getChildBoard();
//                MancalaBoard c1 = new MancalaBoard(c.getPits(), c.getP_1Storage(), c.getP_2Storage());
//                c1.setTurn("P2");
                eval = minmax(x.getChildBoard(), depth - 1, alpha, beta, true, com);
                //System.out.println("Child Min");
                //System.out.println(x.getChildBoard());
                minVal = Math.min(minVal, eval);
                beta = Math.min(beta, eval);
                if (eval <= minVal) {
                    currentMove = x.getPosition();
//                    System.out.println("Update eval");
//                    System.out.println(currentMove);
//                    System.out.println(x.getChildBoard());
                }
                //System.out.println("Eval -> " + eval + " minVal ->" + minVal + " beta->" + beta + " pos-> " + x.getPosition());
                if (beta <= alpha) break;
            }
            return minVal;
        }
    }
}

//4 0 1 4 4 4
//        4 5 4 4 1 0
