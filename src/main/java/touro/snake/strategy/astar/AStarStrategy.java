package touro.snake.strategy.astar;

import touro.snake.*;
import touro.snake.strategy.SnakeStrategy;

import java.util.ArrayList;
import java.util.List;

public class AStarStrategy implements SnakeStrategy {
    private Snake snake;
    private List<Node> closed;
    private List<Node> open;
    private Node targetNode;
    private List<Node> shortestPath;

    @Override
    public void turnSnake(Snake snake, Garden garden) {
        this.snake = snake;

        Square head = snake.getHead();
        Food food = garden.getFood();

        if (food == null)
            return;
        targetNode = new Node(food);

        closed = new ArrayList<>();
        open = new ArrayList<>();

        Node headNode = new Node(head.getX(), head.getY());
        open.add(headNode);

        Node current = headNode;
        while (open.size() > 0) {
            //search for lowest fcost
            for (Node node : open) {
                if (node.getCost() < current.getCost()) {
                    current = node;
                }
            }
            //if reached target
            if (current.getX() == food.getX() && current.getY() == food.getY())
                break;
            //otherwise move current node to closed list
            open.remove(current);
            closed.add(current);

            // and expand frontier
            for (Direction d : Direction.values()) {
                Square neighborSquare = head.moveTo(d);
                Node neighborNode = new Node(neighborSquare, current, food);
                //neighbor is not traversable or have already visited
                if (neighborSquare.inBounds() || isInClosed(neighborNode)) {

                    //determine if there is a shorter path from start node to neighborNode
                    // other than from current node
                    //double movementCost = current.getFromStart() + current.distance(neighborSquare);
                    //if (neighborNode.getFromStart() > movementCost || isNotInOpen(neighborNode)) {
                      //  neighborNode.setParent(current);
                        if (isNotInOpen(neighborNode)) {
                            open.add(neighborNode);
                       // }
                    }
                }
            }
        }
        //set snake on the shortest path
        shortestPath.add(closed.get(closed.size() - 1)); //null pointer exception here
        while (current.getParent() != headNode) {
            shortestPath.add(current.getParent());
        }

        //there may be something very wrong with this loop...
        for (int i = shortestPath.size() - 1; i <= 0; i--) {
            current = shortestPath.get(i);
            Direction direction = head.directionTo(current);
            snake.turnTo(direction);
            garden.advance();
        }


    }

    private boolean isNotInOpen(Node neighborNode) {
        for (Node node : open) {
            if (node == neighborNode) {
                return false;
            }
        }
        return true;
    }

    private boolean isInClosed(Node neighborNode) {
        for (Node node : closed) {
            if (node == neighborNode) {
                return true;
            }
        }
        return false;
    }

    private boolean inSnakeBody(Square neighborSquare) {
        for (Square square : snake.getBody()) {
            if (neighborSquare == square)
                return true;
        }
        return false;
    }
}
