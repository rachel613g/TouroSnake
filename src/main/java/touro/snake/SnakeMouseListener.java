package touro.snake;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static java.lang.Math.abs;
/**
SnakeMouseListener controls snake turns as direct by input from movement of the mouse.
 */
public class SnakeMouseListener extends MouseMotionAdapter {
    private final Snake snake;

    public SnakeMouseListener(Snake snake) {
        this.snake = snake;
    }

    public void mouseMoved(MouseEvent e) {
        //snake head postion (Square object) multiplied by 10 to be closer
        //to scale of mouse position (MouseEvent object) numbers

        //logic taken from https://bit.ly/36Gu7GG
        //determine x and y values of the slope of line between mouse position
        //and snake head position
        int deltaX = e.getX() - (snake.getHead().getX() * 10);
        int deltaY = e.getY() - (snake.getHead().getY() * 10);

        //go in direction of axis with greater value in slope of line
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            if (deltaX < 0) {
                snake.turnTo(Direction.West);
            } else {
                snake.turnTo(Direction.East);
            }
        }
        else if (Math.abs(deltaY) > Math.abs(deltaX)){
            if (deltaY < 0) {
                snake.turnTo(Direction.North);
            }else {
                snake.turnTo(Direction.South);
            }

        //else i.e. deltaX == deltaY
        //   do nothing
        }


    }
}
