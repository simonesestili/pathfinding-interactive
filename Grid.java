public class Grid {

    private boolean[][] grid;
    private Position[][] posGrid;
    private Position start;
    private Position end;

    public Grid(int row, int col){
        grid = new boolean[row][col];
        posGrid = new Position[row][col];
        fillPosGrid();
        start = new Position(0, 0);
        end = new Position(row - 1, col - 1);
    }

    public void toggleCell(int row, int col){
        if(!isOnGrid(row, col) || isStartPos(row, col) || isEndPos(row, col))
            return;
        grid[row][col] = !grid[row][col];
    }

    public void reset(){
        grid = new boolean[grid.length][grid[0].length];
    }

    public void setStart(int row, int col){
        if(!isOnGrid(row, col) || isEndPos(row, col))
            return;
        start = new Position(row, col);
    }

    public void setEnd(int row, int col){
        if(!isOnGrid(row, col) || isStartPos(row, col))
            return;
        end = new Position(row, col);
    }

    public boolean getCell(int row, int col){
        return grid[row][col];
    }

    public Position getPosCell(int row, int col){
        return posGrid[row][col];
    }

    public int getRows(){
        return grid.length;
    }

    public int getCols(){
        return grid[0].length;
    }

    public boolean isOnGrid(int row, int col){
        return row >= 0 && col >= 0 && row < grid.length && col < grid[0].length;
    }

    public boolean isStartPos(int row, int col){
        return row == start.getRow() && col == start.getCol();
    }

    public boolean isEndPos(int row, int col){
        return row == end.getRow() && col == end.getCol();
    }

    private void fillPosGrid(){
        for(int r = 0; r < getRows(); r++)
            for(int c = 0; c < getCols(); c++)
                posGrid[r][c] = new Position(r, c);
    }
}
