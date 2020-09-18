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

    /*
    Below I began implementing what would be if there were NorthWest,
    North, and NorthEast directions. This is not finished.
     */

//    @Override
//    public void mouseMoved(MouseEvent e) {
//        //mouse is either NW, N or NE of snake head
//        if(e.getY() > snake.getHead().getY())
//        {
//            //mouse is NW of snake head
//            if(isMajorDiagonal(e.getX(), e.getY(),
//                    snake.getHead().getX(), snake.getHead().getY()))
//            {
//
//            }
//
//            //mouse is N of snake head
//        }
//    }
//
//    private boolean isMajorDiagonal(int mouseX, int mouseY, int snakeX, int snakeY)
//    {
//        throw new UnsupportedOperationException("not implemented yet");
//    }
//
//    private boolean isMinorDiagonal(int mouseX, int mouseY, int snakeX, int snakeY)
//    {
//        throw new UnsupportedOperationException("not implemented yet");
//    }
}
