package touro.snake;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static java.lang.Math.abs;

public class SnakeMouseListener extends MouseMotionAdapter {
    private final Snake snake;

    public SnakeMouseListener(Snake snake) {
        this.snake = snake;
    }

    public void mouseMoved(MouseEvent e) {
        //snake head postion multiplied by 10 to be closer 
        //to scale with mouse position numbers
        int headX = snake.getHead().getX() * 10;
        int headY = snake.getHead().getY() * 10;
        
        int mouseX = e.getX();
        int mouseY = e.getY();

        //mouse move to the right of snake head
        if (mouseX > headX) {
            snake.turnTo(Direction.East);
        }
        //mouse moved to the left of snake head
        else if (mouseX < headX) {
            snake.turnTo(Direction.West);
        }
        //mouse moved above snake head
        if (mouseY < headY) {
            snake.turnTo(Direction.North);
        }
        //mouse moved below snake head
        else if (mouseY > headY) {
            snake.turnTo(Direction.South);
        }

    }
}
