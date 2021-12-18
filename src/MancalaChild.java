public class MancalaChild {
    MancalaBoard childBoard;
    int position;

    public MancalaChild(MancalaBoard childBoard, int position) {
        this.childBoard = childBoard;
        this.position = position;
    }

    public MancalaBoard getChildBoard() {
        return childBoard;
    }

    public int getPosition() {
        return position;
    }
}
