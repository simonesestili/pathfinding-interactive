import java.applet.Applet;

public class Main extends Applet {
    public static void main (String[] args) {
        Grid grid = new Grid(20, 10);
        GridFrame frame = new GridFrame(new GridPanel(grid));
    }
}