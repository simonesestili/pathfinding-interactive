import javax.swing.*;

public class GridFrame extends JFrame {

    private GridPanel panel;

    public GridFrame(GridPanel panel){
        this.setTitle("Pathfinder");
        this.panel = panel;
        this.panel.setFrame(this);
        this.setSize(panel.getSize());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(panel);
        ClickListener cl = new ClickListener(panel, this);
        this.addMouseListener(cl);
        this.addMouseMotionListener(cl);
        this.setVisible(true);
        this.setResizable(false);
        this.pack();
    }
}
