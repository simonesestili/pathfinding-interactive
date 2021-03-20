import java.awt.*;

public class Node {

    private Position pos;
    private Position end;
    private Node parent;
    private boolean explored;
    private int heuristic;
    private int distFromStart;
    private int f;
    private boolean isFound;

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

    public Position getEnd() {
        return end;
    }

    public boolean isFound() {
        return isFound;
    }

    public void setFound() {
        isFound = true;
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
        if(Pathfinder.canMoveDiagonal())
            heuristic = (int) (Math.sqrt(Math.pow(Math.abs(pos.getRow() - end.getRow()), 2) +
                    Math.pow(Math.abs(pos.getCol() - end.getCol()), 2)) * 10);
        else
            heuristic = (Math.abs(pos.getRow() - end.getRow()) * 10) + (Math.abs(pos.getCol() - end.getCol()) * 10);

        updateF();
    }


    @Override
    public String toString() {
        return "Node{" +
                "parent=" + parent +
                '}';
    }
}
