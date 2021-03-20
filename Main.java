public class Main {
    public static void main (String[] args) {
        Grid grid = new Grid(20);
        GridFrame frame = new GridFrame(new GridPanel(grid));
    }
}