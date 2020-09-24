package touro.snake;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class SnakeMouseListener extends MouseMotionAdapter {
    private final Snake snake;

    public SnakeMouseListener(Snake snake) {
        this.snake = snake;
    }

    public void mouseMoved(MouseEvent e) {
        Square head = snake.getHead();

        //mouse moved above snake head
        if (e.getY() < head.getY()) {
            snake.turnTo(Direction.North);
        }
        //mouse moved below snake head
        else if (e.getY() > head.getY()) {
            snake.turnTo(Direction.South);
        }
        //mouse move to the left of snake head
        else if (e.getX() > head.getX()) {
            snake.turnTo(Direction.East);
        }
        //mouse moved to the right of snake head
        else if (e.getX() < head.getX()) {
            snake.turnTo(Direction.West);
        }
    }
}
