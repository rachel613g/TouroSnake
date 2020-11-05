package touro.snake.strategy.astar.gutmann;

import touro.snake.*;
import touro.snake.strategy.SnakeStrategy;
import touro.snake.strategy.astar.Node;

import java.util.ArrayList;
import java.util.List;

public class AStarStrategy implements SnakeStrategy {
    private List<Node> closed;
    private List<Node> open;
    private Square head;
    private Food food;
    private List<Square> shortestPath;
    private Direction directions[] = Direction.values();

    @Override
    public void turnSnake(Snake snake, Garden garden) {

        this.head = snake.getHead();
        this.food = garden.getFood();

        if (food == null) {
            return;
        }

        closed = new ArrayList<>();
        open = new ArrayList<>();


        open.add(new Node(head));


        while (!open.isEmpty()) {
            Node current = getLowestFCost();

            //if reached target
            if (current.equals(food)) {
                Node firstChild = getFirstChild(head, current);
                Direction direction = head.directionTo(firstChild);
                setShortestPath(current);
                snake.turnTo(direction);
                break;
            }
            //otherwise move current node to closed list
            open.remove(current);
            closed.add(current);

            // and expand frontier
            for (Direction d : Direction.values()) {
                Node neighbor = new Node(current.moveTo(d), current, food);
                //neighbor is not traversable or have already visited
                if (!neighbor.inBounds() || closed.contains(neighbor) || snake.contains(neighbor)) {
                    continue;
                }
                //determine if there is a shorter path from start node to neighbor
                // other than from current node
                int index = open.indexOf(neighbor);
                if (index != -1) {
                    Node oldNeighbor = open.get(index);
                    if (neighbor.getCost() < oldNeighbor.getCost()) {
                        open.remove(index);
                        open.add(neighbor);
                    }
                } else {
                    open.add(neighbor);
                }
            }
        }
    }

    @Override
    public List<Square> getPath() {
        return shortestPath;
    }

    @Override
    public List<Square> getSearchSpace() {
        return List.copyOf(closed);
    }

    private void setShortestPath(Node current) {
        shortestPath = new ArrayList<>();
        while (!current.getParent().equals(head)) {
            shortestPath.add(current.getParent());
            current = current.getParent();
        }
    }

    private Node getLowestFCost() {
        Node compare = open.get(0);
        for (Node node : open) {
            if (node.getCost() < compare.getCost()) {
                compare = node;
            }
        }
        return compare;
    }

    public Node getFirstChild(Square head, Node end) {
        Node n = end;
        while (!n.getParent().equals(head)) {
            n = n.getParent();
        }
        return n;
    }
}
