package touro.snake.strategy.astar.schwimmer;

import touro.snake.*;
import touro.snake.strategy.SnakeStrategy;
import touro.snake.strategy.astar.Node;

import java.util.*;

/**
 * Implement the AStar Algorithm
 * https://www.youtube.com/watch?v=-L-WgKMFuhE
 */
public class AStarStrategy implements SnakeStrategy {
    private List<Node> open;
    private List<Node> closed;
    private List<Square> shortestPath;
    private Square head;
    private Food food;

    @Override
    public void turnSnake(Snake snake, Garden garden) {

        Direction directions[] = Direction.values();
        this.food = garden.getFood();
        this.head = snake.getHead();
        if (food == null) {
            return;
        }

        open = new ArrayList<>();
        closed = new ArrayList<>();
        shortestPath = new ArrayList<>();

        open.add(new Node(snake.getHead()));

        while (!open.isEmpty()) {
            Node current = getLowestCost(open);
            open.remove(current);
            closed.add(current);

            if (current.equals(food)) {
                Node firstChild = getFirstChild(head, current);
                Direction direction = head.directionTo(firstChild);
                snake.turnTo(direction);
                setShortestPath(current);
                if (head.equals(food))
                {
                    open.clear();
                    closed.clear();
                }
                break;
            }

            for (Direction direction : directions) {
                Node neighbor = new Node(current.moveTo(direction), current, food);
                if (!neighbor.inBounds() || snake.contains(neighbor) || closed.contains(neighbor)) {
                    continue;
                }

                int index = open.indexOf(neighbor);
                if (index != -1) {
                    Node oldNeighbor = open.get(index);
                    if (neighbor.getCost() < oldNeighbor.getCost()) {
                        open.remove(index);
                        open.add(neighbor);
                    }
                }
                else {
                    open.add(neighbor);
                }

            }

        }

    }

    private void setShortestPath(Node current) {
        while (!current.getParent().equals(head)) {
            shortestPath.add(current.getParent());
            current = current.getParent();
        }
    }

    @Override
    public List<Square> getPath() {
        return shortestPath;
    }

    @Override
    public List<Square> getSearchSpace() {
        List<Square> searchSpace = new ArrayList<>();
        for (int i = 0; i < open.size(); i++)
        {
            searchSpace.add(open.get(i));
            searchSpace.add(closed.get(i));
        }
        return searchSpace;
    }

    private Node getLowestCost(List<Node> nodes) {
        return nodes.stream()
                .min(Comparator.comparingDouble(Node::getCost))
                .get();
    }

    public Node getFirstChild(Square head, Node end) {
        Node n = end;
        while (!n.getParent().equals(head)) {
            n = n.getParent();
        }
        return n;
    }

}
