package touro.snake.strategy.astar;

import touro.snake.*;
import touro.snake.strategy.SnakeStrategy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AStarStrategy implements SnakeStrategy {
    @Override
    public void turnSnake(Snake snake, Garden garden) {

        Food food = garden.getFood();
        Square head = snake.getHead();

        List<Node> haveVisited = new ArrayList<>();
        List<Node> haveNotVisited = new ArrayList<>();


        haveNotVisited.add(new Node(head, null, food));

        Node current = haveNotVisited.get(0);
        while (head != food) {
            //search for lowest fcost
            for (Node node : haveNotVisited) {
                if (node.getCost() < current.getCost()) {
                    current = node;
                }
            }

            haveNotVisited.remove(current);
            haveVisited.add(current);

            //figure out why in video he needed below lines but with your
            //code it never will get called.
            //path has been found
            //if (current.distance(food) == 0)
                //break;

            //expand frontier
            for (Direction d : Direction.values()) {
                Square neighborSquare = head.moveTo(d);
                if (neighborSquare.inBounds()) {
                    Node neighborNode = new Node(neighborSquare, current, food);
                    haveNotVisited.add(neighborNode);
                }
            }

        }

    }
}
