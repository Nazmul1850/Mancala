import java.util.*;

public class MancalaBoard {
    private int[][] pits;
    private int P_1Storage = 0;
    private int P_2Storage = 0;
    private String turn;
    private boolean P_1bonus;
    private boolean P_2bonus;
    private String winner;
    private boolean compState;

    MancalaBoard(int[][] pits, int p1Store, int p2store) {
        this.P_1bonus = false;
        this.P_2bonus = false;
        this.compState = false;
        this.winner = "RN";
        this.pits = new int[2][6];
        this.P_1Storage = p1Store;
        this.P_2Storage = p2store;
        if (pits == null) throw new IllegalArgumentException();
        else {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 6; j++) {
                    this.pits[i][j] = pits[i][j];
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("\n\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        str.append("+               +");
        if (this.pits[0][0] < 10) str.append("   " + this.pits[0][0] + "   +");
        else str.append("   " + this.pits[0][0] + "  +");
        if (this.pits[0][1] < 10) str.append("   " + this.pits[0][1] + "   +");
        else str.append("   " + this.pits[0][1] + "  +");
        if (this.pits[0][2] < 10) str.append("   " + this.pits[0][2] + "   +");
        else str.append("   " + this.pits[0][2] + "  +");
        if (this.pits[0][3] < 10) str.append("   " + this.pits[0][3] + "   +");
        else str.append("   " + this.pits[0][3] + "  +");
        if (this.pits[0][4] < 10) str.append("   " + this.pits[0][4] + "   +");
        else str.append("   " + this.pits[0][4] + "  +");
        if (this.pits[0][5] < 10) str.append("   " + this.pits[0][5] + "   +               +\n");
        else str.append("   " + this.pits[0][5] + "  +               +\n");
        str.append("+");
        if (this.P_1Storage < 10) str.append("       " + P_1Storage + "       +");
        else str.append("       " + P_1Storage + "     +");
        str.append("++++++++++++++++++++++++++++++++++++++++++++++++\t\t");
        if (P_2Storage < 10) str.append("" + P_2Storage + "       +\n");
        else str.append("" + P_2Storage + "      +\n");
        str.append("+               +");
        if (this.pits[1][0] < 10) str.append("   " + this.pits[1][0] + "   +");
        else str.append("   " + this.pits[1][0] + "  +");
        if (this.pits[1][1] < 10) str.append("   " + this.pits[1][1] + "   +");
        else str.append("   " + this.pits[1][1] + "  +");
        if (this.pits[1][2] < 10) str.append("   " + this.pits[1][2] + "   +");
        else str.append("   " + this.pits[1][2] + "  +");
        if (this.pits[1][3] < 10) str.append("   " + this.pits[1][3] + "   +");
        else str.append("   " + this.pits[1][3] + "  +");
        if (this.pits[1][4] < 10) str.append("   " + this.pits[1][4] + "   +");
        else str.append("   " + this.pits[1][4] + "  +");
        if (this.pits[1][5] < 10) str.append("   " + this.pits[1][5] + "   +");
        else str.append("   " + this.pits[1][5] + "  +");
        str.append("               +\n");
        str.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.println(str);
        return "";
    }

    public boolean isCompState() {
        return compState;
    }

    public void setCompState(boolean compState) {
        this.compState = compState;
    }

    void setTurn(String t) {
        this.turn = t;
    }


    String getTurn() {
        if (this.turn.equalsIgnoreCase("P1")) {
            return "Player 1's Move";
        } else if (this.turn.equalsIgnoreCase("P2")) {
            return "Player 2's Move";
        } else {
            return "No one's Move";
        }
    }

    boolean isP1bonus() {
        return P_1bonus;
    }

    boolean isP2bonus() {
        return P_2bonus;
    }


    ArrayList<MancalaChild> getChilds() {
        ArrayList<MancalaChild> itr = new ArrayList<>();
        int[][] childPit = new int[2][6];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                childPit[i][j] = this.pits[i][j];
            }
        }
        if (this.turn.equalsIgnoreCase("P1")) {
            MancalaBoard child = new MancalaBoard(childPit, this.P_1Storage, this.P_2Storage);
            child.setTurn("P1");
            for (int i = 0; i < 6; i++) {
                child.setCompState(true);
                int val = child.createMove(i);
                //System.out.println("Checking move");
                //System.out.println(val);
                //System.out.println(child);
                if (val != -1) {
                    if (child.isP1bonus()) child.setTurn("P1");
                    else child.setTurn("P2");
                    itr.add(new MancalaChild(child, i));
                    child = new MancalaBoard(childPit, this.P_1Storage, this.P_2Storage);
                    child.setTurn("P1");
                }
//                System.out.println("Child " + i);
//                System.out.println(child);
//                System.out.println(child.heurestic1());
            }
        } else if (this.turn.equalsIgnoreCase("P2")) {
            MancalaBoard child = new MancalaBoard(childPit, this.P_1Storage, this.P_2Storage);
            child.setTurn("P2");
            for (int i = 0; i < 6; i++) {
                child.setCompState(true);
                int val = child.createMove(i);
                //System.out.println("Checking move");
                //System.out.println(val);
                //System.out.println(child);
                if (val != -1) {
                    if (child.isP2bonus()) child.setTurn("P2");
                    else child.setTurn("P1");
                    child.setCompState(true);
                    itr.add(new MancalaChild(child, i));
                    child = new MancalaBoard(childPit, this.P_1Storage, this.P_2Storage);
                    child.setTurn("P2");
                }
            }
        }
//        for (MancalaChild x : itr) {
//            System.out.println("Childs--> ");
//            System.out.println(this.turn);
//            System.out.println(x.getPosition());
//            System.out.println(x.getChildBoard());
//        }
        return itr;
    }

    void callMove(int position) {
        P_1bonus = false;
        P_2bonus = false;
        int val = createMove(position - 1);
        if (val == 0) {
            if (this.turn.equalsIgnoreCase("P1")) {
                if (!P_1bonus) setTurn("P2");
            } else if (this.turn.equalsIgnoreCase("P2")) {
                if (!P_2bonus) setTurn("P1");
            }
        } else {
            System.out.println("Wrong Move");
        }
        checkGoal();
    }


    boolean checkGoal() {
        boolean finished = false;
        int sumP1 = 0;
        int sumP2 = 0;
        for (int i = 0; i < 6; i++) {
            sumP1 += this.pits[0][i];
            sumP2 += this.pits[1][i];
        }
        //System.out.println("P1 sum" + sumP1);
        if (sumP1 == 0 || sumP2 == 0) finished = true;
        //System.out.println("P2 sum" + sumP2);
        if (finished) {
            if (sumP1 == 0) P_2Storage += sumP2;
            if (sumP2 == 0) P_1Storage += sumP1;
            if (P_1Storage > P_2Storage) winner = "P1";
            else if (P_1Storage < P_2Storage) winner = "P2";
            else winner = "D";
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 6; j++) {
                    this.pits[i][j] = 0;
                }
            }
        }
        return finished;
    }

    String getWinner() {
        return this.winner;
    }

    MancalaBoard getBoard() {
        return this;
    }

    int[][] getPits() {
        int[][] copyPits = new int[2][6];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                copyPits[i][j] = this.pits[i][j];
            }
        }
        return copyPits;
    }

    public int getP_1Storage() {
        return P_1Storage;
    }

    public int getP_2Storage() {
        return P_2Storage;
    }

    int createMove(int position) {
        int stone;
        int rValue = 0;
        this.P_1bonus = false;
        this.P_2bonus = false;
        if (this.turn.equalsIgnoreCase("P1")) {
            stone = this.pits[0][position];
            if (stone == 0) return -1;
            this.pits[0][position] = 0;
            //System.out.println(stone + "<-SP->" + (position + 1));
            position--;
            int tempStone = stone;
            if (position + 2 == stone) {
                if (!compState) System.out.println("You Got a Bonus Move");
                this.P_1bonus = true;
            }
//            if (stone >= position + 2) {
//                System.out.println("Storage Touch");
//                P_1Storage++;
//                tempStone--;
//            }
            int positionP2 = 0;
            int lastpos = -1;
            while (tempStone > 0) {
                //System.out.println("t-> " + tempStone + "p1-> " + position + "p2-> " + positionP2);
                //System.out.println(position);
                if (position >= 0) {
                    this.pits[0][position]++;
                    position--;
                } else {
                    if (position == -1) {
                        P_1Storage++;
                        tempStone--;
                        position = -2;
                        continue;
                    }
                    if (positionP2 <= 5) {
                        this.pits[1][positionP2]++;
                        positionP2++;
                    } else {
                        position = 5;
                        if (tempStone == 1) {
                            this.pits[0][position]++;
                            position--;
                        }
                    }

                }
                if (tempStone == 1) {
                    if (position != -1 && position != -2) lastpos = position + 1;
                }
                tempStone--;
            }
            //System.out.println("lastPs" + lastpos);
            if (lastpos != -1 && this.pits[0][lastpos] == 1 && this.pits[1][lastpos] != 0) {
                P_1Storage += this.pits[1][lastpos] + 1;
                this.pits[1][lastpos] = 0;
                this.pits[0][lastpos] = 0;
            }

        } else if (this.turn.equalsIgnoreCase("P2")) {
            stone = this.pits[1][position];
            if (stone == 0) return -1;
            this.pits[1][position] = 0;
            //System.out.println(stone + "<-SP->" + (position + 1));
            if (position + stone == 6) {
                if (!compState) {
                    System.out.println("You Got a Bonus Move");
                }
                this.P_2bonus = true;
            }
            position++;
            int tempStone = stone;
//            if (stone >= position + 2) {
//                System.out.println("Storage Touch");
//                P_1Storage++;
//                tempStone--;
//            }
            int positionP1 = 5;
            int lastpos = -1;
            while (tempStone > 0) {
                //System.out.println("t-> " + tempStone + "p1-> " + position + "p2-> " + positionP2);
                //System.out.println(position);
                if (position <= 5) {
                    this.pits[1][position]++;
                    position++;
                } else {
                    if (position == 6) {
                        P_2Storage++;
                        tempStone--;
                        position = 7;
                        continue;
                    }
                    if (positionP1 >= 0) {
                        this.pits[0][positionP1]++;
                        positionP1--;
                    } else {
                        position = 0;
                        if (tempStone == 1) {
                            this.pits[1][position]++;
                            position++;
                        }
                    }

                }
                if (tempStone == 1) {
                    if (position != 7 && position != 8) {
                        lastpos = position - 1;
                    }
                }
                tempStone--;
            }
            //System.out.println("lastPs" + lastpos);
            if (lastpos != -1 && this.pits[1][lastpos] == 1 && this.pits[0][lastpos] != 0) {
                P_2Storage += this.pits[0][lastpos] + 1;
                this.pits[1][lastpos] = 0;
                this.pits[0][lastpos] = 0;
            }
        } else {
            System.out.println("Comp");
        }
        return rValue;
    }

    int heurestic1() {
        int sumP1 = 0;
        int sumP2 = 0;
        for (int i = 0; i < 6; i++) {
            sumP1 += this.pits[0][i];
        }
        for (int i = 0; i < 6; i++) {
            sumP2 += this.pits[1][i];
        }
        if (this.turn.equalsIgnoreCase("P1")) {
            return (sumP1 - sumP2);
        } else {
            return (sumP2 - sumP1);
        }
//        return sumP1 - sumP2;
    }
}
