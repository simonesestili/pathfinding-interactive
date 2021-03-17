import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GridFrame extends JFrame {

    private GridPanel panel;

    private static final int X_CURSOR_OFFSET = 5;
    private static final int Y_CURSOR_OFFSET = 22;

    Action reset;
    Action path;
    Action start;
    Action end;

    public GridFrame(GridPanel panel){
        this.setTitle("Pathfinder");
        this.panel = panel;
        this.panel.setFrame(this);
        this.setSize(panel.getSize());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        ClickListener cl = new ClickListener(panel, this);
        this.addMouseListener(cl);
        this.addMouseMotionListener(cl);

        reset = new ResetAction();
        path = new PathAction();
        start = new StartAction();
        end = new EndAction();

        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("R"), "reset");
        panel.getActionMap().put("reset", reset);
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("P"), "path");
        panel.getActionMap().put("path", path);
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "start");
        panel.getActionMap().put("start", start);
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("E"), "end");
        panel.getActionMap().put("end", end);

        this.add(panel);
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
    }

    class ResetAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.getGrid().reset();
            repaint();
        }
    }

    class PathAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.togglePath();
            repaint();
        }
    }

    class StartAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            Point point = getMousePosition();
            if(point == null)
                return;

            panel.getGrid().setStart(convertToRow(point.y - Y_CURSOR_OFFSET), convertToCol(point.x - X_CURSOR_OFFSET));
            repaint();
        }
    }

    class EndAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            Point point = getMousePosition();
            if(point == null)
                return;

            panel.getGrid().setEnd(convertToRow(point.y - Y_CURSOR_OFFSET), convertToCol(point.x - X_CURSOR_OFFSET));
            repaint();
        }
    }

    private int convertToRow(int n){
        return n / ( getHeight() / panel.getGrid().getRows() );
    }

    private int convertToCol(int n){
        return n / ( getWidth() / panel.getGrid().getCols() );
    }
}
