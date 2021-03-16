import java.awt.*;

public class Node {

    private Position pos;
    private Position end;
    private Node parent;
    private boolean explored;
    private int heuristic;
    private int distFromStart;
    private int f;

    public Node(int row, int col, Position end){
        this.pos = new Position(row, col);
        this.end = end;
        setDistFromStart(Integer.MAX_VALUE / 2);
        calcHeuristic();
    }

    public void setDistFromStart(int dist){
        distFromStart = dist;
        updateF();
    }

    public void setExplored(){
        explored = true;
    }

    public boolean isExplored(){
        return explored;
    }

    public Position getPos() {
        return pos;
    }

    public void setParent(Node parent){
        this.parent= parent;
    }

    public Node getParent() {
        return parent;
    }

    public int getDistFromStart() {
        return distFromStart;
    }

    public int getF(){
        return f;
    }

    private void updateF(){
        f = heuristic + distFromStart;
    }

    private void calcHeuristic(){
        heuristic = (int) (Math.sqrt(Math.pow(Math.abs(pos.getRow() - end.getRow()), 2) +
                                    Math.pow(Math.abs(pos.getCol() - end.getCol()), 2)) * 10);
        updateF();
    }
}
