package touro.snake;

import org.junit.Test;

import java.awt.event.MouseEvent;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SnakeMouseListenerTest {

    @Test
    public void mouseMoved_left() {
        //testing the scenario:  deltaX = -30, deltaY = 0
        //given
        Snake snake = mock(Snake.class);
        Square head = mock(Square.class);
        SnakeMouseListener mouseListener = new SnakeMouseListener(snake);

        MouseEvent event = mock(MouseEvent.class);
        doReturn(100).when(event).getX();

        //snake position x and y values are lower than mouse event's because
        //will be multiplied by 10 in mouseMoved()
        doReturn(head).when(snake).getHead();
        doReturn(7).when(head).getX();

        //when
        mouseListener.mouseMoved(event);

        //then
        verify(snake).turnTo(Direction.East);
    }

    @Test
    public void mouseMoved_right() {
        //testing the scenario:  deltaX = 30, deltaY = 0
        //given
        Snake snake = mock(Snake.class);
        Square head = mock(Square.class);
        SnakeMouseListener mouseListener = new SnakeMouseListener(snake);

        MouseEvent event = mock(MouseEvent.class);
        doReturn(70).when(event).getX();
        doReturn(100).when(event).getY();

        //snake position x and y values are lower than mouse event's because
        //will be multiplied by 10 in mouseMoved()
        doReturn(head).when(snake).getHead();
        doReturn(10).when(head).getX();
        doReturn(10).when(head).getY();

        //when
        mouseListener.mouseMoved(event);

        //then
        verify(snake).turnTo(Direction.West);
    }

    @Test
    public void mouseMoved_up() {
        //testing the scenario: deltaX: 0, deltaY = -30
        //given
        Snake snake = mock(Snake.class);
        Square head = mock(Square.class);
        SnakeMouseListener mouseListener = new SnakeMouseListener(snake);

        MouseEvent event = mock(MouseEvent.class);
        doReturn(70).when(event).getY();

        doReturn(head).when(snake).getHead();
        doReturn(10).when(head).getY();

        //when
        mouseListener.mouseMoved(event);

        //then
        verify(snake).turnTo(Direction.North);
    }

    @Test
    public void mouseMoved_down() {
        //testing the scenario: deltaX: 0, deltaY = 30
        //given
        Snake snake = mock(Snake.class);
        Square head = mock(Square.class);
        SnakeMouseListener mouseListener = new SnakeMouseListener(snake);

        MouseEvent event = mock(MouseEvent.class);
        doReturn(100).when(event).getY();

        doReturn(head).when(snake).getHead();
        doReturn(7).when(head).getY();

        //when
        mouseListener.mouseMoved(event);

        //then
        verify(snake).turnTo(Direction.South);
    }
}