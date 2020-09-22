package touro.snake;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class SnakeMouseListener extends MouseMotionAdapter {
    private final Snake snake;

    public SnakeMouseListener(Snake snake) {
        this.snake = snake;
    }

    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        System.out.println("Mouse moved: (" + e.getX() +", " + e.getY() + ")");
        if (e.getY() < snake.getHead().getY()) {
            snake.turnTo(Direction.North);
        } else if (e.getY() > snake.getHead().getY()) {
            snake.turnTo(Direction.South);
        } else if (e.getX() > snake.getHead().getX()){
            snake.turnTo(Direction.East);
        } else if (e.getX() < snake.getHead().getX()){
            snake.turnTo(Direction.West);
        }
    }
}
