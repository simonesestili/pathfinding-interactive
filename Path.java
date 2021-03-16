import java.util.Set;

public class Path {

    private Set<Node> path;
    private Set<Node> explored;

    public Path(Set<Node> path, Set<Node> explored) {
        this.path = path;
        this.explored = explored;
    }

    public Set<Node> getPath() {
        return path;
    }

    public Set<Node> getExplored() {
        return explored;
    }
}
