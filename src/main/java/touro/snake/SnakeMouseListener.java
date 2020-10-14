package touro.snake;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static java.lang.Math.abs;
/**
SnakeMouseListener controls snake turns as directed by input from movement of the mouse.
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
        final int CELL_SIZE = 10;
        int deltaX = e.getX() - (snake.getHead().getX() * CELL_SIZE);
        int deltaY = e.getY() - (snake.getHead().getY() * CELL_SIZE);
        int absDeltaX = abs(deltaX);
        int absDeltaY = abs(deltaY);

        //go in direction of axis with greater value in slope of line
        if (absDeltaX > absDeltaY) {
            if (deltaX < 0) {
                snake.turnTo(Direction.West);
            } else {
                snake.turnTo(Direction.East);
            }
        }
        else if (absDeltaY > absDeltaX){
            if (deltaY < 0) {
                snake.turnTo(Direction.North);
            }else {
                snake.turnTo(Direction.South);
            }
        }
        //else i.e. deltaX == deltaY
        //do nothing
    }
}
