package touro.snake.strategy;

import touro.snake.*;

import java.util.List;

/**
 * An implementation of SnakeStrategy that has no effect.
 */
public class SimpleStrategy implements SnakeStrategy {
    @Override
    public void turnSnake(Snake snake, Garden garden) {
        Direction[] directions = Direction.values();
        Food food = garden.getFood();
        Square head = snake.getHead();
        Direction bestDirection = null;
        double shortestDistance = Double.MAX_VALUE;

        for (Direction d : directions) {
            Square neighbor = head.moveTo(d);
            double distanceToFood = neighbor.distance(food);
            if (snake.contains(neighbor) || !neighbor.inBounds()) {
                continue;
            }
            if (distanceToFood < shortestDistance) {
                bestDirection = d;
                shortestDistance = distanceToFood;
            }
        }

        snake.turnTo(bestDirection);
    }

    @Override
    public List<Square> getPath() {
        return null;
    }

    @Override
    public List<Square> getSearchSpace() {
        return null;
    }
}
