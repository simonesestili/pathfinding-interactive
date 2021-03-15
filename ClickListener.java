import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class ClickListener extends MouseAdapter {

    private Grid grid;
    private GridPanel panel;
    private GridFrame frame;
    private Set<Position> currDrag = new HashSet<>();
    private boolean currSelection;

    private static final int X_OFFSET = 8;
    private static final int Y_OFFSET = 32;

    public ClickListener(GridPanel panel, GridFrame frame){
        this.grid = panel.getGrid();
        this.panel = panel;
        this.frame = frame;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int row = convertToRow(e.getY() - Y_OFFSET);
        int col = convertToCol(e.getX() - X_OFFSET);

        if(!grid.isOnGrid(row, col))
            return;

        currSelection = grid.getCell(row, col);
        currDrag.clear();
        currDrag.add(grid.getPosCell(row, col));
        grid.toggleCell(row, col);
        frame.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int row = convertToRow(e.getY() - Y_OFFSET);
        int col = convertToCol(e.getX() - X_OFFSET);

        if(!grid.isOnGrid(row, col) || grid.getCell(row, col) != currSelection)
            return;

        if(currDrag.add(grid.getPosCell(row, col))){
            grid.toggleCell(row, col);
            frame.repaint();
        }
    }

    private int convertToRow(int n){
        return n / (panel.getHeight() / grid.getRows());
    }

    private int convertToCol(int n){
        return n / (panel.getWidth() / grid.getCols());
    }
}
