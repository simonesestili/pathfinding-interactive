import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {

        private static final int MAX_SIZE = 800;
        private int width;
        private int height;
        private int side;
        private Dimension size;
        private Grid grid;
        private GridFrame frame;

        private Graphics g;
        private Graphics2D g2;

        private static final Color emptyCol = new Color(16, 24, 32);
        private static final Color obstacleCol = new Color(242, 170, 76);
        private static final Color pathCol = new Color(199,0,57);
        private static final Color exploredCol = new Color(0,107,61);
        private static final Color lineCol = Color.WHITE;

        private Image startImage;
        private Image endImage;

        private boolean isPathVisible;
        private boolean isShowing;

        public GridPanel(Grid grid){
            this.grid = grid;
            side = MAX_SIZE / Math.max(grid.getCols(), grid.getRows());
            height = side * grid.getRows();
            width = side * grid.getCols();
            size = new Dimension(width, height);

            this.setPreferredSize(size);
            this.setSize(size);
        }

        public void paint(Graphics g){
            this.g = g;
            g2 = (Graphics2D) g;

            update();
            drawLines();
        }

        public Dimension getSize(){
            return size;
        }

        public void setFrame(GridFrame frame){
            this.frame = frame;
        }

        public void update(){
            for(int r = 0; r < grid.getRows(); r++){
                for(int c = 0; c < grid.getCols(); c++){
                    g2.setColor(grid.getCell(r, c) ? obstacleCol : emptyCol);
                    g2.fillRect(c * side, r * side, side, side);
                }
            }
        }

        public Grid getGrid(){
            return grid;
        }

        public void drawLines(){
            g2.setColor(lineCol);

            for(int r = 1; r < grid.getRows(); r++)
                g2.drawLine(0, r * side, width, r * side);

            for(int c = 1; c < grid.getRows(); c++)
                g2.drawLine(c * side, 0, c * side, height);
        }
}
