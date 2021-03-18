import java.util.*;

public class Pathfinder {

    private static boolean canMoveDiagonal = true;

    public static Path aStar(Grid grid){
        Node[][] dp = new Node[grid.getRows()][grid.getCols()];
        fillDp(dp, grid);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node::getF));
        pq.add(dp[grid.getStart().getRow()][grid.getStart().getCol()]);

        while(!pq.isEmpty() && !pathFound(dp, grid)){
            Node curr = pq.poll();
            aStarExploreNeighbors(curr, dp, pq);
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

    private static void aStarExploreNeighbors(Node curr, Node[][] dp, PriorityQueue<Node> pq) {
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


        if(Pathfinder.canMoveDiagonal() && upRight != null && !upRight.isExplored() && curr.getDistFromStart() + 14 < upRight.getDistFromStart()){
            upRight.setParent(curr);
            upRight.setDistFromStart(curr.getDistFromStart() + 14);
            if(!upRight.isExplored())
                pq.add(upRight);
        }

        if(Pathfinder.canMoveDiagonal() && upLeft != null && !upLeft.isExplored() && curr.getDistFromStart() + 14 < upLeft.getDistFromStart()){
            upLeft.setParent(curr);
            upLeft.setDistFromStart(curr.getDistFromStart() + 14);
            if(!upLeft.isExplored())
                pq.add(upLeft);
        }

        if(Pathfinder.canMoveDiagonal() && downRight != null && !downRight.isExplored() && curr.getDistFromStart() + 14 < downRight.getDistFromStart()){
            downRight.setParent(curr);
            downRight.setDistFromStart(curr.getDistFromStart() + 14);
            if(!downRight.isExplored())
                pq.add(downRight);
        }

        if(Pathfinder.canMoveDiagonal() && downLeft != null && !downLeft.isExplored() && curr.getDistFromStart() + 14 < downLeft.getDistFromStart()){
            downLeft.setParent(curr);
            downLeft.setDistFromStart(curr.getDistFromStart() + 14);
            if(!downLeft.isExplored())
                pq.add(downLeft);
        }

        curr.setExplored();
    }


    public static Path bfs(Grid grid){
        Node[][] dp = new Node[grid.getRows()][grid.getCols()];
        fillDp(dp, grid);

        Queue<Node> queue = new ArrayDeque<>();
        dp[grid.getStart().getRow()][grid.getStart().getCol()].setExplored();
        queue.add(dp[grid.getStart().getRow()][grid.getStart().getCol()]);

        while(!queue.isEmpty()){
            Node curr = queue.poll();
            bfsExploreNeighbors(curr, dp, queue);
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

    private static void bfsExploreNeighbors(Node curr, Node[][] dp, Queue<Node> queue){
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
                queue.add(up);
            up.setExplored();
        }

        if(down != null && !down.isExplored() && curr.getDistFromStart() + 10 < down.getDistFromStart()){
            down.setParent(curr);
            down.setDistFromStart(curr.getDistFromStart() + 10);
            if(!down.isExplored())
                queue.add(down);
            down.setExplored();
        }

        if(right != null && !right.isExplored() && curr.getDistFromStart() + 10 < right.getDistFromStart()){
            right.setParent(curr);
            right.setDistFromStart(curr.getDistFromStart() + 10);
            if(!right.isExplored())
                queue.add(right);
            right.setExplored();
        }

        if(left != null && !left.isExplored() && curr.getDistFromStart() + 10 < left.getDistFromStart()){
            left.setParent(curr);
            left.setDistFromStart(curr.getDistFromStart() + 10);
            if(!left.isExplored())
                queue.add(left);
            left.setExplored();
        }


        if(Pathfinder.canMoveDiagonal() && upRight != null && !upRight.isExplored() && curr.getDistFromStart() + 14 < upRight.getDistFromStart()){
            upRight.setParent(curr);
            upRight.setDistFromStart(curr.getDistFromStart() + 14);
            if(!upRight.isExplored())
                queue.add(upRight);
            upRight.setExplored();
        }

        if(Pathfinder.canMoveDiagonal() && upLeft != null && !upLeft.isExplored() && curr.getDistFromStart() + 14 < upLeft.getDistFromStart()){
            upLeft.setParent(curr);
            upLeft.setDistFromStart(curr.getDistFromStart() + 14);
            if(!upLeft.isExplored())
                queue.add(upLeft);
            upLeft.setExplored();
        }

        if(Pathfinder.canMoveDiagonal() && downRight != null && !downRight.isExplored() && curr.getDistFromStart() + 14 < downRight.getDistFromStart()){
            downRight.setParent(curr);
            downRight.setDistFromStart(curr.getDistFromStart() + 14);
            if(!downRight.isExplored())
                queue.add(downRight);
            downRight.setExplored();
        }

        if(Pathfinder.canMoveDiagonal() && downLeft != null && !downLeft.isExplored() && curr.getDistFromStart() + 14 < downLeft.getDistFromStart()){
            downLeft.setParent(curr);
            downLeft.setDistFromStart(curr.getDistFromStart() + 14);
            if(!downLeft.isExplored())
                queue.add(downLeft);
            downLeft.setExplored();
        }
    }


    public static Path dfs(Grid grid){
        Node[][] dp = new Node[grid.getRows()][grid.getCols()];
        fillDp(dp, grid);

        Stack<Node> stack = new Stack<>();
        stack.push(dp[grid.getStart().getRow()][grid.getStart().getCol()]);

        while(!dp[grid.getEnd().getRow()][grid.getEnd().getCol()].isFound() && !stack.isEmpty()){
            Node curr = stack.pop();

            curr.setExplored();
            dfsExploreNeighbors(curr, dp, stack, grid);
        }

        Set<Node> path = new HashSet<>();
        Set<Node> explored = new HashSet<>();
        Stack<Node> pathStack = new Stack<>();

        if(dp[grid.getEnd().getRow()][grid.getEnd().getCol()].isFound()){
            backtrack(dp[grid.getEnd().getRow()][grid.getEnd().getCol()], pathStack);
            while(!pathStack.isEmpty())
                path.add(pathStack.pop());
        }

        for(Node[] row : dp)
            for(Node node : row)
                if(node != null && node.isExplored() && !path.contains(node))
                    explored.add(node);

        return new Path(path, explored);
    }

    private static void dfsExploreNeighbors(Node curr, Node[][] dp, Stack<Node> stack, Grid grid){
        if(curr.getPos().equals(curr.getEnd()) || dp[grid.getStart().getRow()][grid.getStart().getCol()].isFound()) {
            curr.setFound();
            return;
        }

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

        if(Pathfinder.canMoveDiagonal() && !dp[curr.getPos().getRow()][curr.getPos().getCol()].isFound() && upRight != null && !upRight.isExplored()){
            upRight.setParent(curr);
            if(!upRight.isExplored())
                stack.add(upRight);
            upRight.setExplored();
        }

        if(!dp[curr.getPos().getRow()][curr.getPos().getCol()].isFound() && up != null && !up.isExplored()){
            up.setParent(curr);
            if(!up.isExplored())
                stack.add(up);
            up.setExplored();
        }

        if(Pathfinder.canMoveDiagonal() && !dp[curr.getPos().getRow()][curr.getPos().getCol()].isFound() && upLeft != null && !upLeft.isExplored()){
            upLeft.setParent(curr);
            if(!upLeft.isExplored())
                stack.add(upLeft);
            upLeft.setExplored();
        }

        if(!dp[curr.getPos().getRow()][curr.getPos().getCol()].isFound() && left != null && !left.isExplored()){
            left.setParent(curr);
            if(!left.isExplored())
                stack.add(left);
            left.setExplored();
        }

        if(Pathfinder.canMoveDiagonal() && !dp[curr.getPos().getRow()][curr.getPos().getCol()].isFound() && downLeft != null && !downLeft.isExplored()){
            downLeft.setParent(curr);
            if(!downLeft.isExplored())
                stack.add(downLeft);
            downLeft.setExplored();
        }

        if(!dp[curr.getPos().getRow()][curr.getPos().getCol()].isFound() && down != null && !down.isExplored()){
            down.setParent(curr);
            if(!down.isExplored())
                stack.add(down);
            down.setExplored();
        }

        if(!dp[curr.getPos().getRow()][curr.getPos().getCol()].isFound() && right != null && !right.isExplored()){
            right.setParent(curr);
            if(!right.isExplored())
                stack.add(right);
            right.setExplored();
        }

        if(Pathfinder.canMoveDiagonal() && !dp[curr.getPos().getRow()][curr.getPos().getCol()].isFound() && downRight != null && !downRight.isExplored()){
            downRight.setParent(curr);
            if(!downRight.isExplored())
                stack.add(downRight);
            downRight.setExplored();
        }
    }

    private static void fillDp(Node[][] dp, Grid grid){
        for(int r = 0; r < grid.getRows(); r++)
            for(int c = 0; c < grid.getCols(); c++)
                dp[r][c] = grid.getCell(r, c) ? null : new Node(r, c, grid.getEnd());
        dp[grid.getStart().getRow()][grid.getStart().getCol()].setDistFromStart(0);
    }

    private static void backtrack(Node node, Stack<Node> stack) {
        if(node == null)
            return;

        stack.add(node);
        backtrack(node.getParent(), stack);
    }

    private static boolean isValid(Node[][] dp, Position pos){
        return pos.getRow() >= 0 && pos.getCol() >= 0
                && pos.getRow() < dp.length && pos.getCol() < dp[0].length
                && dp[pos.getRow()][pos.getCol()] != null;
    }

    private static boolean pathFound(Node[][] dp, Grid grid){
        return dp[grid.getEnd().getRow()][grid.getEnd().getCol()].getDistFromStart() < Integer.MAX_VALUE / 2;
    }

    public static boolean canMoveDiagonal() {
        return canMoveDiagonal;
    }

    public static void toggleCanMoveDiagonal() {
        Pathfinder.canMoveDiagonal = !canMoveDiagonal;
    }
}
