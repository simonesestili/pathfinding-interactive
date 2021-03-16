import java.util.*;

public class Pathfinder {

    public static Path aStar(Grid grid){
        Node[][] dp = new Node[grid.getRows()][grid.getCols()];
        fillDp(dp, grid);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getF));
        pq.add(dp[grid.getStart().getRow()][grid.getStart().getCol()]);

        while(!pq.isEmpty() && !pathFound(dp, grid)){
            Node curr = pq.poll();
            exploreNeighbors(curr, dp, pq);
        }

        Set<Node> path = new HashSet<>();
        Set<Node> explored = new HashSet<>();
        Stack<Node> stack = new Stack<>();

        if(pathFound(dp, grid)){
            backtrack(dp[grid.getEnd().getRow()][grid.getEnd().getCol()], stack);
            while(!stack.isEmpty())
                path.add(stack.pop());
        }

        for(Node[] row : dp)
            for(Node node : row)
                if(node != null && node.isExplored() && !path.contains(node))
                    explored.add(node);

        return new Path(path, explored);
    }

    private static void backtrack(Node node, Stack<Node> stack) {
        if(node == null)
            return;

        stack.add(node);
        backtrack(node.getParent(), stack);
    }

    private static void exploreNeighbors(Node curr, Node[][] dp, PriorityQueue<Node> pq) {
        Node up = isValid(dp, curr.getPos().up())
                ? dp[curr.getPos().up().getRow()][curr.getPos().up().getCol()] : null;
        Node down = isValid(dp, curr.getPos().down())
                ? dp[curr.getPos().down().getRow()][curr.getPos().down().getCol()] : null;
        Node right = isValid(dp, curr.getPos().right())
                ? dp[curr.getPos().right().getRow()][curr.getPos().right().getCol()] : null;
        Node left = isValid(dp, curr.getPos().left())
                ? dp[curr.getPos().left().getRow()][curr.getPos().left().getCol()] : null;

        Node upRight = isValid(dp, curr.getPos().upRight())
                ? dp[curr.getPos().upRight().getRow()][curr.getPos().upRight().getCol()] : null;
        Node upLeft = isValid(dp, curr.getPos().upLeft())
                ? dp[curr.getPos().upLeft().getRow()][curr.getPos().upLeft().getCol()] : null;
        Node downRight = isValid(dp, curr.getPos().downRight())
                ? dp[curr.getPos().downRight().getRow()][curr.getPos().downRight().getCol()] : null;
        Node downLeft = isValid(dp, curr.getPos().downLeft())
                ? dp[curr.getPos().downLeft().getRow()][curr.getPos().downLeft().getCol()] : null;


        if(up != null && !up.isExplored() && curr.getDistFromStart() + 10 < up.getDistFromStart()){
            up.setParent(curr);
            up.setDistFromStart(curr.getDistFromStart() + 10);
            if(!up.isExplored())
                pq.add(up);
        }

        if(down != null && !down.isExplored() && curr.getDistFromStart() + 10 < down.getDistFromStart()){
            down.setParent(curr);
            down.setDistFromStart(curr.getDistFromStart() + 10);
            if(!down.isExplored())
                pq.add(down);
        }

        if(right != null && !right.isExplored() && curr.getDistFromStart() + 10 < right.getDistFromStart()){
            right.setParent(curr);
            right.setDistFromStart(curr.getDistFromStart() + 10);
            if(!right.isExplored())
                pq.add(right);
        }

        if(left != null && !left.isExplored() && curr.getDistFromStart() + 10 < left.getDistFromStart()){
            left.setParent(curr);
            left.setDistFromStart(curr.getDistFromStart() + 10);
            if(!left.isExplored())
                pq.add(left);
        }


        if(upRight != null && !upRight.isExplored() && curr.getDistFromStart() + 14 < upRight.getDistFromStart()){
            upRight.setParent(curr);
            upRight.setDistFromStart(curr.getDistFromStart() + 14);
            if(!upRight.isExplored())
                pq.add(upRight);
        }

        if(upLeft != null && !upLeft.isExplored() && curr.getDistFromStart() + 14 < upLeft.getDistFromStart()){
            upLeft.setParent(curr);
            upLeft.setDistFromStart(curr.getDistFromStart() + 14);
            if(!upLeft.isExplored())
                pq.add(upLeft);
        }

        if(downRight != null && !downRight.isExplored() && curr.getDistFromStart() + 14 < downRight.getDistFromStart()){
            downRight.setParent(curr);
            downRight.setDistFromStart(curr.getDistFromStart() + 14);
            if(!downRight.isExplored())
                pq.add(downRight);
        }

        if(downLeft != null && !downLeft.isExplored() && curr.getDistFromStart() + 14 < downLeft.getDistFromStart()){
            downLeft.setParent(curr);
            downLeft.setDistFromStart(curr.getDistFromStart() + 14);
            if(!downLeft.isExplored())
                pq.add(downLeft);
        }

        curr.setExplored();
    }

    private static boolean isValid(Node[][] dp, Position pos){
        return pos.getRow() >= 0 && pos.getCol() >= 0
                && pos.getRow() < dp.length && pos.getCol() < dp[0].length
                && dp[pos.getRow()][pos.getCol()] != null;
    }

    private static void fillDp(Node[][] dp, Grid grid){
        for(int r = 0; r < grid.getRows(); r++)
            for(int c = 0; c < grid.getCols(); c++)
                dp[r][c] = grid.getCell(r, c) ? null : new Node(r, c, grid.getEnd());
        dp[grid.getStart().getRow()][grid.getStart().getCol()].setDistFromStart(0);
    }

    private static boolean pathFound(Node[][] dp, Grid grid){
        return dp[grid.getEnd().getRow()][grid.getEnd().getCol()].getDistFromStart() < Integer.MAX_VALUE / 2;
    }
}
