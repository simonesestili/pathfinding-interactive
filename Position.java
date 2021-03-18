public class Position {

    private int row;
    private int col;

    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }

    public Position up(){
        return new Position(row - 1, col);
    }

    public Position down(){
        return new Position(row + 1, col);
    }

    public Position right(){
        return new Position(row, col + 1);
    }

    public Position left(){
        return new Position(row, col - 1);
    }

    public Position upRight(){
        return new Position(row - 1, col + 1);
    }

    public Position upLeft(){
        return new Position(row - 1, col - 1);
    }

    public Position downRight(){
        return new Position(row + 1, col + 1);
    }

    public Position downLeft(){
        return new Position(row + 1, col - 1);
    }

    @Override
    public boolean equals(Object obj) {
        Position other = (Position) obj;
        return row == other.row && col == other.col;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}
